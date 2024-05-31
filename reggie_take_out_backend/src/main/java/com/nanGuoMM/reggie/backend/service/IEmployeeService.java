package com.nanGuoMM.reggie.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeDTO;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeLoginFormDTO;
import com.nanGuoMM.reggie.backend.domain.employee.PO.EmployeePO;
import com.nanGuoMM.reggie.backend.domain.employee.VO.EmployeeLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
public interface IEmployeeService extends IService<EmployeePO> {

    IPage<EmployeeDTO> pageEmployee(String page, String pageSize, String name);
}
