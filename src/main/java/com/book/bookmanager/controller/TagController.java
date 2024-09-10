package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Tag;
import com.book.bookmanager.service.TagService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("tag")
public class TagController extends BaseController<TagService, Tag> {


}

