package com.nanGuoMM.reggie.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeDTO;
import com.nanGuoMM.reggie.backend.domain.employee.DTO.EmployeeLoginFormDTO;
import com.nanGuoMM.reggie.backend.domain.employee.PO.EmployeePO;
import com.nanGuoMM.reggie.backend.domain.employee.VO.EmployeeLoginVO;
import com.nanGuoMM.reggie.backend.mapper.EmployeeMapper;
import com.nanGuoMM.reggie.backend.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeePO> implements IEmployeeService {


    @Override
    public IPage<EmployeeDTO> pageEmployee(String page, String pageSize, String name) {
        //查询数据
        Page<EmployeePO> pagePo = new Page<>(Integer.parseInt(page),Integer.parseInt(pageSize));
        //条件构造器
        LambdaQueryWrapper<EmployeePO> queryWrapper = new LambdaQueryWrapper<EmployeePO>()
                .like(StringUtils.hasLength(name),EmployeePO::getName,name)
                .orderByDesc(EmployeePO::getUpdateTime);
        super.page(pagePo,queryWrapper);

        //将EmployeePO象转换为EmployeeDTO对象
        List<EmployeeDTO> employeePageVOList = pagePo.getRecords().stream()
                .map(employeePO -> {
                    //封装对象
                    EmployeeDTO dto = new EmployeeDTO();

                    BeanUtils.copyProperties(employeePO,dto);

                    return dto;
                }).collect(Collectors.toList());

        // 将转换后的数据封装到Page<EmployeePageVO>对象中
        Page<EmployeeDTO> employeePageVOPage = new Page<>(Integer.parseInt(page),Integer.parseInt(pageSize),pagePo.getTotal());
        employeePageVOPage.setRecords(employeePageVOList);
        return employeePageVOPage;
    }
}
