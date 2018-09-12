package com.tfive.blog.web;

import com.tfive.blog.po.Blog;
import com.tfive.blog.service.BlogService;
import com.tfive.blog.service.TagService;
import com.tfive.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    //返回index页面
    @GetMapping("/")
    public String index(@PageableDefault(size = 6,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTags());
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogs(8));
        return "index";
    }

    //博客具体页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getAndConvert(id);
        blog.setViews(blog.getViews()+1);
        blogService.saveBlog(blog);
        model.addAttribute("blog",blog);
        return "blog";
    }

}




