package com.nanGuoMM.reggie.front.domain.shopping_cart;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@Getter
@Setter
@ApiModel(value = "ShoppingCartPO对象", description = "购物车")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("菜品id")
    private Long dishId;

    @ApiModelProperty("套餐id")
    private Long setmealId;

    @ApiModelProperty("口味")
    private String dishFlavor;

    @ApiModelProperty("数量")
    private Integer number;

    @ApiModelProperty("金额")
    private BigDecimal amount;
}
