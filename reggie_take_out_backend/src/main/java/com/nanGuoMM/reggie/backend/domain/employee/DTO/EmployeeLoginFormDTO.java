package com.nanGuoMM.reggie.backend.domain.employee.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工信息
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Getter
@Setter
@ApiModel(value = "员工表", description = "接收前端员工登录信息")
public class EmployeeLoginFormDTO {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}
