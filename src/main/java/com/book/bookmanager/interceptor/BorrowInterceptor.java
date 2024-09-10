package com.book.bookmanager.interceptor;


import com.book.bookmanager.entity.Book;
import com.book.bookmanager.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
/*
@Slf4j 是 Lombok 提供的一种注解，用于在类中自动生成一个名为 log 的日志对象。
通过使用 @Slf4j 注解，可以方便地在代码中使用日志功能，而无需手动创建和初始化日志对象。
 */
@Slf4j
public class BorrowInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/borrow")) {
            log.info("拦截请求：{}", requestURI);
            throw new BookException(400, "清先登陆在进行访问");
        }
        return true;
    }
}
