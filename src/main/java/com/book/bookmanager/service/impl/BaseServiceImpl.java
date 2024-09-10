package com.book.bookmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookmanager.entity.BaseEntity;
import com.book.bookmanager.mapper.ICrudMapper;
import com.book.bookmanager.service.BaseService;


public class BaseServiceImpl<T extends BaseEntity> extends ServiceImpl<ICrudMapper<T>, T> implements BaseService<T> {
}

