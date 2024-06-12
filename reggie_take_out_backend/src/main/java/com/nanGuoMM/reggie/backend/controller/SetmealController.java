package com.nanGuoMM.reggie.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.backend.service.ISetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@RestController
@RequestMapping("/setmeal")
@Api(tags = "套餐")
public class SetmealController {

    @Autowired
    private ISetmealService setmealService;

    @ApiOperation(value = "显示",notes = "分页查询")
    @GetMapping("/page")
    public Result<IPage<SetmealDTO>> pageSetmeal(@ApiParam("分页查询参数") @RequestParam Map<String,String> params) {
        //调用service
        IPage<SetmealDTO> setmealDTOIPage = setmealService.pageSetmeal(Integer.parseInt(params.get("page"))
                , Integer.parseInt(params.get("pageSize")), params.get("name"));
        return Result.success(setmealDTOIPage);
    }

    @ApiOperation(value = "添加",notes = "添加菜品")
    @PostMapping
    public Result<Object> saveSetmeal(@ApiParam("新套餐") @RequestBody SetmealDTO setmealDTO) {

        //调用service
        setmealService.saveSetmeal(setmealDTO);
        return Result.success();
    }

    @ApiOperation(value = "查询",notes = "回显查询")
    @GetMapping("/{id}")
    public Result<SetmealDTO> getSetmealById(@ApiParam("套餐id") @PathVariable Long id) {
        //调用service
        SetmealDTO setmealById = setmealService.getSetmealById(id);
        return Result.success(setmealById);
    }

    @ApiOperation(value = "修改",notes = "保存修改的数据")
    @PutMapping
    public Result<Object> updateSetmeal(@ApiParam("新套餐") @RequestBody SetmealDTO setmealDTO) {
        //调用service
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }

    @ApiOperation(value = "删除",notes = "删除套餐")
    @DeleteMapping
    public Result<Object> deleteSetmeal(@ApiParam("套餐ids") @RequestParam List<Long> ids) {

        //调用service
        setmealService.deleteSetmeal(ids);
        return Result.success();
    }

    @ApiOperation(value = "状态",notes = "启售禁售")
    @PostMapping("/status/{status}")
    public Result<Object> updateStatus(@ApiParam("状态码") @PathVariable Integer status,
                                       @ApiParam("套餐ids") @RequestParam List<Long> ids) {
        setmealService.updateStatus(status,ids);
        return Result.success();
    }
}
