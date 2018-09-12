package com.tfive.blog.web.admin;

import com.tfive.blog.po.Blog;
import com.tfive.blog.po.Type;
import com.tfive.blog.po.User;
import com.tfive.blog.service.BlogService;
import com.tfive.blog.service.TagService;
import com.tfive.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    //访问博客管理页面
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Blog blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }

    //搜索功能
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                Pageable pageable, Blog blog, Model model){

        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";   //返回片段
    }

    //新增页面
    @GetMapping("/blogs/input")
    public String blogInput(Model model){
        model.addAttribute("tags",tagService.listTags());
        model.addAttribute("types",typeService.listType());
        Blog blog = new Blog();
        blog.setType(new Type());
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //发布页面
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTags(blog.getTagIds()));


        if( blogService.saveBlog(blog) == null)
            attributes.addFlashAttribute("message","操作失败");
        else
            attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }

    //编辑博客
    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagService.listTags());
        model.addAttribute("types",typeService.listType());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

}
