package com.wpy.blog.service;


import java.util.List;
import java.util.Map;

import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

public interface BlogService {


	public Response add(Blog blog);
	public Response update(Blog blog);
	public Response delete(String ids);
	public Blog     getObjectById(Integer id);
	public DataGrid getAllList(Map<String,Object> map);
	public Integer getTotalCount();
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
