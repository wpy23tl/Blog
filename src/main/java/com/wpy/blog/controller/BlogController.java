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
		//application.setAttribute("clickHitRank",clickHitRank);
		model.addAttribute("clickHitRank",clickHitRank.getData());
		//最新文章
		Response<Blog> createTimeRank = blogService.getRankByCreateTime();
		//application.setAttribute("createTimeRank",createTimeRank);
		model.addAttribute("createTimeRank",createTimeRank.getData());
		//随机文章
		Response<Blog> randomBlogs = blogService.getRankByRandom();
		//application.setAttribute("randomBlogs",randomBlogs);
		model.addAttribute("randomBlogs",randomBlogs.getData());
		//友情链接
        DataGrid dataGrid = linkService.getAllList("1","100");
        List<Link> linkList =(List<Link>)dataGrid.getRows();
		model.addAttribute("linkList",linkList);
		//application.setAttribute("linkList",linkList);
		//标签云（获取所有博客类型）
		Response<List<BlogType>> blogTypeList = blogTypeService.selectTypeCount(map);
		model.addAttribute("blogTypeList",blogTypeList.getData());
//		//application.setAttribute("blogTypeList",blogTypeList);
		//博主推荐
		//List<Blog> bloggerRecommends = blogService.getBloggerRecommend();
		DataGrid dataGrid1 = bloggerRecommendService.getAllList("1","100");
		List<Blog> newBloggerRecommends = (List<Blog>)dataGrid1.getRows();
//		List<BlogVo> newBloggerRecommends = new ArrayList<>();
//		for(Blog blog:bloggerRecommends){
//			Picture picture = pictureService.getBlogById(blog.getArticlePictureViewId());
//			BlogVo blogVo = new BlogVo();
//			BeanUtils.copyProperties(blog, blogVo);
//			if (picture!=null){
//				String picturePath = picture.getPath();
//				blogVo.setPath(picturePath);
//			}
//			newBloggerRecommends.add(blogVo);
//		}
		model.addAttribute("bloggerRecommends",newBloggerRecommends);
	}


	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam(value="page",required=false)String page,
						@RequestParam(value="rows",required=false)String pageSize,String blogTypeId){
		//判断当前页与要查询条数
		if("".equals(page) || page==null){
			page="1";
		}
		if("".equals(pageSize) || pageSize==null){
			pageSize="10";
		}
		//查询出所有博客
		DataGrid dataGrid = blogService.getAllList(page,pageSize);
		List<BlogVo> blogList = (List<BlogVo>)dataGrid.getRows();
		List<BlogVo> newBlogList = new ArrayList<>();
		for(BlogVo blogVo:blogList){
			Picture picture = pictureService.getObjectById(blogVo.getArticlePictureViewId());
			if (picture!=null){
				String picturePath = picture.getPath();
				blogVo.setPath(picturePath);
			}
			newBlogList.add(blogVo);
		}
		model.addAttribute("blogList",newBlogList);

		//查出所有banner
		DataGrid dataGrid1 = firstPageBannerSettingService.getAllList("1","100");
		List<BlogVo> bannerBlogList = (List<BlogVo>)dataGrid1.getRows();
		model.addAttribute("bannerBlogList",bannerBlogList);
		Integer totalCount = blogService.getTotalCount();
		String pageCode = PageUtil.genPageCode(totalCount, Integer.valueOf(pageSize),Integer.valueOf(page), blogTypeId, request);
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
		Map<String,Object> map = new HashMap<>();
		//获取所有博客
		DataGrid dataGrid =  blogService.getAllList("1","100");
		List<Blog> newBlogList = (List<Blog>)dataGrid.getRows();
		//List<BlogVo> newBlogList = new ArrayList<>();
//		for(Blog blog:blogList){
//			Date createTime = blog.getCreateTime();
//			String createTimeString = DateTimeUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
//			BlogVo blogVo = new BlogVo();
//			BeanUtils.copyProperties(blog, blogVo);
//			blogVo.setCreateTime(createTimeString);
//			newBlogList.add(blogVo);
//		}
		model.addAttribute("blogList",newBlogList);
		//根据id获取博客
		Blog blog =blogService.getObjectById(Integer.valueOf(id));
		//增加查看次数
		blog.setClickHit(blog.getClickHit()+1);
		blogService.update(blog);
		Date createTime = blog.getCreateTime();
		BlogVo blogVo = new BlogVo();
		BeanUtils.copyProperties(blog, blogVo);
		String createTimeString = DateTimeUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
		blogVo.setCreateTime(createTimeString);
		model.addAttribute("blog",blogVo);
		//获取上一篇博客
		Response<Blog> lastBlog =	blogService.getLastBlog(Integer.valueOf(id));
		//获取下一篇博客
		Response<Blog> nextBlog = blogService.getNextBlog(Integer.valueOf(id));
		model.addAttribute("lastBlog",lastBlog.getData());
		model.addAttribute("nextBlog",nextBlog.getData());



		if(blogTypeId!=null && !"null".equals(blogTypeId) ){
			map.put("blogTypeId",Integer.valueOf(blogTypeId));
		}
		Map<String,Object> map0 = new HashMap<>();
//		List<BlogType> blogTypeList =blogTypeService.selectTypeCount(map0);
//		model.addAttribute("blogTypeList",blogTypeList);
		return "portal/article";
	}
}
