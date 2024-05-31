package com.nanGuoMM.reggie.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanGuoMM.reggie.front.domain.dish.PO.DishPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Mapper
public interface DishMapper extends BaseMapper<DishPO> {

}
