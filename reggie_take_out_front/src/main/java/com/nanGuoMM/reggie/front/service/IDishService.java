package com.nanGuoMM.reggie.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nanGuoMM.reggie.front.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.front.domain.dish.PO.DishPO;
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
public interface IDishService extends IService<DishPO> {
}
