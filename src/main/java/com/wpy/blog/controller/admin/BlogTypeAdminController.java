package com.wpy.blog.controller.admin;

import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/blogTypeAdminController")
public class BlogTypeAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    /**
     * 跳转到博客类型管理界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        return "admin/blogTypeManage";
    }

    

    /**
     * 填充数据表格
     * @param request
     * @param response
     * @param model
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getAllList")
    public DataGrid getAllList(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String pageSize) throws Exception {
        return blogTypeService.getAllList(page,pageSize);
    }

    /**
     * 添加博客类型
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Response add(HttpServletRequest request, HttpServletResponse response, Model model,BlogType blogType) {

        return blogTypeService.add(blogType);


    }

    /**
     * 修改博客类型
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    @ResponseBody
    public Response update(HttpServletRequest request, HttpServletResponse response, Model model, BlogType blogType) throws Exception {

        return blogTypeService.update(blogType);

    }

    @RequestMapping("/save")
    @ResponseBody
    public Response save(HttpServletRequest request, HttpServletResponse response, Model model, BlogType blogType) throws Exception {

        if (!"".equals(blogType.getId())&&blogType.getId()!=null){
            return blogTypeService.update(blogType);
        }else{
            return blogTypeService.add(blogType);

        }

        //return null;

    }


    /**
     * 删除博客类型
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(HttpServletRequest request, HttpServletResponse response, String ids) {

        return blogTypeService.delete(ids);

    }


}
