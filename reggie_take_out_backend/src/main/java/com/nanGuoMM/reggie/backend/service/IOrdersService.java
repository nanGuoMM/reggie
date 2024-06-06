package com.nanGuoMM.reggie.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.orders.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nanGuoMM.reggie.backend.domain.orders.OrdersDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    IPage<OrdersDTO> pageOrder(Integer page, Integer pageSize, LocalDateTime beginTime, LocalDateTime endTime, Long number);

    void updateStatus(Orders orders);
}
