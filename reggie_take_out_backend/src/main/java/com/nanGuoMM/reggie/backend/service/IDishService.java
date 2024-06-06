package com.nanGuoMM.reggie.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.backend.domain.dish.PO.DishPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Transactional
public interface IDishService extends IService<DishPO> {
    IPage<DishDTO> pageDish(String page, String PageSize, String name);

    void saveDish(DishDTO dishDto);

    DishDTO getDishById(Long id);

    void updateDish(DishDTO dishDTO);

    void deleteDish(List<Long> ids);

    void updateStatus(Integer status, List<Long> ids);
}
