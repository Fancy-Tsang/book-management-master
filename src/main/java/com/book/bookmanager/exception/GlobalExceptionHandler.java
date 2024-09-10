package com.book.bookmanager.exception;

import com.book.bookmanager.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("未知异常！原因是:", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = BookException.class)
    public Result bookExceptionHandler(BookException e) {
        log.error("自定义异常！原因是:", e);
        if (e.getCode() != null) return Result.fail(e.getCode(), e.getMessage());
        return Result.fail(e.getMessage());
    }
}
