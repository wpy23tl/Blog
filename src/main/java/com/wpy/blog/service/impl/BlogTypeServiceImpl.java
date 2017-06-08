package com.wpy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpy.blog.dao.BlogTypeMapper;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.BlogTypeService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {
	@Autowired
	private BlogTypeMapper blogTypeMapper;


	@Override
	public Response add(BlogType blogType) {
		Response response = new Response();
		try {
			blogTypeMapper.insert(blogType);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response update(BlogType blogType) {
		Response response = new Response();
		try {
			blogTypeMapper.updateByPrimaryKey(blogType);
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
				blogTypeMapper.deleteByPrimaryKey(Integer.valueOf(idsArray[i]));
				response.setSuccess(true);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.setSuccess(false);
			}
		}
		return response;
	}



	@Override
	public Response<List<BlogType>> getAllList(String page, String pageSize) {

		Response<List<BlogType>> response = new Response<>();
		try {
			PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
			List<BlogType> blogTypeList = blogTypeMapper.select(null);
			response.setData(blogTypeList);
			response.setSuccess(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.setSuccess(false);
		}

		return response;

	}

	@Override
	public DataGrid getAllListToGrid(String page, String pageSize) {

			DataGrid dataGrid = new DataGrid();
			PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
			List<BlogType> blogTypeList = blogTypeMapper.select(null);
			dataGrid.setRows(blogTypeList);
			Integer total = blogTypeMapper.selectCount(new BlogType());
			dataGrid.setTotal(total);
			return dataGrid;

	}

	/**
	 * @param map
	 * @return
	 */
	@Override
	public Response<List<BlogType>> selectTypeCount(Map<String, Object> map) {
		Response response = new Response();
		try {
			List<BlogType> blogTypeList = blogTypeMapper.selectTypeCount(map);
			response.setData(blogTypeList);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public List<BlogType> getAllList() {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(1,2);
		List<BlogType> blogTypeList = blogTypeMapper.select(null);
		PageInfo<BlogType> pageInfo = new PageInfo<BlogType>(blogTypeList);
		pageInfo.getTotal();
		return null;
	}

}

