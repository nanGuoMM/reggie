package com.nanGuoMM.reggie.front.mapper;

import com.nanGuoMM.reggie.front.domain.user.PO.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-28
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}
