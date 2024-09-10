package com.book.bookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.BaseEntity;
import com.book.bookmanager.util.Result;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
/*
HTTP 协议里面，四个表示操作方式的动词：GET 、POST 、PUT、 DELETE。
它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。
 */
@SuppressWarnings("all")
public abstract class BaseController<S extends BaseService<T>, T extends BaseEntity> {

    @Autowired
    protected S service;

    /**
     * 域对象类型
     */
    protected Class<T> entityClass;

    /* 方法注解 */
    @PostMapping("/save")
    @CacheEvict(cacheNames = "bookCache",key = "#entity.id")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result save(@RequestBody T entity) {
        saveBefore(entity);
        if (!service.save(entity)) throw new BookException("保存失败");
        try {
            // 执⾏了异常代码(0不能做除数)
            int i = 10 / 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // ⼿动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        saveAfter(entity);
        return Result.success(entity);
    }


    /**
     * 前置数据的操作
     *
     * @param entity
     */
    public void saveBefore(T entity) {

    }

    /**
     * 后置数据的操作
     *
     * @param entity
     */
    public void saveAfter(T entity) {

    }


//    @DeleteMapping("/delete")
//    @CacheEvict(cacheNames = "bookCache")
//    public Result delete(T entity) {
//        boolean remove = service.removeById(entity);
//        if (!remove) throw new BookException("删除失败");
//        return Result.success(remove);
//    }

    @DeleteMapping("/batchDelete")
    public void batchDelete(@RequestParam("ids") ArrayList<Integer> ids) {
        if (!service.removeByIds(ids)) throw new BookException("删除失败");
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody T entity) {
        updateBefore(entity);
        if (!service.updateById(entity)) {
            throw new BookException();
        }
        updateAfter(entity);
        return Result.success(entity);
    }

    /**
     * 前置数据修改的操作
     *
     * @param entity
     */
    public void updateBefore(T entity) {

    }

    /**
     * 修改后置数据的操作
     *
     * @param entity
     */
    public void updateAfter(T entity) {

    }

    @GetMapping("/page")
    public Result page(Page<T> page) {
        return Result.success(service.page(page));
    }

    @GetMapping("/getOne")
    public Result getOne(String id) {
        return Result.success(service.getById(id));
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(service.list());
    }
}

