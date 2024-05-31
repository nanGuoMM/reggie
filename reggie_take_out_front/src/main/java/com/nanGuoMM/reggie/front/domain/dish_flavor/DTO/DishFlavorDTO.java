package com.nanGuoMM.reggie.front.domain.dish_flavor.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "菜品口味", description = "菜品口味DTO")
@Getter
@Setter
public class DishFlavorDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品")
    private Long dishId;

    @ApiModelProperty("口味名称")
    private String name;

    @ApiModelProperty("口味数据list")
    private String value;
}
