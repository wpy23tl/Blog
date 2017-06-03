package com.wpy.blog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wpy.blog.entity.Blog;
import com.wpy.blog.service.BlogService;

public class BlogServiceImplTest {

//	@Test
//	public void testAddBlog() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BlogService blogService = (BlogService)ac.getBean("blogService");
//		Blog blog = new Blog();
//		blog.setId(null);
//		blog.setBlogTitle("发的顺丰到付");
//		blog.setUpdateTime(new Date());
//		blog.setUpdateTime(new Date());
//		blog.setBlogContent("违法打发第三方第三方士大夫打发打发");
//		blog.setBlogTypeId(Integer.valueOf(23));
//		blogService.addBlog(blog);
//		System.out.println("------------");
//	}

//	@Test
//	public void testUpdateBlog() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BlogService blogService = (BlogService)ac.getBean("blogService");
//		Blog blog =blogService.getBlogById(Integer.valueOf(1));
//		blog.setBlogTitle("修改");
//		blog.setUpdateTime(new Date());
//		blog.setUpdateTime(new Date());
//		blog.setBlogContent("修改内容");
//		blog.setBlogTypeId(Integer.valueOf(85));
//		blogService.updateBlog(blog);
//		System.out.println("------------");
//	}

//	@Test
//	public void testDeleteBlog() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BlogService blogService = (BlogService)ac.getBean("blogService");
//		blogService.deleteBlog(Integer.valueOf(1));
//		System.out.println("------------");
//	}
//
//	@Test
//	public void testGetBlogById() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BlogService blogService = (BlogService)ac.getBean("blogService");
//		Blog blog =blogService.getBlogById(Integer.valueOf(1));
//		System.out.println("------------");
//		System.out.println(blog.toString());
//	}

//	@Test
//	public void testGetAllBlog() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BlogService blogService = (BlogService)ac.getBean("blogService");
//		Map<String,Object> map=new HashMap<String,Object>();
//		List<Blog> blogs = blogService.getAll(map);
//		System.out.println(blogs.toString());
//	}

}
