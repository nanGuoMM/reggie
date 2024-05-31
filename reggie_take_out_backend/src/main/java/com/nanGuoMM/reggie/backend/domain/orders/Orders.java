package com.nanGuoMM.reggie.backend.domain.orders;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-30
 */
@Getter
@Setter
@TableName("orders")
@ApiModel(value = "OrdersPO对象", description = "订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("订单号")
    @TableField("number")
    private String number;

    @ApiModelProperty("订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("下单用户")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("地址id")
    @TableField("address_book_id")
    private Long addressBookId;

    @ApiModelProperty("下单时间")
    @TableField("order_time")
    private LocalDateTime orderTime;

    @ApiModelProperty("结账时间")
    @TableField("checkout_time")
    private LocalDateTime checkoutTime;

    @ApiModelProperty("支付方式 1微信,2支付宝")
    @TableField("pay_method")
    private Integer payMethod;

    @ApiModelProperty("实收金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("收货人姓名")
    @TableField("consignee")
    private String consignee;
}
