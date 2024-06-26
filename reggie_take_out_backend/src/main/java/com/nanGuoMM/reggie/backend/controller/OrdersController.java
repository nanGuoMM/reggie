package com.nanGuoMM.reggie.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.domain.orders.Orders;
import com.nanGuoMM.reggie.backend.domain.orders.OrdersDTO;
import com.nanGuoMM.reggie.backend.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-30
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @ApiOperation("查询")
    @GetMapping("/page")
    public Result<IPage<OrdersDTO>> pageOrder(@ApiParam("页码") Integer page,@ApiParam("每页条数") Integer pageSize,
                                              @ApiParam("开始时间") LocalDateTime beginTime,@ApiParam("结束时间") LocalDateTime endTime,
                                              @ApiParam("订单号") Long number) {
        IPage<OrdersDTO> ordersDTOIPage = ordersService.pageOrder(page,pageSize,beginTime,endTime,number);
        return Result.success(ordersDTOIPage);
    }

    @ApiOperation("状态")
    @PutMapping
    public Result<Object> updateStatus(@ApiParam("原数据") @RequestBody Orders orders) {
        ordersService.updateStatus(orders);
        return Result.success();
    }
}
