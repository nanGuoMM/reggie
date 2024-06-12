package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.category.DTO.CategoryDTO;
import com.nanGuoMM.reggie.front.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.front.domain.dish.PO.DishPO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.DTO.DishFlavorDTO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.PO.DishFlavorPO;
import com.nanGuoMM.reggie.front.service.IDishFlavorService;
import com.nanGuoMM.reggie.front.service.IDishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Api(tags = "菜品")
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private IDishService dishService;

    @ApiOperation(value = "查询",notes = "下拉列表")
    @GetMapping("/list")
    public Result<List<DishDTO>> listDish(@ApiParam("分类id") Long categoryId,@ApiParam("状态") Integer status) {
        List<DishDTO> dishDTOS = dishService.listDish(categoryId, status);
        return Result.success(dishDTOS);
    }
}
