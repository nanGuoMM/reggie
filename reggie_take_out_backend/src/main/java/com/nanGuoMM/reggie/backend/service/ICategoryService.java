package com.nanGuoMM.reggie.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.category.DTO.CategoryDTO;
import com.nanGuoMM.reggie.backend.domain.category.PO.CategoryPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    IPage<CategoryDTO> pageCategory(Integer page, Integer pageSize);
    void removeCategory(Long id);

    void saveCagory(CategoryDTO categorySaveFormDTO);

    void updateCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> listCategory(Integer type);
}
