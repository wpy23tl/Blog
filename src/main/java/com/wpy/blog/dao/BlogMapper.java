package com.wpy.blog.dao;

import com.github.abel533.mapper.Mapper;
import com.wpy.blog.entity.Blog;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {

    /**
     * 查询当前表所有数据
     * @param id
     * @return
     */
    List<Blog> selectAll();
    /**
     * 查询总记录数
     * @return
     */
    Integer getTotalCount();
    /**
     * @author wpy
     * @description  获取上一篇博客信息
     * @date 2017年1月12日
     * @return
     */
    Blog getLastBlog(Integer id);
    /**
     * @author wpy
     * @description 获取下一篇博客信息
     * @date 2017年1月12日
     * @return
     */
    Blog getNextBlog(Integer id);
    /**
     * @author wpy
     * @desc 根据点击率排行
     * @date 2017年1月18日
     * @return
     */
    List<Blog> getRankByClickHit();
    /**
     * @author wpy
     * @desc 根据创建时间拍行
     * @date 2017年1月18日
     * @return
     */
    List<Blog> getRankByCreateTime();
    /**
     * @author wpy
     * @desc 随机获取博客文章
     * @date 2017年1月18日
     * @return
     */
    List<Blog> getRankByRandom();
}