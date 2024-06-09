package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.PO.SetmealPO;
import com.nanGuoMM.reggie.front.service.ISetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@RestController
@RequestMapping("/setmeal")
@Api(tags = "套餐")
public class SetmealController {
    @Autowired
    private ISetmealService setmealService;

    @ApiOperation("查询")
    @GetMapping("/list")
    public Result<List<SetmealDTO>> listSetmeal(SetmealDTO setmealDTO) {

        List<SetmealDTO> setmealDTOS =setmealService.listSetmeal(setmealDTO);

        return Result.success(setmealDTOS);
    }

    @ApiOperation("展示")
    @GetMapping("/dish/{ids}")
    public Result<List<DishDTO>> dish(@PathVariable Long ids){
        List<DishDTO> dishDTOList = setmealService.dish(ids);
        return Result.success(dishDTOList);
    }
}
