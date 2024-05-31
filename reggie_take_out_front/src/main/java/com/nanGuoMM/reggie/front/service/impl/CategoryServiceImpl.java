package com.nanGuoMM.reggie.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanGuoMM.reggie.front.domain.category.PO.CategoryPO;
import com.nanGuoMM.reggie.front.mapper.CategoryMapper;
import com.nanGuoMM.reggie.front.service.ICategoryService;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryPO> implements ICategoryService {
}
