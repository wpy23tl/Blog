package com.wpy.blog.service;


import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

import java.util.List;
import java.util.Map;

public interface BlogTypeService {

	/**
     * 增加
	 * @param blog
     * @return
     */
	public Response add(BlogType blogType);

    /**
     * 修改
	 * @param blog
     * @return
     */
	public Response update(BlogType blogType);

    /**
     * 删除
	 * @param ids
     * @return
     */
	public Response delete(String ids);

    /**
     * 填充数据表格
	 * @param page
     * @param pageSize
     * @return
     */
	public Response<List<BlogType>> getAllList(String page, String pageSize);
	public DataGrid getAllListToGrid(String page, String pageSize);

	/**
	 *
	 * @param map
	 * @return
	 */
	public Response<List<BlogType>> selectTypeCount(Map<String,Object> map);
	public List<BlogType> getAllList();
}
