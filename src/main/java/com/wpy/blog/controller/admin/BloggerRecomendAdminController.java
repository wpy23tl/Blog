package com.wpy.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.BlogTypeService;
import com.wpy.blog.service.BloggerRecommendService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 博主推荐
 */
@Controller
@RequestMapping("/bloggerRecommendAdminController")
public class BloggerRecomendAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    @Resource
    private BloggerRecommendService bloggerRecommendService;

    /**
     * 跳转到博主推荐管理界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        return "admin/bloggerRecommend";
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
        DataGrid dataGrid = bloggerRecommendService.getAllList("1","10");
        return dataGrid;
    }

    /**
     * 添加博主推荐博客
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Response add(HttpServletRequest request, HttpServletResponse response, Model model,BlogType blogType,String ids) {


        return bloggerRecommendService.add(ids);


    }

    /**
     * 修改博主推荐博客
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    @ResponseBody
    public Response update(HttpServletRequest request, HttpServletResponse response, Model model, BlogType blogType,String id,String recommendNo) throws Exception {

        Map<String, Object> map= new HashMap<>();
        Blog blog = blogService.getObjectById(Integer.valueOf(id));
        blog.setRecommendNo(Integer.valueOf(recommendNo));
        bloggerRecommendService.update(blog);

        return null;

    }



    /**
     * 删除博主推荐博客
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(HttpServletRequest request, HttpServletResponse response, String ids) {


        return  bloggerRecommendService.delete(ids);

    }


}
