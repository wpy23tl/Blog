package com.wpy.blog.service.impl;


import com.wpy.blog.entity.BlogType;
import com.wpy.blog.service.BlogTypeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogTypeServiceImplTest {

	@Test
	public void testGetAllBlogType() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlogTypeService blogTypeService = (BlogTypeService)ac.getBean("blogTypeService");
		Map<String,Object> map=new HashMap<String,Object>();
		List<BlogType> blogTypeList = blogTypeService.getAllList();
		System.out.println("-----------");
		System.out.println("-----------");
	}

}
