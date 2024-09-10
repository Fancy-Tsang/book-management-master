package com.book.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评论表(Comment)表实体类
 *
 * @author 12444
 * @since 2024-01-24 10:16:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity implements Serializable {


    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人
     */
    private Long userId;

    /**
     * 评论时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
