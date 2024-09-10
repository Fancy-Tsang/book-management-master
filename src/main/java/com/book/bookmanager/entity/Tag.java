package com.book.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分类表(Tag)表实体类
 *
 * @author 12444
 * @since 2024-01-24 10:16:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseEntity implements Serializable {


    /**
     * 分类名称
     */
    private String name;

}
