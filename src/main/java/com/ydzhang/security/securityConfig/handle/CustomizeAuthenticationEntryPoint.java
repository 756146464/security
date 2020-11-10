package com.ydzhang.security.securityConfig.handle;

import com.alibaba.fastjson.JSON;
import com.ydzhang.security.config.JsonResult;
import com.ydzhang.security.config.ResultCode;
import com.ydzhang.security.config.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Hutengfei
 * @Description: 匿名用户访问无权限资源时的异常
 * @Date Create in 2019/9/3 21:35
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JsonResult jsonResult = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("test/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
