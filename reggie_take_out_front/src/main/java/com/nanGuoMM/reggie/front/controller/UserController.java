package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.user.DTO.UserLoginDTO;
import com.nanGuoMM.reggie.front.domain.user.DTO.UserRegisterDTO;
import com.nanGuoMM.reggie.front.domain.user.PO.UserPO;
import com.nanGuoMM.reggie.front.domain.user.VO.UserVO;
import com.nanGuoMM.reggie.front.service.IUserService;
import com.nanGuoMM.reggie.front.utils.MailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-28
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private MailUtil mailUtil;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<UserVO> login(HttpServletRequest request, @ApiParam("登录表单") @RequestBody UserLoginDTO loginDTO) {
        //查询
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getMail,loginDTO.getMail());
        UserPO userPO = userService.getOne(queryWrapper);
        if(userPO == null) {
            return Result.error("邮箱未注册");
        }
        //将密码md5处理
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        if (!password.equals(userPO.getPassword())) {
            return Result.error("密码错误");
        }
        //存到session，并将用户信息保存到本地储存空间
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userPO,userVO);
        request.getSession().setAttribute("user",userPO.getId());
        return Result.success(userVO);
    }

    @ApiOperation("发送验证码")
    @GetMapping("/code")
    public Result<Object> code(@ApiParam("邮箱") @RequestParam String mail) {
        //检查邮箱是否已注册
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getMail,mail);
        if (userService.getOne(queryWrapper) != null) {
            return Result.error("此邮箱已注册");
        }
        //生成验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            // 生成随机数字，范围为0-9
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        String code = sb.toString();
        //发送验证码
        mailUtil.sendMail(mail, "瑞吉外卖", "这是瑞吉外卖的注册码:" + code +
                "------请勿随意交给他人！！！");
        //生成验证码放入session
        log.info("验证码:{}", code);
        //存入Redis，有效时间三分钟
        redisTemplate.opsForValue().set(mail,code,3, TimeUnit.MINUTES);
        return Result.success();
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<Object> register(HttpServletRequest request,@ApiParam("用户注册表单") @RequestBody UserRegisterDTO registerDTO) {
        //获取验证码
        String code = redisTemplate.opsForValue().get(registerDTO.getMail());
        if (code == null) {
            return Result.error("请获取验证码");
        }
        if (!code.equals(registerDTO.getCode())) {
            return Result.error("验证码错误");
        }

        //验证通过删除本地缓存的验证码
        redisTemplate.delete(registerDTO.getMail());

        //对密码进行md5加密
        registerDTO.setPassword(DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes()));
        //执行插入逻辑
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(registerDTO,userPO);
        userService.save(userPO);

        return Result.success();
    }

    @ApiOperation("退出")
    @PostMapping("/loginout")
    public Result<Object> logout(HttpServletRequest request) {
        //删除session
        request.getSession().removeAttribute("user");
        return Result.success();
    }
}
