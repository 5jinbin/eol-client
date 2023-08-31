package com.jinbin.eolclient.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jinbin.eolclient.util.JwtHelper;
import com.jinbin.eolclient.util.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final int errStatusCode = 886;
    private final String errTips = "未登录或登录状态过期,请先登录!";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("Authorization");

        // 如果token为空，或者已经过期，则返回错误响应
        if(StringUtils.isBlank(token) || JwtHelper.isExpiration(token)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.fail().code(errStatusCode).message(errTips)));
            return false;
        }

        // 验证token的合法性
        try {
            Integer userId = JwtHelper.getUserId(token);
            request.setAttribute("userId", userId);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.fail().code(errStatusCode).message(errTips)));
            return false;
        }

        return true;
    }
}
