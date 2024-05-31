package com.nanGuoMM.reggie.backend.domain.employee.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工信息LoginVo
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-22
 */
@Getter
@Setter
@ApiModel(value = "员工表", description = "给前端提供当前会话登录员工信息")
public class EmployeeLoginVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("状态 0:禁用，1:正常")
    private Integer status;
}
