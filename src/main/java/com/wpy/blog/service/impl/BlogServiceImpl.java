package com.wpy.blog.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpy.blog.dao.BlogMapper;
import com.wpy.blog.entity.PageBean;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.util.DateTimeUtil;
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
		PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
		//List<Blog> blogList = blogMapper.select(null);
		List<Blog> blogList = blogMapper.selectAll(map);
		PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
		//int total = blogMapper.selectCount(new Blog());
		int total = Integer.valueOf(String.valueOf(pageInfo.getTotal())).intValue();
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
		dataGrid.setTotal(total);


		return dataGrid;
	}

	@Override
	public Integer getTotalCount() {
		return blogMapper.getTotalCount();
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

