package com.wpy.blog.service;


import java.util.List;
import java.util.Map;

import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.vo.BlogVo;

import javax.servlet.http.HttpServletRequest;

public interface BlogService {


	public Response add(Blog blog);
	public Response update(Blog blog);
	public Response delete(String ids);
	public Blog     getObjectById(Integer id);
	public Response<List<BlogVo>> getAllList(Map<String,Object> map);
	public DataGrid getAllListToDataGrid(Map<String,Object> map);
	public Integer getTotalCount();

	/**
	 * 获取首页数据
	 * @return
	 */
	public Map<String,Object> getIndexData(String page,String pageSize,String blogTypeId,HttpServletRequest request);

	/**
	 * 获取文章数据
	 * @param page
	 * @param pageSize
	 * @param blogTypeId
	 * @param request
	 * @return
	 */
	public Map<String,Object> updateAndGetArticleData(String id,String blogTypeId);
	/**
	 * @author wpy
	 * @desc 根据点击数排行
	 * @date 2017年1月18日
	 * @return
	 */
	public Response<List<Blog>> getRankByClickHit();
	/**
	 * @author wpy
	 * @desc 获取上一个博客信息
	 * @date 2017年1月12日
	 * @param id
	 * @return
	 */
	public Response<Blog> getLastBlog(Integer id);
	/**
	 * @author wpy
	 * @desc 获取下一个博客新
	 * @date 2017年1月12日
	 * @param id
	 * @return
	 */
	public Response<Blog> getNextBlog(Integer id);
	/**
	 * @author wpy
	 * @desc 根据创建时间排序
	 * @date 2017年1月18日
	 * @return
	 */
	Response<Blog> getRankByCreateTime();
	/**
	 * @author wpy
	 * @desc 获取随机文章
	 * @date 2017年1月18日
	 * @return
	 */
	Response<Blog> getRankByRandom();

}
