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
@ApiModel(value = "注册form",description = "用户注册DTO")
public class UserRegisterDTO {
    @ApiModelProperty("邮箱")
    String mail;
    @ApiModelProperty("密码")
    String password;
    @ApiModelProperty("验证码")
    String code;
}
