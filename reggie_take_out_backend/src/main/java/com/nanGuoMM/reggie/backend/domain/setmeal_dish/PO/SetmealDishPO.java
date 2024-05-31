package com.nanGuoMM.reggie.backend.domain.setmeal_dish.PO;

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
 * 套餐菜品关系
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-26
 */
@Getter
@Setter
@TableName("setmeal_dish")
public class SetmealDishPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 套餐id 
     */
    @TableField("setmeal_id")
    private String setmealId;

    /**
     * 菜品id
     */
    @TableField("dish_id")
    private String dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @TableField("name")
    private String name;

    /**
     * 菜品原价（冗余字段）
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 份数
     */
    @TableField("copies")
    private Integer copies;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

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
