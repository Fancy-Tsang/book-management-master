package com.book.bookmanager.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
@controller、@Service、@Configuration、@Repository这四个注解其实都是@Component派生注解，
点开源码就会发现，全是使用的@Component注解自定义的。
其实spring整这几个注解就是为了方便我们分层，controller层用@controller，service用@Service，仅此而已！
 */
@Configuration
public class MybatisPlusConfiguration {

    /*
    @Bean用于将对象存入spring的ioc容器中，同@controller、@Service、@Component、@Configuration、@Repository等几个注解是一样的，都是负责将对象存入容器当中。
    只不过方式不同，他们是用在类上面的，然后将当前类通过无参构造函数创建对象然后放入容器，
    而@Bean是用在方法上，将当前方法的返回值对象放到容器当中！
    可以理解为前者是由spring自动创建对象，而@Bean创建对象是交给我们自己来控制。
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
//    paginationInterceptor: 配置分页插件
}
