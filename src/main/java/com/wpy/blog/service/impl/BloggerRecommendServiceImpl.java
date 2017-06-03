package com.wpy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wpy.blog.dao.BlogMapper;
import com.wpy.blog.dao.BloggerRecommedMapper;
import com.wpy.blog.dao.FirstPageBannerSettingMapper;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.BlogService;
import com.wpy.blog.service.BloggerRecommendService;
import com.wpy.blog.util.DateTimeUtil;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("bloggeRecommendService")
public class BloggerRecommendServiceImpl implements BloggerRecommendService {
	@Autowired
	private BlogMapper blogMapper;

	@Autowired
	private BloggerRecommedMapper bloggerRecommedMapper;

	@Autowired
	private FirstPageBannerSettingMapper firstPageBannerSettingMapper;

	@Override
	public Response add(String ids) {

		String[] idsArray = ids.split(",");
		Response response = new Response();
		for(int i=0;i<idsArray.length;i++){
			try {
				Blog blog = blogMapper.selectByPrimaryKey(Integer.valueOf(idsArray[i]));
				blog.setRecommendFlag(1);
				blogMapper.updateByPrimaryKey(blog);
				response.setSuccess(true);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.setSuccess(false);
			}
		}
		return response;
	}

	@Override
	public Response update(Blog blog) {

			Response response = new Response();
			try {
				//Blog newBlog = blogMapper.selectByPrimaryKey(blog.getId());
				blogMapper.updateByPrimaryKey(blog);
				response.setSuccess(true);
			} catch (NumberFormatException e) {
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
				Blog blog = blogMapper.selectByPrimaryKey(Integer.valueOf(idsArray[i]));
				blog.setRecommendFlag(0);
				blogMapper.updateByPrimaryKey(blog);
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
	public DataGrid getAllList(String page, String pageSize) {
		PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(pageSize));
		Blog blo = new Blog();
		blo.setRecommendFlag(1);
		//List<Blog> blogList = blogMapper.select(blo);
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

