package com.book.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.dto.BorrowDto;
import com.book.bookmanager.entity.Book;
import com.book.bookmanager.entity.Borrow;
import com.book.bookmanager.entity.User;
import com.book.bookmanager.enums.BookStatus;
import com.book.bookmanager.enums.BookType;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.mapper.BookMapper;
import com.book.bookmanager.mapper.UserMapper;
import com.book.bookmanager.service.BorrowService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class BorrowServiceImpl extends BaseServiceImpl<Borrow> implements BorrowService {

//    @Resource注解主要用于实现依赖注入。可以用在字段、setter方法和构造函数上。
//    它可以将一个类所需要的依赖对象自动注入到该类中，从而避免了手动创建和配置对象的繁琐过程。
//    通过@Resource注解，我们可以将注意力更多地集中在业务逻辑的实现上，而不是在对象的创建和配置上。
//    当用在字段上时，容器会自动为标注了该注解的字段注入相应的依赖对象。
//    当使用省略了name属性的@Resource修饰实例变量时，name属性值默认与该实例变量同名。
    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserMapper userMapper;

    /**
     *  图书借阅
     * @param borrow
     * @return
     */
    @Override
    public Book borrow(Borrow borrow) {
        // 根据用户id查询用户是否被禁用
        User user = userMapper.selectById(borrow.getUserId());
        if (user.getStatus() == 0) {
            throw new BookException("当前用户已经被禁用！");
        }
        Book book = bookMapper.selectById(borrow.getBookId());
        // 判断当前图书的数量是否大于0
        if (book.getNum() <= 0) {
            throw new BookException("当前图书已经被借完了！");
        }
        // 查询当前用户是否借阅过这本书
        Borrow one = this.lambdaQuery()
                .eq(Borrow::getUserId, borrow.getUserId())
                .eq(Borrow::getBookId, borrow.getBookId())
                .eq(Borrow::getStatus, BookStatus.NOT_RETURNED.getCode())
                .one();
        if (one != null) {
            this.updateById(one); // 更新借阅表信息
            return book;
        }
        // 设置借阅状态，借阅时间，并进行库存减一
        borrow.setType(String.valueOf(BookType.BORROW.getCode()));
        borrow.setStatus(BookStatus.NOT_RETURNED.getCode());
        borrow.setCreateTime(LocalDateTime.now());
        boolean save = this.save(borrow);
        if (save) {
            book.setNum(book.getNum() - 1);
            bookMapper.updateById(book);
        }
        return book;
    }

    /**
     *  图书归还
     */
    @Override
    public Book returnBook(Borrow borrow) {
        Book book = bookMapper.selectById(borrow.getBookId());
        // 查询当前用户是否借阅过这本书
        Borrow one = this.lambdaQuery()
                .eq(Borrow::getUserId, borrow.getUserId())
                .eq(Borrow::getBookId, borrow.getBookId())
                .eq(Borrow::getStatus, BookStatus.NOT_RETURNED.getCode())
                .one();
        if (one == null) one = this.lambdaQuery()
                .eq(Borrow::getUserId, borrow.getUserId())
                .eq(Borrow::getBookId, borrow.getBookId())
                .eq(Borrow::getStatus, BookStatus.LATE_RETURN.getCode())
                .one();
        if (one == null)    throw new BookException("当前用户没有借阅过这本书！");
        // 计算是否逾期
        LocalDateTime now = LocalDateTime.now();
        long between = ChronoUnit.DAYS.between(now, one.getCreateTime());
        borrow.setType(String.valueOf(BookType.RETURN.getCode()));
        borrow.setStatus(BookStatus.RETURNED.getCode());
        borrow.setCreateTime(now);
        if (between > 30) {
            borrow.setStatus(BookStatus.LATE_RETURN.getCode());
        }
        boolean save = this.save(borrow);
        if (save) {
            one.setStatus(BookStatus.RETURNED.getCode());
            this.updateById(one);
            book.setNum(book.getNum() + 1);
            bookMapper.updateById(book);
        }
        return book;
    }

    /**
     *  查询用户借阅信息
     * @param page 分页对象
     * @param userId 用户id
     * @param type 归还或借阅
     * @return 分页对象
     */
    @Override
    public Page<BorrowDto> getListByUser(Page<Borrow> page, String userId, int type) {
        Page<Borrow> borrowPage = this.lambdaQuery()
                .eq(StringUtils.hasLength(userId), Borrow::getUserId, userId)
                .ne(type != BookStatus.RETURNED.getCode(), Borrow::getStatus, BookStatus.RETURNED.getCode())
                .eq(Borrow::getType, type).page(page);
        List<BorrowDto> borrowDtos = new ArrayList<>();
        for (Borrow borrow : borrowPage.getRecords()) {
            BorrowDto borrowDto = new BorrowDto();
            Book book = bookMapper.selectById(borrow.getBookId());
            User user = userMapper.selectById(borrow.getUserId());
            BeanUtils.copyProperties(book, borrowDto);
            BeanUtils.copyProperties(borrow, borrowDto);
            borrowDto.setUser(user.getUsername());
            borrowDto.setUserId(user.getId());
            borrowDto.setBookId(book.getId());
            borrowDto.setStatus(BookStatus.getDesc(borrow.getStatus()));
            borrowDtos.add(borrowDto);
        }
        Page<BorrowDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(borrowPage, dtoPage);
        dtoPage.setRecords(borrowDtos);
        return dtoPage;
    }
}
