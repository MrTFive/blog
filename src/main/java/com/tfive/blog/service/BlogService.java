package com.tfive.blog.service;


import com.tfive.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getAndConvert(Long id);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable,Blog blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,Long typeId);

    List<Blog> listRecommendBlogs(Integer size);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);


}
