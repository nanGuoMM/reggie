package com.nanGuoMM.reggie.backend.service.impl;

import com.nanGuoMM.reggie.backend.domain.order_detail.OrderDetail;
import com.nanGuoMM.reggie.backend.mapper.OrderDetailMapper;
import com.nanGuoMM.reggie.backend.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-30
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
