package com.book.bookmanager.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@Data注解用于生成Java Bean所需的所有方法
@NoArgsConstructor
//@NoArgsConstructor注解用于生成无参构造函数，默认生成的构造函数是public权限的。
@AllArgsConstructor
//@AllArgsConstructor注解用于生成全参构造函数，默认生成构造函数的访问权限也是public。注解加在类上时，会为所有字段生成构造函数。
public class BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    protected Long id;
}

