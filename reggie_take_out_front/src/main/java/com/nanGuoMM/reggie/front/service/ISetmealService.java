package com.nanGuoMM.reggie.front.service;

import com.nanGuoMM.reggie.front.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.PO.SetmealPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
public interface ISetmealService extends IService<SetmealPO> {

    List<SetmealDTO> listSetmeal(SetmealDTO setmealDTO);
}
