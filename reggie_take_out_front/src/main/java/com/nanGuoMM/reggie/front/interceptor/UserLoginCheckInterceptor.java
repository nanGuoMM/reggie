package com.nanGuoMM.reggie.front.interceptor;

import com.alibaba.fastjson.JSON;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserLoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("user");
        if ( userId != null) {
            log.info("用户已登录，拦截器放行-----------------");
            //给当前用户Servlet线程设置值
            BaseContext.setCurrentId(userId);
            return true;
        }

        log.info("用户未登录，拦截-------------------");
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return false;
    }
}
