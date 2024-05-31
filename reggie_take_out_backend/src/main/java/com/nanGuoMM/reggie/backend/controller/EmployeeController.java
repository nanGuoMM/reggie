package com.nanGuoMM.reggie.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeDTO;
import com.nanGuoMM.reggie.backend.domain.employee.PO.EmployeePO;
import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeLoginFormDTO;
import com.nanGuoMM.reggie.backend.domain.employee.VO.EmployeeLoginVO;
import com.nanGuoMM.reggie.backend.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Api(tags = "员工")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;


    @ApiOperation(value = "登录",notes = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(HttpServletRequest request, @RequestBody EmployeeLoginFormDTO empDto) {

        //查询
        LambdaQueryWrapper<EmployeePO> queryWrapper = new LambdaQueryWrapper<EmployeePO>()
                .eq(EmployeePO::getUsername,empDto.getUsername());
        EmployeePO employeePO = employeeService.getOne(queryWrapper);
        if(employeePO == null) {
            return Result.error("员工不存在");
        }
        //md5处理
        String password = DigestUtils.md5DigestAsHex(empDto.getPassword().getBytes());
        if (!password.equals(employeePO.getPassword())) {
            return Result.error("密码错误");
        }
        request.getSession().setAttribute("employee",employeePO.getId());
        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO();
        BeanUtils.copyProperties(employeePO,employeeLoginVO);
        return Result.success(employeeLoginVO);
    }

    @ApiOperation(value = "退出",notes = "退出管理系统")
    @PostMapping("/logout")
    public Result<Object> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return Result.success();
    }

    @ApiOperation(value = "添加",notes = "新增员工")
    @PostMapping
    public Result<Object> add(@RequestBody EmployeeDTO employeeDto) {
        //封装po对象
        EmployeePO employee = new EmployeePO();
        BeanUtils.copyProperties(employeeDto,employee);
        //设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //调用service
        employeeService.save(employee);
        return Result.success();
    }

    @ApiOperation(value = "查询",notes = "分页查询")
    @GetMapping("/page")
    public Result<IPage<EmployeeDTO>> page(@RequestParam Map<String,String> params) {
        //调用service
        IPage<EmployeeDTO> employeePageVOIPage = employeeService
                .pageEmployee(params.get("page"), params.get("pageSize"), params.get("name"));
        return Result.success(employeePageVOIPage);
    }

    @ApiOperation(value = "状态",notes = "修改账号")
    @PutMapping
    public Result<Object> update(HttpServletRequest req, @RequestBody EmployeeDTO updateDTO) {

        //封装PO数据
        EmployeePO employee = new EmployeePO();
        BeanUtils.copyProperties(updateDTO,employee);

        //调用service
        employeeService.updateById(employee);
        return Result.success();
    }

    @ApiOperation(value = "id查询",notes = "根据id查询")
    @GetMapping("/{id}")
    public Result<EmployeeDTO> getById(@PathVariable Long id) {
        //调用service
        EmployeePO empPO = employeeService.getById(id);

        if(empPO == null) {
            return Result.error("查询结果为空");
        }

        //封装发送EmployeeDTO
        EmployeeDTO empDTO = new EmployeeDTO();
        BeanUtils.copyProperties(empPO,empDTO);
        return Result.success(empDTO);
    }
}
