package com.wpy.blog.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpy.blog.entity.*;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.util.ResponseUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/blogAdminController")
public class BlogAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private PictureService pictureService;

    /**
     * 跳转到博客管理界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        return "admin/blogManage";
    }

    /**
     * 跳转到博客添加页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        DataGrid dataGrid = blogTypeService.getAllList("1","1000");
        List<BlogType> blogTypeList = (List<BlogType>)dataGrid.getRows();
        model.addAttribute("blogTypeList",blogTypeList);
        return "admin/blogAdd2";
    }

    /**
     * 跳转到博客修改页面
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePage")
    public String updatePage(HttpServletRequest request,HttpServletResponse response,Model model,Integer id) throws Exception{
        Blog blog = blogService.getObjectById(id);
        DataGrid dataGrid = blogTypeService.getAllList("1","1000");
        List<BlogType> blogTypeList = (List<BlogType>)dataGrid.getRows();
        model.addAttribute("blogTitle",blog.getBlogTitle());
        model.addAttribute("blogContent",blog.getBlogContent());
        model.addAttribute("blogTypeList",blogTypeList);
        model.addAttribute("id",id);
        model.addAttribute("blogTypeId",blog.getBlogTypeId());
        return "admin/blogAdd2";
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
        return blogService.getAllList(page,pageSize);
    }

   /* *//**
     * 添加博客
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     *//*
    @RequestMapping("/add")
    @ResponseBody
    public Response add(HttpServletRequest request, HttpServletResponse response, Model model,Blog blog) {

        return blogService.add(blog);


    }

    *//**
     * 修改博客
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/update")
    @ResponseBody
    public Response update(HttpServletRequest request, HttpServletResponse response, Model model, Blog blog) throws Exception {

        return blogService.update(blog);

    }*/

    /**
     * 保存博客
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public Response save(HttpServletRequest request, HttpServletResponse response, Model model, Blog blog) throws Exception {

        //解析首张图片名称，保存到图片表
        String blogInfo = blog.getBlogContent();
        Document doc = Jsoup.parse(blogInfo);
        Elements jpgs = doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片
        //String title = doc.select("img[src$=.jpg]").get(0).attr("title"); //　查找扩展名是jpg的图片
        Integer pictureViewId = null;
        for(int i=0;i<jpgs.size();i++){
            if(i==1){
                break;
            }
            Element jpg=jpgs.get(i);
            //获得图片名
            String jpgTitle = jpg.attr("title");
            String oldPath = jpg.attr("src");
            if(jpgTitle!=null && !("").equals(jpgTitle)){
                //插入图片表
                Picture picture = new Picture();
                picture.setPath(jpgTitle);
                pictureService.add(picture);
                pictureViewId = Integer.valueOf(picture.getId());
            }
            movePictureLocation(request,oldPath,jpgTitle);
            //String jpgString =jpg.toString();
        }


        if (!"".equals(blog.getId())&&blog.getId()!=null){
            blog.setUpdateTime(new Date());
            return blogService.update(blog);
        }else{
            blog.setCreateTime(new Date());
            blog.setArticlePictureViewId(pictureViewId);
            return blogService.add(blog);

        }
       //return blogService.update(blog);

    }


    /**
     * 删除博客
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(HttpServletRequest request, HttpServletResponse response, String ids) {

        return blogService.delete(ids);

    }

    /**
     * 移动图片初始位置到指定位置
     * @param originalLocation 图片初始位置
     * @return
     */
    public  void movePictureLocation  (HttpServletRequest request,String oldPath,String newPath) throws Exception {

        String oldPath1 = oldPath.substring(5);
        String filePath =request.getSession().getServletContext().getRealPath(oldPath1);
        // 封装数据源
        FileInputStream fis = new FileInputStream(filePath);

        String filePath1 =request.getSession().getServletContext().getRealPath("/articlePictureView")+"/"+newPath;

        // 封装目的地
        FileOutputStream fos = new FileOutputStream(filePath1);

        //复制数据
        int by = 0;
        while ((by = fis.read()) != -1){
            fos.write(by);
        }

        //释放资源
        fos.close();
        fis.close();
    }


}
