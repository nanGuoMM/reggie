package com.nanGuoMM.reggie.backend.mapper;

import com.nanGuoMM.reggie.backend.domain.employee.PO.EmployeePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeePO> {

}
