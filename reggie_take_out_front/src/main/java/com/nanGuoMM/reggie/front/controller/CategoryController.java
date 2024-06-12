package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.category.DTO.CategoryDTO;
import com.nanGuoMM.reggie.front.domain.category.PO.CategoryPO;
import com.nanGuoMM.reggie.front.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "查询",notes = "展示分类下拉列表")
    @GetMapping("/list")
    public Result<List<CategoryDTO>> listCategory(@ApiParam("类型") Integer type) {
        //查询
        LambdaQueryWrapper<CategoryPO> queryWrapper = new LambdaQueryWrapper<CategoryPO>()
                .eq(type != null,CategoryPO::getType,type).orderByAsc(CategoryPO::getSort).orderByDesc(CategoryPO::getUpdateTime);
        List<CategoryPO> listPO = categoryService.list(queryWrapper);
        //封装dto数据
        List<CategoryDTO> listDTO = new ArrayList<>();
        listPO.forEach(categoryPO -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(categoryPO,categoryDTO);
            listDTO.add(categoryDTO);
        });
        return Result.success(listDTO);
    }
}
