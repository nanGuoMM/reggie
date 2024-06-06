package com.nanGuoMM.reggie.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.backend.domain.dish.PO.DishPO;
import com.nanGuoMM.reggie.backend.service.IDishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "查询",notes = "分页查询")
    @GetMapping("/page")
    public Result<IPage<DishDTO>> pageDish(@RequestParam Map<String,String> params) {
        //调用service
        IPage<DishDTO> dishDTOIPage = dishService.pageDish(params.get("page"), params.get("pageSize"),params.get("name"));
        return Result.success(dishDTOIPage);
    }

    @ApiOperation(value = "添加",notes = "添加菜品")
    @PostMapping
    public Result<Object> saveDish(@RequestBody DishDTO dishDto) {
        //调用service
        dishService.saveDish(dishDto);
        return Result.success();
    }

    @ApiOperation(value = "显示",notes = "按id查询数据回显")
    @GetMapping("/{id}")
    public Result<DishDTO> getById(@PathVariable Long id) {
        DishDTO dishDTO = dishService.getDishById(id);
        return Result.success(dishDTO);
    }

    @ApiOperation(value = "修改",notes = "保存修改结果")
    @PutMapping
    public Result<Object> updateDish(@RequestBody DishDTO dishDTO) {

        dishService.updateDish(dishDTO);
        return Result.success();
    }

    @ApiOperation(value = "删除",notes = "删除菜品")
    @DeleteMapping
    public Result<Object> deleteDish(@RequestParam List<Long> ids) {
        //调用service
        dishService.deleteDish(ids);
        return Result.success();
    }

    @ApiOperation(value = "状态",notes = "停售起售")
    @PostMapping("/status/{status}")
    public Result<Object> updateStatus(@PathVariable Integer status,@RequestParam List<Long> ids) {
        dishService.updateStatus(status,ids);
        return Result.success();
    }

    @ApiOperation(value = "查询",notes = "下拉列表")
    @GetMapping("/list")
    public Result<List<DishDTO>> listDish(@RequestParam Long categoryId) {
        //查询
        LambdaQueryWrapper<DishPO> queryWrapper = new LambdaQueryWrapper<DishPO>()
                .eq(DishPO::getCategoryId,categoryId);
        List<DishPO> dishPOS = dishService.list(queryWrapper);
        //转换
        List<DishDTO> dishDTOS = new ArrayList<>();
        dishPOS.forEach(dishPO -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(dishPO,dishDTO);
            dishDTOS.add(dishDTO);
        });
        return Result.success(dishDTOS);
    }
}
