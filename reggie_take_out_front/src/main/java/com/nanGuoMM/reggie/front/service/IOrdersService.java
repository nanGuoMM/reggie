package com.nanGuoMM.reggie.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.front.domain.orders.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nanGuoMM.reggie.front.domain.orders.OrdersDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-30
 */
@Transactional
public interface IOrdersService extends IService<Orders> {

    void submit(Orders orders);

    IPage<OrdersDTO> pageOrder(Integer page, Integer pageSize);
}
