package com.nanGuoMM.reggie.front.mapper;

import com.nanGuoMM.reggie.front.domain.address_book.PO.AddressBookPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 地址管理 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBookPO> {

}
