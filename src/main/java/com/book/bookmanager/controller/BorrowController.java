package com.book.bookmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.Borrow;
import com.book.bookmanager.service.BorrowService;
import com.book.bookmanager.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("borrow")
public class BorrowController extends BaseController<BorrowService, Borrow> {

    @Resource
    private BorrowService borrowService;


    /**
     * 用户查询借阅记录
     *
     * @param page   分页数据
     * @param userId 用户id
     * @param type   借阅或是归还
     * @return 分页数据
     */
    @GetMapping(value = {"/page/{type}/{userId}", "/page/{type}"})
    public Result page(Page<Borrow> page,
                             @PathVariable(value = "userId", required = false) String userId,
                             @PathVariable("type") int type) {
        return Result.success(borrowService.getListByUser(page, userId, type));
    }

    /**
     * 用户借阅图书
     *
     * @param borrow 借阅信息
     * @return 是否成功
     */
    @PostMapping("/borrow")
    public Result borrow(@RequestBody Borrow borrow) {
        return Result.success(borrowService.borrow(borrow));
    }


    /**
     * 用户归还图书
     *
     * @param borrow 归还信息
     * @return 是否成功
     */
    @PostMapping("/return")
    public Result returnBook(@RequestBody Borrow borrow) {
        return Result.success(borrowService.returnBook(borrow));
    }



}

