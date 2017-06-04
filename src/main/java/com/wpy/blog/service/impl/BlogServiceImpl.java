package com.wpy.blog.service.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpy.blog.dao.BlogMapper;
import com.wpy.blog.entity.PageBean;
import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.FirstPageBannerSettingService;
import com.wpy.blog.service.PictureService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.util.PageUtil;
import com.wpy.blog.util.ResponseUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpy.blog.entity.Blog;
import com.wpy.blog.service.BlogService;



@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogMapper blogMapper;

	@Resource
	private PictureService pictureService;
	@Resource
	private FirstPageBannerSettingService firstPageBannerSettingService;

	@Override
	public Response add(Blog blog) {
		Response response = new Response();
		try {
			//blog.setCreateTime(new Date());
			//写博客初始浏览数为0
			blog.setClickHit(0);
			blogMapper.insert(blog);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response update(Blog blog) {
		Response response = new Response();
		try {
			//blog.setCreateTime(new Date());
			blogMapper.updateByPrimaryKey(blog);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;

	}

	@Override
	public Response delete(String ids) {
		String[] idsArray = ids.split(",");
		Response response = new Response();
		for(int i=0;i<idsArray.length;i++){
			try {
				blogMapper.deleteByPrimaryKey(Integer.valueOf(idsArray[i]));
				response.setSuccess(true);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.setSuccess(false);
			}
		}
		return response;
	}

	@Override
	public Blog getObjectById(Integer id) {
		return blogMapper.selectByPrimaryKey(id);
	}


	@Override
	public DataGrid getAllList(Map<String,Object> map) {
		String page = (String) map.get("page");
		String pageSize = (String) map.get("pageSize");
		//List<Blog> blogList = blogMapper.select(null);
		List<Blog> blogList = blogMapper.selectAll(map);
		PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
		//int total = blogMapper.getTotalCount();
		List<BlogVo> newBlogList = new ArrayList<>();
		for(Blog blog:blogList){
			Date createTime = blog.getCreateTime();
			String createTimeString = DateTimeUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
			BlogVo blogVo = new BlogVo();
			BeanUtils.copyProperties(blog, blogVo);
			blogVo.setCreateTime(createTimeString);
			newBlogList.add(blogVo);
		}
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(newBlogList);
		//dataGrid.setTotal(total);


		return dataGrid;
	}

	@Override
	public Integer getTotalCount() {
		return blogMapper.getTotalCount();
	}

	/**
	 * 获取首页数据
	 *
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> getIndexData(String page,String pageSize,String blogTypeId,HttpServletRequest request) {
		Map<String,Object> respMap = new HashMap<>();

		//判断当前页与要查询条数
		if("".equals(page) || page==null){
			page="1";
		}
		if("".equals(pageSize) || pageSize==null){
			pageSize="10";
		}
		//查询出所有博客
		Map<String,Object> map = new HashMap<>();
		PageBean pageBean = new PageBean(Integer.valueOf(page),Integer.valueOf(pageSize));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("blogTypeId",blogTypeId);
		DataGrid dataGrid = this.getAllList(map);
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
		respMap.put("blogList",newBlogList);
		//查出所有banner
		DataGrid dataGrid1 = firstPageBannerSettingService.getAllList("1","100");
		List<BlogVo> bannerBlogList = (List<BlogVo>)dataGrid1.getRows();
		respMap.put("bannerBlogList",bannerBlogList);
		Integer totalCount = this.getTotalCount();
		String pageCode = PageUtil.genPageCode(totalCount, Integer.valueOf(pageSize),Integer.valueOf(page), blogTypeId, request);
		respMap.put("pageCode",pageCode);
		return respMap;
	}

	/**
	 * 修改以及获取文章数据
	 * 修改指修改访问量
	 * @param id
	 * @param blogTypeId
	 * @return
	 */
	@Override
	public Map<String, Object> updateAndGetArticleData(String id, String blogTypeId) {
		Map<String,Object> respMap = new HashMap<>();
		//获取所有博客
		Map<String,Object> map = new HashMap<>();
		map.put("blogTypeId",blogTypeId);
		DataGrid dataGrid = this.getAllList(map);
		List<BlogVo> newBlogList = (List<BlogVo>)dataGrid.getRows();
		respMap.put("blogList",newBlogList);
		//根据id获取博客
		Blog blog =this.getObjectById(Integer.valueOf(id));
		//增加查看次数
		blog.setClickHit(blog.getClickHit()+1);
		this.update(blog);
		//blogMapper.updateByPrimaryKey(blog);
		Date createTime = blog.getCreateTime();
		BlogVo blogVo = new BlogVo();
		BeanUtils.copyProperties(blog, blogVo);
		String createTimeString = DateTimeUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
		blogVo.setCreateTime(createTimeString);
		respMap.put("blog",blogVo);
		//获取上一篇博客
		Response<Blog> lastBlog =	this.getLastBlog(Integer.valueOf(id));
		//获取下一篇博客
		Response<Blog> nextBlog = this.getNextBlog(Integer.valueOf(id));
		respMap.put("lastBlog",lastBlog.getData());
		respMap.put("nextBlog",nextBlog.getData());

		if(blogTypeId!=null && !"null".equals(blogTypeId) ){
			map.put("blogTypeId",Integer.valueOf(blogTypeId));
		}
		return respMap;
	}


	/**
	 * @return
	 * @author wpy
	 * @desc 根据点击数排行
	 * @date 2017年1月18日
	 */
	@Override
	public Response<List<Blog>> getRankByClickHit() {
		Response response = new Response();
		try {
			List<Blog> blogList = blogMapper.getRankByClickHit();
			response.setData(blogList);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @param id
	 * @return
	 * @author wpy
	 * @desc 获取上一个博客信息
	 * @date 2017年1月12日
	 */
	@Override
	public Response<Blog> getLastBlog(Integer id) {
		Response response = new Response();
		try {
			Blog blog = blogMapper.getLastBlog(id);
			response.setData(blog);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @param id
	 * @return
	 * @author wpy
	 * @desc 获取下一个博客新
	 * @date 2017年1月12日
	 */
	@Override
	public Response<Blog> getNextBlog(Integer id) {
		Response response = new Response();
		try {
			Blog blog = blogMapper.getNextBlog(id);
			response.setData(blog);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @return
	 * @author wpy
	 * @desc 根据创建时间排序
	 * @date 2017年1月18日
	 */
	@Override
	public Response<Blog> getRankByCreateTime() {
		Response response = new Response();
		try {
			List<Blog> blogList = blogMapper.getRankByCreateTime();
			response.setData(blogList);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @return
	 * @author wpy
	 * @desc 获取随机文章
	 * @date 2017年1月18日
	 */
	@Override
	public Response<Blog> getRankByRandom() {
		Response response = new Response();
		try {
			List<Blog> blogList = blogMapper.getRankByRandom();
			response.setData(blogList);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

}

