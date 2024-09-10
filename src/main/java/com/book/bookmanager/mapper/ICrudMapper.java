package com.book.bookmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.bookmanager.entity.BaseEntity;
//BaseMapper是MyBatis-Plus提供的模板mapper，其中包含了基本的CRUD方法，泛型为操作的实体类型
public interface ICrudMapper<T extends BaseEntity> extends BaseMapper<T> {
}
