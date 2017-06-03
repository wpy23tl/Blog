package com.wpy.blog.service;


import com.wpy.blog.entity.Link;
import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

import java.util.Map;

public interface LinkService {

	public Response add(Link link);
	public Response update(Link link);
	public Response delete(String ids);
	public Response getObjectById(Integer id);
	public DataGrid getAllList(String page, String pageSize);
	public Integer getTotalCount();
}
