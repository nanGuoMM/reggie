package com.nanGuoMM.reggie.front.domain.setmeal_dish.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-26
 */
@Getter
@Setter
@ApiModel("套餐菜品关系")
public class SetmealDishDTO {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("套餐id")
    private String setmealId;
    @ApiModelProperty("菜品id")
    private String dishId;
    @ApiModelProperty("菜品名称 （冗余字段）")
    private String name;
    @ApiModelProperty("菜品原价（冗余字段）")
    private BigDecimal price;
    @ApiModelProperty("份数")
    private Integer copies;
    @ApiModelProperty("排序")
    private Integer sort;
}
