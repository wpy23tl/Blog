package com.wpy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wpy.blog.dao.BlogMapper;
import com.wpy.blog.dao.LinkMapper;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.entity.Link;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.LinkService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("linkService")
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkMapper linkMapper;


	@Override
	public Response add(Link link) {
		Response response = new Response();
		try {
			linkMapper.insert(link);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response update(Link link) {
		Response response = new Response();
		try {
			linkMapper.updateByPrimaryKey(link);
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
				linkMapper.deleteByPrimaryKey(Integer.valueOf(idsArray[i]));
				response.setSuccess(true);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.setSuccess(false);
			}
		}
		return response;
	}

	@Override
	public Response getObjectById(Integer id) {
		return null;
	}

	@Override
	public DataGrid getAllList(String page, String pageSize) {
		PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
		List<Link> linkList = linkMapper.select(null);
		int total = linkMapper.selectCount(new Link());
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(linkList);
		dataGrid.setTotal(total);
		return dataGrid;

	}

	@Override
	public Integer getTotalCount() {
		return null;
	}
}

