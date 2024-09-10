package com.book.bookmanager.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookType {

    BORROW(1, "借阅"),
    RETURN(2, "归还");

    private Integer code;
    private String message;

}
