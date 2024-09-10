package com.book.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 借阅(归还)表(Borrow)表实体类
 *
 * @author 12444
 * @since 2024-01-24 10:16:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Borrow extends BaseEntity implements Serializable {


    /**
     * 图书编号
     */
    private Integer bookId;

    /**
     * 借阅(归还)数量
     */
    private Integer num;

    /**
     * 类型(1:借阅，2:归还)
     */
    private String type;

    /**
     * 借阅人
     */
    private Integer userId;

    /**
     * 借阅时间
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 图书状态
     */
    private Integer status;

    /**
     * 剩余天数
     */
    private Long disparity;

}
