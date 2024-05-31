package com.nanGuoMM.reggie.front.domain.user.VO;

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
@ApiModel(value = "登录form",description = "给前端响应登录信息")
public class UserVO {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("邮箱")
    private String mail;
}
