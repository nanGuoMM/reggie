package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.front.domain.setmeal.PO.SetmealPO;
import com.nanGuoMM.reggie.front.service.ISetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        //查询
        LambdaQueryWrapper<SetmealPO> queryWrapper = new LambdaQueryWrapper<SetmealPO>()
                .eq(SetmealPO::getId,setmealDTO.getId()).eq(SetmealPO::getStatus,setmealDTO.getStatus())
                .orderByDesc(SetmealPO::getUpdateTime);
        List<SetmealPO> setmealPOS = setmealService.list(queryWrapper);

        List<SetmealDTO> setmealDTOS = setmealPOS.stream().map(setmealPO -> {
            SetmealDTO setmealDTO1 = new SetmealDTO();
            BeanUtils.copyProperties(setmealPO, setmealDTO1);
            return setmealDTO1;
        }).collect(Collectors.toList());

        return Result.success(setmealDTOS);
    }
}
