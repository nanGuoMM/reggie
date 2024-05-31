package com.nanGuoMM.reggie.backend.domain.category.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜品及套餐分类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Getter
@Setter
@ApiModel(value = "菜品及套餐分类",description = "菜品及套餐分类DTO")
public class CategoryDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("类型   1 菜品分类 2 套餐分类")
    private Integer type;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("顺序")
    private Integer sort;

    @ApiModelProperty("最后操作时间")
    private LocalDateTime updateTime;
}
