package com.wpy.blog.dao;

import com.github.abel533.mapper.Mapper;
import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeMapper extends Mapper<BlogType> {

    Integer getTotalCount();
    /**
     * @author wpy
     * @desc 查询博客类型数量
     * @date 2017年1月25日
     * @return
     */
    List<BlogType> selectTypeCount(Map<String,Object> map);

}
