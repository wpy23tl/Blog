package com.wpy.blog.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wpy.blog.entity.Blog;

public class BlogDaoTest {
//	private BlogDao blogDao;
//
//	@Before
//	public void setUp() throws Exception {
//		 // 完成mybatis的初始化
//        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        this.blogDao = sqlSession.getMapper(BlogDao.class);
//	}
//
//	@Test
//	public void testSelectByPrimaryKey() {
//	Blog blog =	blogDao.selectByPrimaryKey(Integer.valueOf(1));
//	System.out.println(blog);
//	}

}
