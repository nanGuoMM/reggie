package com.nanGuoMM.reggie.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanGuoMM.reggie.front.domain.category.PO.CategoryPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryPO> {

}
