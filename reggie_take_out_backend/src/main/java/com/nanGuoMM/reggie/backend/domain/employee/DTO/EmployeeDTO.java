package com.nanGuoMM.reggie.backend.domain.employee.DTO;

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
@ApiModel(value = "员工表", description = "员工DTO")
public class EmployeeDTO  {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("状态 0:禁用，1:正常")
    private Integer status;
}
