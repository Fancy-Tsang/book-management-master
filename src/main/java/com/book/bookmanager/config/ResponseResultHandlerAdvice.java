package com.book.bookmanager.config;

import com.book.bookmanager.annotation.BaseResponse;
import com.book.bookmanager.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;


/**
 * 统一响应体处理器
 *
 * @author NULL
 * @date 2019-07-16
 */
/*
@RestControllerAdvice作用
当我们在类上使用@RestControllerAdvice注解时，它相当于同时使用了@ControllerAdvice和@ResponseBody。
这意味着被@RestControllerAdvice注解标记的类将被视为全局异常处理器，并且异常处理方法的返回值将以JSON格式直接写入响应体中。

通过在@RestControllerAdvice类中定义异常处理方法，我们可以捕获和处理控制器中抛出的异常，提供自定义的异常处理逻辑，以及返回适当的响应给客户端。
这样可以统一处理应用程序中的异常情况，提高代码的可维护性和可读性。
 */
    /*
自定义类实现 ResponseBodyAdvice 接口，然后在类上标记 @ControllerAdvice 或@RestControllerAdvice 注解即可自动识别并进行功能增强。

ResponseBodyAdvice 接口允许在执行 @ResponseBody 或 ResponseEntity 控制器方法之后，
但在使用 HttpMessageConverter 写入响应体之前自定义响应，进行功能增强。通常用于 加密，签名，统一数据格式等。
     */
@RestControllerAdvice(annotations = BaseResponse.class)
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper om;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if ((body instanceof String) && !MediaType.APPLICATION_XML_VALUE.equals(selectedContentType.toString())) {
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(Result.success(body));
        }

        if (Objects.isNull(body) && MediaType.TEXT_HTML_VALUE.equals(selectedContentType.toString())) {
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(Result.success(body));
        }

        // 非JSON格式body直接返回即可
        return Result.success(body);
    }
}

