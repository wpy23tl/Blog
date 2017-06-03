package com.wpy.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.BlogTypeService;
import com.wpy.blog.service.FirstPageBannerSettingService;
import com.wpy.blog.service.PictureService;
import com.wpy.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文章预览图设置
 */
@Controller
@RequestMapping("/articlePictureViewSettingAdminController")
public class ArticlePictureViewSettingAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private PictureService pictureService;

    @Resource
    private FirstPageBannerSettingService firstPageBannerSettingService;

    /**
     * 跳转到文章首图设置界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        return "admin/articlePictureViewSet";
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
        return firstPageBannerSettingService.getAllList(page,pageSize);
    }

    /**
     *
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     */
    @RequestMapping("/add")

    public Response add(HttpServletRequest request, HttpServletResponse response, Model model,String id, MultipartFile pictureFile) throws Exception {

        Blog blog= blogService.getObjectById(Integer.valueOf(id));
        String timeString =String.valueOf(new Date().getTime());
        int a=(int)(Math.random()*10);
        int b=(int)(Math.random()*10);
        timeString = timeString+a+b;
        String originalfileName =pictureFile.getOriginalFilename();
        String newFileName = timeString+originalfileName.substring(originalfileName.lastIndexOf("."));
        //String filePath = request.getContextPath()+"/bannerImages/";
        String filePath =request.getSession().getServletContext().getRealPath("/bannerImages");
        //新文件
        File file = new File(filePath,newFileName);
        //将内存中的文件写入磁盘
        pictureFile.transferTo(file);
        blog.setBannerName(newFileName);
        blog.setBannerFlag(1);
        Map<String, Object> map= new HashMap<>();
        try {
            blogService.update(blog);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false );
        }
        String result = JSON.toJSONString(map);
        ResponseUtil.write(response,result);
        return null;


    }

    /**
     *
     * @param request
     * @param response
     * @param model
     * @param blog
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public Response update(HttpServletRequest request, HttpServletResponse response, Model model, String id, MultipartFile pictureFile1) throws Exception {

        Blog blog= blogService.getObjectById(Integer.valueOf(id));
        String timeString =String.valueOf(new Date().getTime());
        int a=(int)(Math.random()*10);
        int b=(int)(Math.random()*10);
        timeString = timeString+a+b;
        String originalfileName =pictureFile1.getOriginalFilename();
        String newFileName = timeString+originalfileName.substring(originalfileName.lastIndexOf("."));
        //String filePath = request.getContextPath()+"/bannerImages/";
        String filePath =request.getSession().getServletContext().getRealPath("/bannerImages");
        //新文件
        File file = new File(filePath,newFileName);
        //将内存中的文件写入磁盘
        pictureFile1.transferTo(file);
        blog.setBannerName(newFileName);
        blog.setBannerFlag(1);
        Map<String, Object> map= new HashMap<>();
        try {
            blogService.update(blog);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false );
        }
        String result = JSON.toJSONString(map);
        ResponseUtil.write(response,result);
        return null;

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

        return firstPageBannerSettingService.delete(ids);

    }


    @RequestMapping("/save")
    public  String save(HttpServletRequest request, HttpServletResponse response, Model model, String id, MultipartFile pictureFile1) throws Exception{
        Blog blog= blogService.getObjectById(Integer.valueOf(id));
        String timeString =String.valueOf(new Date().getTime());
        int a=(int)(Math.random()*10);
        int b=(int)(Math.random()*10);
        timeString = timeString+a+b;
        String originalfileName =pictureFile1.getOriginalFilename();
        String newFileName = timeString+originalfileName.substring(originalfileName.lastIndexOf("."));
        String filePath =request.getSession().getServletContext().getRealPath("/articlePictureView");
        //新文件
        File file = new File(filePath,newFileName);
        //将内存中的文件写入磁盘
        pictureFile1.transferTo(file);
        //将路径存入图片表
        Picture picture = new Picture();
        picture.setPath(newFileName);
        pictureService.add(picture);
        int pictureId = picture.getId();
        blog.setArticlePictureViewId(pictureId);
        Map<String, Object> map= new HashMap<>();
        try {
            blogService.update(blog);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false );
        }
        String result = JSON.toJSONString(map);
        ResponseUtil.write(response,result);
        return null;
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
