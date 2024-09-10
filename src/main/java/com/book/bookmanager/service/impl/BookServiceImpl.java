package com.book.bookmanager.service.impl;

import com.book.bookmanager.entity.Book;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.mapper.BookMapper;
import com.book.bookmanager.service.BookService;
import com.book.bookmanager.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    @Resource
    private BookMapper bookMapper;

        @Value("${file.upload.path}")
        private String uploadPath;

        @Override
        public int deleteById(String id){
            return this.bookMapper.deleteById(id);
        }

        @Override
        public List<Book> getOne(String id) {
            return this.lambdaQuery()
                    .eq(Book::getId, id)
                    .orderByAsc(Book::getClickNum)
                    .list();
        }

    @Override

    public List<Book> getByTagId(Integer tagId, boolean more) {
        return this.lambdaQuery()
                .eq(Book::getTagId, tagId)
                .orderByAsc(Book::getClickNum)
                .last(more, "limit 5").list();
    }

    @Override
    public List<Book> hot() {
        return this.lambdaQuery().orderByDesc(Book::getClickNum).last("limit 6").list();
    }

    @Override
    public List<Book> search(String search) {
        return this.lambdaQuery().like(Book::getName, search).list();
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public Result upload(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(uploadPath + fileName));
                return Result.success(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BookException(500, "上传失败");
            }
        } else {
            return Result.fail("上传失败");
        }
    }
}
