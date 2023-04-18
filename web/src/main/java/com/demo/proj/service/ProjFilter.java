package com.demo.proj.service;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@jakarta.servlet.annotation.WebFilter("/*")
public class ProjFilter implements jakarta.servlet.Filter {
    @Override
    public  void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter start");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter work");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        System.out.println(url);

        if (url.contains("login") || url.contains("register")) {
            //no action
        } else {
            //verify token
            String jwt=request.getHeader("token");
            if (!StringUtils.hasLength(jwt)) {
                log.info("token为空");
                Result error= Result.fail(Result.getError(-15));
                String NotLogin=JSONObject.toJSONString(error);
                response.getWriter().write(NotLogin);
                return;
            }


            Result result = JWTUtil.verifyToken(jwt);
            log.info(jwt);
            if (result.getCode() == -1) {
                Result error= Result.fail(Result.getError(-10));
                String Fail=JSONObject.toJSONString(error);
                response.getWriter().write(Fail);
                return;
                }
            }
        filterChain.doFilter(request,response);
    }

    @Override
    public  void destroy() {
        log.info("Filter end");
        Filter.super.destroy();
    }
}
