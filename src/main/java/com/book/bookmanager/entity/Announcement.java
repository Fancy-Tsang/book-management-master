package com.book.bookmanager.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Announcement)表实体类
 *
 * @author 12444
 * @since 2024-03-11 11:38:20
 */
/*把原本在内存中的对象状态 变成可存储或传输的过程称之为序列化。
序列化之后，就可以把序列化后的内容写入磁盘，或者通过网络传输到别的机器上。

        反序列化则是将可存储或传输的资源变成对象状态。

        序列化只需要使自定义类实现接口，即 implements Serializable*/
@Data
public class Announcement extends BaseEntity implements Serializable {


    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
