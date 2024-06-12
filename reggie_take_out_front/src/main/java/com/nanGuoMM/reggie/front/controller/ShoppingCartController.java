package com.nanGuoMM.reggie.front.controller;

import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.shopping_cart.ShoppingCart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
@Api(tags = "购物车")
public class ShoppingCartController {

    @ApiOperation("查询")
    @GetMapping("/list")
    public Result<List<ShoppingCart>> listShopCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) session.getAttribute("shoppingCart");
        if (shoppingCarts == null) {
            shoppingCarts = new ArrayList<>();
        }
        return Result.success(shoppingCarts);
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public Result<ShoppingCart> addShopCart(HttpServletRequest request,
                                            @ApiParam("新菜品") @RequestBody ShoppingCart shoppingCart) {
        HttpSession session = request.getSession();
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) session.getAttribute("shoppingCart");

        // 如果购物车为空，初始化购物车
        if (shoppingCarts == null) {
            shoppingCarts = new ArrayList<>();
        }

        // 判断是套餐还是菜品，并更新购物车
        boolean isSetmeal = shoppingCart.getSetmealId() != null;
        boolean itemUpdated = updateCartItem(shoppingCarts, shoppingCart, isSetmeal);

        if (!itemUpdated) {
            // 新增购物车项
            shoppingCart.setNumber(1);
            shoppingCarts.add(shoppingCart);
            session.setAttribute("shoppingCart", shoppingCarts);
            return Result.success(shoppingCart);
        }

        session.setAttribute("shoppingCart", shoppingCarts);
        return Result.success(shoppingCart);
    }

    @ApiOperation("清空购物车")
    @DeleteMapping("/clean")
    public Result<String> clearShopCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("shoppingCart", null);
        return Result.success();
    }


    @ApiOperation("减少")
    @PostMapping("/sub")
    public Result<ShoppingCart> reduceShopCart(HttpServletRequest request,@ApiParam("菜品对象") @RequestBody ShoppingCart shoppingCart) {
        HttpSession session = request.getSession();
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) session.getAttribute("shoppingCart");

        // 如果购物车为空，返回错误信息
        if (shoppingCarts == null) {
            return Result.error("购物车为空");
        }

        // 判断是套餐还是菜品，并更新购物车
        boolean isSetmeal = shoppingCart.getSetmealId() != null;
        boolean itemUpdated = reduceCartItem(shoppingCarts, shoppingCart, isSetmeal);

        if (!itemUpdated) {
            return Result.error("购物车中没有该项");
        }

        session.setAttribute("shoppingCart", shoppingCarts);
        return Result.success(shoppingCart);
    }


    private boolean updateCartItem( List<ShoppingCart> shoppingCarts, ShoppingCart shoppingCart, boolean isSetmeal) {
        for (ShoppingCart temp : shoppingCarts) {
            if (isSetmeal && temp.getSetmealId() != null && temp.getSetmealId().equals(shoppingCart.getSetmealId())) {
                int number = temp.getNumber() + 1;
                shoppingCart.setNumber(number);
                temp.setNumber(number);
                return true;
            } else if (!isSetmeal && temp.getDishId() != null && temp.getDishId().equals(shoppingCart.getDishId())) {
                int number = temp.getNumber() + 1;
                shoppingCart.setNumber(number);
                temp.setNumber(number);
                return true;
            }
        }
        return false;
    }

    private boolean reduceCartItem(List<ShoppingCart> shoppingCarts, ShoppingCart shoppingCart, boolean isSetmeal) {
        for (Iterator<ShoppingCart> iterator = shoppingCarts.iterator(); iterator.hasNext(); ) {
            ShoppingCart temp = iterator.next();
            if (isSetmeal && temp.getSetmealId() != null && temp.getSetmealId().equals(shoppingCart.getSetmealId())) {
                int newNumber = temp.getNumber() - 1;
                if (newNumber > 0) {
                    temp.setNumber(newNumber);
                    shoppingCart.setNumber(newNumber);
                } else {
                    iterator.remove();
                }
                return true;
            } else if (!isSetmeal && temp.getDishId() != null && temp.getDishId().equals(shoppingCart.getDishId())) {
                int newNumber = temp.getNumber() - 1;
                if (newNumber > 0) {
                    shoppingCart.setNumber(newNumber);
                    temp.setNumber(newNumber);
                } else {
                    iterator.remove();
                }
                return true;
            }
        }
        return false;
    }
}
