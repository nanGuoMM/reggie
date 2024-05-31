package com.nanGuoMM.reggie.front.service.impl;

import com.nanGuoMM.reggie.front.domain.user.PO.UserPO;
import com.nanGuoMM.reggie.front.mapper.UserMapper;
import com.nanGuoMM.reggie.front.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

}
