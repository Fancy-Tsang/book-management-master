package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Recommend;
import com.book.bookmanager.service.RecommendService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("recommend")
public class RecommendController extends BaseController<RecommendService, Recommend> {
    @Override
    public void saveBefore(Recommend entity) {
        entity.setCreateTime(LocalDateTime.now());
    }
}

