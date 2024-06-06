package com.nanGuoMM.reggie.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.domain.category.DTO.CategoryDTO;
import com.nanGuoMM.reggie.backend.domain.category.PO.CategoryPO;
import com.nanGuoMM.reggie.backend.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Api(tags = "菜品套餐分类")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "新增", notes = "添加分类")
    @PostMapping
    public Result<Object> save(@RequestBody CategoryDTO categorySaveFormDTO) {

        categoryService.saveCagory(categorySaveFormDTO);
        return Result.success();
    }

    @ApiOperation(value = "查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<IPage<CategoryDTO>> pageCategory(Integer page, Integer pageSize) {

        //调用service
        IPage<CategoryDTO> categoryDTOIPage = categoryService.pageCategory(page, pageSize);
        return Result.success(categoryDTOIPage);
    }

    @ApiOperation(value = "删除",notes = "删除分类")
    @DeleteMapping
    public Result<Object> removeCategory(Long ids) {

        //调用service
        categoryService.removeCategory(ids);
        return Result.success();
    }

    @ApiOperation(value = "修改",notes = "修改分类")
    @PutMapping
    public Result<Object> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @ApiOperation(value = "查询",notes = "展示分类下拉列表")
    @GetMapping("/list")
    public Result<List<CategoryDTO>> listCategory(Integer type) {
        List<CategoryDTO> listDTO = categoryService.listCategory(type);
        return Result.success(listDTO);
    }
}
