package com.wpy.blog.service.impl;


import com.wpy.blog.dao.BloggerDao;
import com.wpy.blog.dao.LinkMapper;
import com.wpy.blog.dao.PictureMapper;
import com.wpy.blog.entity.Blogger;
import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BloggerService;
import com.wpy.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文章首页图片
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService{

	@Autowired
	private PictureMapper pictureMapper;

	@Override
	public Response add(Picture picture) {
		Response response = new Response();
		try {
			pictureMapper.insert(picture);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return  response;
	}

	@Override
	public Response update(Picture picture) {
		return null;
	}

	@Override
	public Response delete(Integer id) {
		return null;
	}

	@Override
	public Picture getObjectById(Integer id) {
		return pictureMapper.selectByPrimaryKey(id);
	}

	@Override
	public DataGrid getAll(Map<String, Object> map) {
		return null;
	}

	@Override
	public Integer getTotalCount() {
		return null;
	}
}
