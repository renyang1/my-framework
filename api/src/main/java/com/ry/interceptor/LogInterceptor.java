package com.ry.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ry.annotation.LogParam;
import com.ry.entity.Orders;
import com.ry.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Description: 日志拦截器
 *
 * @author renyang
 * @date 2020-09-20
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private OrderService orderService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        // 获取接口方法上的注解
        LogParam logParam = hm.getMethodAnnotation(LogParam.class);
        if (logParam != null) {
            // 获取接口请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            log.info("接口{}:请求参数为{}", request.getRequestURI(), JSONObject.toJSONString(parameterMap));
        }

        Orders orders = orderService.queryOrder("190827F4AK12R30H");
        System.out.println(orders);
        return true;
    }
}
