package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.orders.Orders;
import com.nanGuoMM.reggie.front.domain.orders.OrdersDTO;
import com.nanGuoMM.reggie.front.service.IOrderDetailService;
import com.nanGuoMM.reggie.front.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("用户下单")
    @PostMapping("/submit")
    public Result<Object> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return Result.success();
    }

    @ApiOperation("查询")
    @GetMapping("/userPage")
    public Result<IPage<OrdersDTO>> pageOrder(Integer page,Integer pageSize) {
        IPage<OrdersDTO> ordersDTOIPage = ordersService.pageOrder(page,pageSize);
        return Result.success(ordersDTOIPage);
    }
}
