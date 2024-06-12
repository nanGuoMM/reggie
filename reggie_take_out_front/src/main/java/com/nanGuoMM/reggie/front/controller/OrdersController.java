package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.orders.Orders;
import com.nanGuoMM.reggie.front.domain.orders.OrdersDTO;
import com.nanGuoMM.reggie.front.service.IOrderDetailService;
import com.nanGuoMM.reggie.front.service.IOrdersService;
import com.nanGuoMM.reggie.front.utils.BaseContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Result<Object> submit(@ApiParam("新订单") @RequestBody Orders orders) {
        //获取用户id
        Long userId = BaseContext.getCurrentId();
        ordersService.submit(orders,userId);
        return Result.success();
    }

    @ApiOperation("查询")
    @GetMapping("/userPage")
    public Result<IPage<OrdersDTO>> pageOrder(@ApiParam("页码") Integer page,@ApiParam("每页条数") Integer pageSize) {
        //获取当前用户数据
        Long userId = BaseContext.getCurrentId();
        IPage<OrdersDTO> ordersDTOIPage = ordersService.pageOrder(page,pageSize,userId);
        return Result.success(ordersDTOIPage);
    }
}
