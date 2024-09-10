package com.book.bookmanager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.book.bookmanager.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> extends IService<T> {
}

