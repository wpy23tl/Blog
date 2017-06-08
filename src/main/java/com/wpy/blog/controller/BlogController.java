package com.wpy.blog.controller;

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
import com.wpy.blog.util.EhcacheUtil;
import net.sf.ehcache.Cache;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.util.PageUtil;
import com.wpy.blog.vo.BlogVo;

@Controller
@RequestMapping("/blogController")
public class BlogController {

	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BloggerRecommendService bloggerRecommendService;
	@Resource
	private BlogService blogService;
	@Resource
	private FirstPageBannerSettingService firstPageBannerSettingService;
	@Resource
	private LinkService linkService;
	@Resource
	private PictureService pictureService;

	public Cache cache ;
	public List<BlogType> blogTypeList;
	public 	List<Blog> bannerBlogList;
	public List<BlogVo> newBlogList;
	public Integer totalCount = null;


	@ModelAttribute
	public void init(Model model){
		Map<String,Object> map = new HashMap<>();
		// 点击排行
		 Response<List<Blog>>  clickHitRank = blogService.getRankByClickHit();
		model.addAttribute("clickHitRank",clickHitRank.getData());
		//最新文章
		Response<Blog> createTimeRank = blogService.getRankByCreateTime();
		model.addAttribute("createTimeRank",createTimeRank.getData());
		//随机文章
		Response<Blog> randomBlogs = blogService.getRankByRandom();
		model.addAttribute("randomBlogs",randomBlogs.getData());
		//友情链接
        Response<List<Link>> linkList = linkService.getAllList("1","100");
		model.addAttribute("linkList",linkList.getData());
		//标签云（获取所有博客类型）
		Response<List<BlogType>> blogTypeList = blogTypeService.selectTypeCount(map);
		model.addAttribute("blogTypeList",blogTypeList.getData());
		//博主推荐
		//List<Blog> bloggerRecommends = blogService.getBloggerRecommend();
		DataGrid dataGrid1 = bloggerRecommendService.getAllList("1","100");
		List<BlogVo> bloggerRecommends = (List<BlogVo>)dataGrid1.getRows();
		List<BlogVo> newBloggerRecommends = new ArrayList<>();
		for(BlogVo blog:bloggerRecommends){
			Picture picture = pictureService.getObjectById(blog.getArticlePictureViewId());
			BlogVo blogVo = new BlogVo();
			BeanUtils.copyProperties(blog, blogVo);
			if (picture!=null){
				String picturePath = picture.getPath();
				blogVo.setPath(picturePath);
			}
			newBloggerRecommends.add(blogVo);
		}
		model.addAttribute("bloggerRecommends",newBloggerRecommends);
	}


	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam(value="page",required=false)String page,
						@RequestParam(value="rows",required=false)String pageSize,String blogTypeId){

		Map<String,Object> respMap = blogService.getIndexData(page,pageSize,blogTypeId,request);
		List<BlogVo> newBlogList = (List<BlogVo>)respMap.get("blogList");
		List<BlogVo> bannerBlogList = (List<BlogVo>)respMap.get("bannerBlogList");
		String pageCode = (String)respMap.get("pageCode");
		//向页面传输数据
		model.addAttribute("blogList",newBlogList);
		model.addAttribute("bannerBlogList",bannerBlogList);
		model.addAttribute("pageCode",pageCode);
		return "portal/index";
	}


	/**
	 * @author wpy
	 * @description 进入文章
	 * @date 2017年1月12日
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/article")
	public String article(HttpServletRequest request,HttpServletResponse response,Model model,String id,String blogTypeId){
		Map<String,Object> respMap = blogService.updateAndGetArticleData(id,blogTypeId);
		List<BlogVo> newBlogList = (List<BlogVo>)respMap.get("blogList");
		BlogVo blogVo  = (BlogVo)respMap.get("blog");
		Blog lastBlog = (Blog)respMap.get("lastBlog");
		Blog nextBlog = (Blog) respMap.get("nextBlog");
		//向页面传输数据
		model.addAttribute("blog",blogVo);
		model.addAttribute("blogList",newBlogList);
		model.addAttribute("lastBlog",lastBlog);
		model.addAttribute("nextBlog",nextBlog);
		return "portal/article";
	}
}
