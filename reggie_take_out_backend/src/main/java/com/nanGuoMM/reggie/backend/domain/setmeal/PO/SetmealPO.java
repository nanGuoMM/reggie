package com.nanGuoMM.reggie.backend.domain.setmeal.PO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Getter
@Setter
@TableName("setmeal")
public class SetmealPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 菜品分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 套餐价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 状态 0:停用 1:启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
