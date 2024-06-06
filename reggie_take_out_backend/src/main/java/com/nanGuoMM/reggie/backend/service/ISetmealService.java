package com.nanGuoMM.reggie.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.backend.domain.setmeal.PO.SetmealPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Transactional
public interface ISetmealService extends IService<SetmealPO> {

    IPage<SetmealDTO> pageSetmeal(int page, int pageSize, String name);

    void saveSetmeal(SetmealDTO setmealDTO);

    SetmealDTO getSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void deleteSetmeal(List<Long> ids);

    void updateStatus(Integer status, List<Long> ids);
}
