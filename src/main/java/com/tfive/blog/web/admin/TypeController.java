package com.tfive.blog.web.admin;

import com.tfive.blog.po.Type;
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


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //显示分类并分页
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    //新增页面
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //编辑
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable  Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //新增分类
    @PostMapping("/type/input")
    public String typeInput(Type type, RedirectAttributes attributes){ //name属性自动封装到Type对象中
        //判断重复
        if (typeService.getTypeByName(type.getName()) != null){
            attributes.addFlashAttribute("message","新增失败 分类重复");
        }else{
            Type t = typeService.saveType(type);
            if (t == null){
                attributes.addFlashAttribute("message","新增失败");
            }else{
                attributes.addFlashAttribute("message","新增成功");
            }
        }
        return "redirect:/admin/types";//这里不要直接返回页面,因为这样不会经过方法查询
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
