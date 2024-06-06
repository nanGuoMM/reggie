package com.nanGuoMM.reggie.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.PO.SetmealPO;
import com.nanGuoMM.reggie.front.mapper.SetmealMapper;
import com.nanGuoMM.reggie.front.service.ISetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, SetmealPO> implements ISetmealService {

    @Cacheable(cacheNames = "setmealCache",key = "#setmealDTO.categoryId + '_' + #setmealDTO.status")
    @Override
    public List<SetmealDTO> listSetmeal(SetmealDTO setmealDTO) {
        //查询
        LambdaQueryWrapper<SetmealPO> queryWrapper = new LambdaQueryWrapper<SetmealPO>()
                .eq(SetmealPO::getCategoryId, setmealDTO.getCategoryId()).eq(SetmealPO::getStatus, setmealDTO.getStatus())
                .orderByDesc(SetmealPO::getUpdateTime);
        List<SetmealPO> setmealPOS = super.list(queryWrapper);

        return setmealPOS.stream().map(setmealPO -> {
            SetmealDTO setmealDTO1 = new SetmealDTO();
            BeanUtils.copyProperties(setmealPO, setmealDTO1);
            return setmealDTO1;
        }).collect(Collectors.toList());
    }
}

