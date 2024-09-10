package com.book.bookmanager.service;

import com.book.bookmanager.entity.User;

public interface UserService extends BaseService<User> {

    User login(User user);
}
