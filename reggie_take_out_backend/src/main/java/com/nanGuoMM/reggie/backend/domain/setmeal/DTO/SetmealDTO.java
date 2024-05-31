package com.nanGuoMM.reggie.backend.domain.setmeal.DTO;

import com.nanGuoMM.reggie.backend.domain.setmeal_dish.DTO.SetmealDishDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Getter
@Setter
@ApiModel("套餐")
public class SetmealDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("套餐分类id")
    private Long categoryId;

    @ApiModelProperty("套餐名称")
    private String name;

    @ApiModelProperty("套餐价格")
    private BigDecimal price;

    @ApiModelProperty("状态 0:停用 1:启用")
    private Integer status;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("套餐分类名")
    private String categoryName;

    @ApiModelProperty("菜品列表")
    private final List<SetmealDishDTO> setmealDishes = new ArrayList<>();
}
