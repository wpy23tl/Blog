package com.wpy.blog.service.impl;

import com.wpy.blog.dao.BlogTypeMapper;
import com.wpy.blog.entity.BlogType;
import com.wpy.blog.service.BlogTypeService;
import com.wpy.blog.service.impl.BlogTypeServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * Created by wpy on 2017/6/11.
 */
public class XX extends QuartzJobBean {


    @Autowired
    private  BlogTypeService blogTypeService;



    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                BlogType blogType = new BlogType();
        blogType.setBlogTypeName("的范德萨发的");
        blogTypeService.add(blogType);
        //blogTypeMapper.insert(blogType);
        System.out.println("xxxxxxxxxxx");
    }
}
