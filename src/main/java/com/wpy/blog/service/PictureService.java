package com.wpy.blog.service;


import com.wpy.blog.entity.BlogType;
import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

import java.util.List;
import java.util.Map;

public interface PictureService {

	public Response add(Picture picture);
	public Response update(Picture picture);
	public Response delete(Integer id);
	public Picture getObjectById(Integer id);
	public DataGrid getAll(Map<String, Object> map);
	public Integer getTotalCount();

}
