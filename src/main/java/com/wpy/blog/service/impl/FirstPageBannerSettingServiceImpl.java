package com.wpy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wpy.blog.dao.BlogMapper;
import com.wpy.blog.dao.FirstPageBannerSettingMapper;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.FirstPageBannerSettingService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("/firstPageBannerSettingService")
public class FirstPageBannerSettingServiceImpl implements FirstPageBannerSettingService {
	@Autowired
	private BlogMapper blogMapper;

	@Autowired
	private FirstPageBannerSettingMapper firstPageBannerSettingMapper;

	@Override
	public Response add(Blog blog) {
		Response response = new Response();
		try {
			//blog.setCreateTime(new Date());
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
		return null;
	}

	@Override
	public Response delete(String ids) {
		Response response = new Response();
		String[] idsArray = ids.split(",");
		for(int i=0;i<idsArray.length;i++){
			Blog blog =	blogMapper.selectByPrimaryKey(Integer.valueOf(idsArray[i]));
			blog.setBannerFlag(0);
			blog.setBannerName("");
			blogMapper.updateByPrimaryKey(blog);
		}

		return  response;
	}

	@Override
	public Blog getObjectById(Integer id) {
		return blogMapper.selectByPrimaryKey(id);
	}


	@Override
	public DataGrid getAllList(String page, String pageSize) {
		PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
		//List<Blog> blogList = blogMapper.select(null);\
		Map map = new HashMap();
		List<Blog> blogList = firstPageBannerSettingMapper.selectAll(map);
		int total = blogMapper.selectCount(new Blog());
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
	public Response<Integer> getTotalCount() {
		return null;
	}

}

