package com.book.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookStatus {

    /**
     * 在库
     */
    NOT_RETURNED(1, "未归还"),
    /**
     * 借出
     */
    RETURNED(2, "已归还"),

    /**
     * 逾期归还
     */
    LATE_RETURN(3, "逾期未还")
    ;

    private int code;
    private String desc;

    public static String getDesc(int code){
        for (BookStatus value : BookStatus.values()) {
            if (value.code == code) return value.desc;
        }
        return null;
    }
}
