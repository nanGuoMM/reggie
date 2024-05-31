package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.shopping_cart.ShoppingCart;
import com.nanGuoMM.reggie.front.service.IShoppingCartService;
import com.nanGuoMM.reggie.front.utils.BaseContext;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @ApiOperation("查询")
    @GetMapping("/list")
    public Result<List<ShoppingCart>> listShopCart() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getUserId,BaseContext.getCurrentId()).orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(queryWrapper);
        return Result.success(shoppingCarts);
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public Result<Object> addShopCart(@RequestBody ShoppingCart shoppingCart){
        //设置用户id
        shoppingCart.setUserId(BaseContext.getCurrentId());
        //构建查询条件
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,shoppingCart.getUserId());

        //判断添加到购物车的是套餐还是菜品
        if(shoppingCart.getSetmealId() != null) {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        } else {
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }
        //查询当前菜品或套餐是否已经在购物车
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        if(cartServiceOne == null) {
            //第一次添加，数量设为1
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        } else {
            //直接+1
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }
        return Result.success(cartServiceOne);
    }

    @ApiOperation("清空")
    @DeleteMapping("/clean")
    public Result<Object> cleanCart() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);
        return Result.success();
    }

    @ApiOperation("减少")
    @PostMapping("/sub")
    public Result<ShoppingCart> subCart(@RequestBody Map<String,Long> id) {
        //查询
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        //判断
        if (id.get("dishId") != null) {
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId,id.get("dishId"));
        } else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,id.get("setmealId"));
        }
        ShoppingCart shoppingCartServiceOne = shoppingCartService.getOne(queryWrapper);
        //修改
        Integer number = shoppingCartServiceOne.getNumber();
        shoppingCartServiceOne.setNumber(number - 1);
        if(shoppingCartServiceOne.getNumber() != 0) {
            //更新
            shoppingCartService.updateById(shoppingCartServiceOne);
            return Result.success(shoppingCartServiceOne);
        }
        //删除
        shoppingCartService.removeById(shoppingCartServiceOne);
        return Result.success(shoppingCartServiceOne);
    }
}
