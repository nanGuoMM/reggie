package com.nanGuoMM.reggie.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nanGuoMM.reggie.front.domain.category.DTO.CategoryDTO;
import com.nanGuoMM.reggie.front.domain.category.PO.CategoryPO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Transactional
public interface ICategoryService extends IService<CategoryPO> {
}
