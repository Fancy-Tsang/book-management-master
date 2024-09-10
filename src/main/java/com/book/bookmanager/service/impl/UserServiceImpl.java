package com.book.bookmanager.service.impl;

import com.book.bookmanager.entity.Borrow;
import com.book.bookmanager.entity.User;
import com.book.bookmanager.enums.BookStatus;
import com.book.bookmanager.enums.BookType;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.BorrowService;
import com.book.bookmanager.service.UserService;
import com.book.bookmanager.util.DaysUtis;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private BorrowService borrowService;

    /**
     * 登录
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public User login(User user) {
        User one = this.lambdaQuery().eq(User::getUsername, user.getUsername())
                .one();
        if (ObjectUtils.isEmpty(one)) throw new BookException("用户不存在");
        if (!one.getPassword().equals(user.getPassword())) throw new BookException("密码错误");
        // 获取当前用户所有未归还的书籍
        List<Borrow> list = borrowService.lambdaQuery()
                .eq(Borrow::getUserId, one.getId())
                .ne(Borrow::getStatus, BookStatus.RETURNED.getCode())
                .eq(Borrow::getType, BookType.BORROW.getCode()).list();
        // 查看是否有逾期书籍
        list.forEach(borrow -> {
            // 计算剩余归还天数
            long disparity = DaysUtis.daysRemaining(borrow.getCreateTime());
            borrow.setDisparity(disparity);
            // 判断是否逾期，逾期的话将账号禁用
            if (disparity < 0 || BookStatus.LATE_RETURN.getCode() == borrow.getStatus()) {
                one.setStatus(0);
                this.baseMapper.updateById(one);
                borrow.setStatus(BookStatus.LATE_RETURN.getCode());
            }
            borrowService.updateById(borrow);
        });
        return one;
    }
}
