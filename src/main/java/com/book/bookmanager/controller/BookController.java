package com.book.bookmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.Book;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.BookService;
import com.book.bookmanager.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//@CrossOrigin
@RestController
/*
@RestController结合了@Controller和@ResponseBody注解的功能，
既可以处理HTTP请求，又可以将方法的返回值直接序列化为响应数据。
*/
/*
1. 处理请求：被@Controller注解标记的类中可以定义多个方法，每个方法用于处理不同的HTTP请求。
这些方法被称为控制器方法(controller method)或处理器方法(handler method)。
它们通常被使用@RequestMapping等注解来标识请求的URL路径和请求方法，以指定由哪个控制器方法来处理特定的请求。
2. 生成响应：控制器方法通常返回一个视图(View)或一个包含数据模型的模型(Model)作为响应。
视图决定了生成响应时要使用的模板以及模型数据的填充方式。而模型包含了要呈现给视图的数据。
3. 处理业务逻辑：控制器类可以包含业务逻辑的处理，例如调用服务(Service)层的方法来处理请求，并对数据进行处理、封装和验证。
*/
@RequestMapping("book")
@Api(tags = "C端-图书浏览接口")
public class BookController extends BaseController<BookService, Book> {

    @Resource
    private BookService bookService;

    @Resource
    private RedisTemplate redisTemplate;

    /*
    @GetMapping注解可以用于类和方法上，用于定义HTTP GET请求的URL路径。
    当客户端发送HTTP GET请求时，Spring Boot会自动将请求映射到具有相应URL路径的控制器方法上。
    */
    @GetMapping("findPage")
    public Result page(Page<Book> page, String search) {
        return Result.success(service.lambdaQuery()
                .like(StringUtils.hasLength(search), Book::getName, search)
                .page(page));
    }

    @Override
    @GetMapping("/getOne")
    @Cacheable(cacheNames = "bookCache",key = "#id") //key: bookCache::id
    @ApiOperation("根据Id查询图书")
    public Result getOne(@RequestParam(value = "id", required = false) String id) {
        List<Book> list = bookService.getOne(id);
        if(list == null){
            redisTemplate.opsForValue().set(id,"",2);
            return Result.fail("此图书id不存在！"); //缓存穿透的解决办法之一
        }
        return Result.success(list);
    }

    @DeleteMapping("/delete")
    @CacheEvict(cacheNames = "bookCache",key = "#id")
    @ApiOperation("根据Id删除图书")
    @Transactional
    public Result deleteById(@RequestParam(value = "id", required = false) String id) {
        int remove = bookService.deleteById(id);
        int i = 10/0;
        if (remove==0) {
            throw new BookException("删除失败");
        }
        return Result.success(remove);
    }

    @GetMapping("getByTagId")
    @ApiOperation("根据tagId查询图书")
    public Result getByTagId(@RequestParam(value = "tagId", required = false) Integer tagId, @RequestParam(required = false, defaultValue = "true")boolean more) {
        List<Book> list = (List<Book>) redisTemplate.opsForValue().get(tagId);
        if (list != null && list.size() > 0){
            return Result.success(list);
        }
        list = bookService.getByTagId(tagId, more);
        if(list == null){
            redisTemplate.opsForValue().set(tagId,"",2);
            return Result.fail("此tagId不存在！"); //缓存穿透的解决办法之一
        }
        redisTemplate.opsForValue().set(tagId,list);
        return Result.success(list);
    }

    @GetMapping("hot")
    public Result hot() {
        return Result.success(bookService.hot());
    }

    @GetMapping("search")
    public Result search(@RequestParam(value = "search", required = false) String search) {
        return Result.success(bookService.search(search));
    }

    /**
     * 文件上传
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam(value = "file", required = false) MultipartFile file){
        return bookService.upload(file);
    }

    @Override
    public void saveBefore(Book entity) {
        entity.setClickNum(0);
        entity.setCreateTime(LocalDateTime.now());
    }
}
