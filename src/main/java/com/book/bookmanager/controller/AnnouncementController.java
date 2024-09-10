package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Announcement;
import com.book.bookmanager.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("announcement")
/*@RequestMapping 注解可以在控制器类的级别和/或其中的方法的级别上使用。

在类的级别上的注解会将一个特定请求或者请求模式映射到一个控制器之上。
之后你还可以另外添加方法级别的注解来进一步指定到处理方法的映射关系。
如代码所示，到 /announcement 的请求会由 saveBefore() 方法来处理*/
public class AnnouncementController extends BaseController<AnnouncementService, Announcement> {

    @Override
    public void saveBefore(Announcement entity) {
        entity.setCreateTime(LocalDateTime.now());
    }
}

