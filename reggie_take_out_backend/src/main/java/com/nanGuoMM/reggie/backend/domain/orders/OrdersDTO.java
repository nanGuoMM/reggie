package com.nanGuoMM.reggie.backend.domain.orders;


import com.nanGuoMM.reggie.backend.domain.order_detail.OrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
@ApiModel("订单表DTO")
public class OrdersDTO {
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("订单号")
    private String number;

    @ApiModelProperty("订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    private Integer status;

    @ApiModelProperty("下单用户")
    private Long userId;

    @ApiModelProperty("地址id")
    private Long addressBookId;

    @ApiModelProperty("下单时间")
    private LocalDateTime orderTime;

    @ApiModelProperty("结账时间")
    private LocalDateTime checkoutTime;

    @ApiModelProperty("支付方式 1微信,2支付宝")
    private Integer payMethod;

    @ApiModelProperty("实收金额")
    private BigDecimal amount;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("收货人姓名")
    private String consignee;

    @ApiModelProperty("订单明细")
    private List<OrderDetail> orderDetails;
}
