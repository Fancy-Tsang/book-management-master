package com.book.bookmanager.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BookException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public BookException() {
        super();
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
