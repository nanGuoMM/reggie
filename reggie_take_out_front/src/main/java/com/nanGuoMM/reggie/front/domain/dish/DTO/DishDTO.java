package com.nanGuoMM.reggie.front.domain.dish.DTO;

import com.nanGuoMM.reggie.front.domain.dish_flavor.DTO.DishFlavorDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "菜品表单",description = "添加表单DTO")
public class DishDTO {
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    @ApiModelProperty("商品码")
    private String code;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("0 停售 1 起售")
    private Integer status;

    @ApiModelProperty("顺序")
    private Integer sort;

    @ApiModelProperty("最后操作时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("口味")
    private final List<DishFlavorDTO> flavors = new ArrayList<>();

    @ApiModelProperty("菜品分类名")
    private String categoryName;
}
