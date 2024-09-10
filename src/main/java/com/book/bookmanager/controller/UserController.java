package com.book.bookmanager.controller;


import com.book.bookmanager.entity.User;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.UserService;
import com.book.bookmanager.util.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {

    @Resource
    private UserService userService;
/*
    @PostMapping 注解的原理
@PostMapping 注解是由 Spring Boot 提供的一个组合注解，它包含了 @RequestMapping 和 @ResponseBody 注解。
其中，@RequestMapping 注解用来声明请求的路径和请求方法，@ResponseBody 注解用来告诉 Spring Boot，返回值需要转换为 JSON 或 XML 格式。
*/

/*
在下面的示例中，我们使用 @PostMapping 注解声明了一个方法 login()，这个方法用来处理客户端发送的 POST 请求，并将请求体中的数据转换为 User 对象。

在这个示例中，我们使用了 @RequestBody 注解来获取请求体中的数据，并将它转换为 User 对象。另外，我们还返回了一个 Result 对象，它会被自动转换为 JSON 或 XML 格式，返回给客户端。
*/
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return Result.success(userService.login(user));
    }

    @Override
    public void saveBefore(User entity) {
        User user = userService.lambdaQuery()
                .eq(StringUtils.hasLength(entity.getUsername()),User::getUsername, entity.getUsername())
                .eq(StringUtils.hasLength(entity.getTelephone()),User::getTelephone, entity.getTelephone()).one();
        if (user != null) throw new BookException("该用户或手机号已被使用");
        entity.setRegTime(LocalDateTime.now());
    }


}

