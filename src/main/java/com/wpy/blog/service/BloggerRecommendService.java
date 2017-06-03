package com.wpy.blog.service;


import com.wpy.blog.entity.Blog;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

import java.util.List;

public interface BloggerRecommendService {


	public Response add(String ids);
	public Response update(Blog blog);
	public Response delete(String ids);
	public Blog     getObjectById(Integer id);
	public DataGrid getAllList(String page, String pageSize);
	public Response<Integer> getTotalCount();
}
