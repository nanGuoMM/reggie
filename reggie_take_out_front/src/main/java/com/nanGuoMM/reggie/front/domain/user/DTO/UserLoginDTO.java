package com.nanGuoMM.reggie.front.domain.user.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-27
 */
@Getter
@Setter
@ApiModel(value = "登录form",description = "用户登录DTO")
public class UserLoginDTO {

    @ApiModelProperty("邮箱")
    private String mail;
    @ApiModelProperty("密码")
    private String password;
}
