package com.book.bookmanager.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Recommend)表实体类
 *
 * @author 12444
 * @since 2024-03-08 14:30:27
 */
@Data
public class Recommend extends BaseEntity implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
