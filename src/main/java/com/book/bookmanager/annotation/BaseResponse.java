package com.book.bookmanager.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//在上面的代码中，使用 @Retention(RetentionPolicy.RUNTIME) 指定 BaseResponse 注解在运行时保留。
public @interface BaseResponse {
}
/*
在这个例子中，@BaseResponse 注解可以应用到类、接口、枚举上，因为 ElementType.TYPE 被包含在 @Target 的元素类型数组中。
 */
