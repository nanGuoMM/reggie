package com.nanGuoMM.reggie.front.domain.address_book.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 地址管理
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@Getter
@Setter
@ApiModel("地址表")
public class AddressBookDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("性别 0 女 1 男")
    private Byte sex;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("详细地址")
    private String detail;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("默认 0 否 1是")
    private boolean isDefault;
}
