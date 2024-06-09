package com.nanGuoMM.reggie.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.front.domain.dish.PO.DishPO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.DTO.DishFlavorDTO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.PO.DishFlavorPO;
import com.nanGuoMM.reggie.front.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.PO.SetmealPO;
import com.nanGuoMM.reggie.front.domain.setmeal_dish.PO.SetmealDishPO;
import com.nanGuoMM.reggie.front.mapper.SetmealMapper;
import com.nanGuoMM.reggie.front.service.IDishFlavorService;
import com.nanGuoMM.reggie.front.service.IDishService;
import com.nanGuoMM.reggie.front.service.ISetmealDishService;
import com.nanGuoMM.reggie.front.service.ISetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ISetmealDishService setmealDishService;

    @Autowired
    private IDishService dishService;

    @Autowired
    private IDishFlavorService dishFlavorService;

    @Cacheable(cacheNames = "setmealCache",key = "'setmeal_single_' + #ids")
    @Override
    public List<DishDTO> dish(Long ids) {
        LambdaQueryWrapper<SetmealDishPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDishPO::getSetmealId,ids);
        List<SetmealDishPO> setmealDishList = setmealDishService.list(queryWrapper);
        return setmealDishList.stream().map((item) -> {
            Long dishId = Long.parseLong(item.getDishId());
            DishDTO dishDto = new DishDTO();
            DishPO byId = dishService.getById(dishId);
            BeanUtils.copyProperties(byId,dishDto);
            LambdaQueryWrapper<DishFlavorPO> flavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            flavorLambdaQueryWrapper.eq(DishFlavorPO::getDishId,dishId);
            List<DishFlavorPO> list = dishFlavorService.list(flavorLambdaQueryWrapper);
            list.forEach(dishFlavorPO -> {
                DishFlavorDTO dishFlavorDTO = new DishFlavorDTO();
                BeanUtils.copyProperties(dishFlavorPO, dishFlavorDTO);
                dishDto.getFlavors().add(dishFlavorDTO);
            });
            return dishDto;
        }).collect(Collectors.toList());
    }

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

