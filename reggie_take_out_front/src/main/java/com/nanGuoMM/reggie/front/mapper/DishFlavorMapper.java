package com.nanGuoMM.reggie.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanGuoMM.reggie.front.domain.dish_flavor.PO.DishFlavorPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品口味关系表 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-24
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavorPO> {

}
