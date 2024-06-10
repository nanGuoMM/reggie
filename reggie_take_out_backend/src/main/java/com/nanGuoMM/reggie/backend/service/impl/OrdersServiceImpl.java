package com.nanGuoMM.reggie.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanGuoMM.reggie.backend.domain.order_detail.OrderDetail;
import com.nanGuoMM.reggie.backend.domain.orders.Orders;
import com.nanGuoMM.reggie.backend.domain.orders.OrdersDTO;
import com.nanGuoMM.reggie.backend.mapper.OrdersMapper;
import com.nanGuoMM.reggie.backend.service.IOrderDetailService;
import com.nanGuoMM.reggie.backend.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-30
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    private IOrderDetailService orderDetailService;
    @Cacheable(cacheNames = "orderCache",key = "'page:' + #page  + '_pageSize:' + #pageSize + '_beginTime:' + #beginTime + '_endTime:' + #endTime + '_number:' + #number")
    @Override
    public IPage<OrdersDTO> pageOrder(Integer page, Integer pageSize, LocalDateTime beginTime, LocalDateTime endTime, Long number) {
        Page<Orders> ordersIPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(beginTime != null&&endTime != null,Orders::getOrderTime,beginTime,endTime)
                .eq(number != null,Orders::getNumber,number).orderByDesc(Orders::getOrderTime);
        super.page(ordersIPage,queryWrapper);
        //查询orderDetail表，并封装List<OrdersDTO>
        List<OrdersDTO> ordersDTOList = ordersIPage.getRecords().stream().map(orders -> {
            OrdersDTO ordersDTO = new OrdersDTO();
            LambdaQueryWrapper<OrderDetail> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(OrderDetail::getOrderId, orders.getNumber());
            List<OrderDetail> orderDetails = orderDetailService.list(queryWrapper1);

            ordersDTO.setOrderDetails(orderDetails);
            BeanUtils.copyProperties(orders, ordersDTO);
            return ordersDTO;
        }).collect(Collectors.toList());
        //封装page
        IPage<OrdersDTO> ordersDTOIPage = new Page<>(page,pageSize,ordersIPage.getTotal());
        ordersDTOIPage.setRecords(ordersDTOList);
        return ordersDTOIPage;
    }

    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    @Override
    public void updateStatus(Orders orders) {
        super.updateById(orders);
    }
}
