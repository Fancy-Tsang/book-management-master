package com.book.bookmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowDto {
    private Long id;
    private Long bookId;
    private String author;
    private String imgUrl;
    private String name;
    private Integer num;
    private String user;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    private String status;
    private long residue;
}
/*
DTO(data transfer object) 数据传输对象，Service 或 Manager 向外传输的对象。（阿里开发手册里的定义）
DTO只传输页面上需要显示的字段，不把整个PO对象传递给客户端，提高数据的传输速度
 */