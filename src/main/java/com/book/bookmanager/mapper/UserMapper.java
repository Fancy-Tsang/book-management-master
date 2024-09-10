package com.book.bookmanager.mapper;

import com.book.bookmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
//Mapper继承该接口(BaseMapper<T>)后，无需编写 mapper.xml 文件，即可获得CRUD功能
@Mapper
public interface UserMapper extends ICrudMapper<User> {

}



