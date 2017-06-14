package com.wpy.blog.job;

import com.wpy.blog.entity.BlogType;
import com.wpy.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wpy on 2017/6/13.
 */
@Component
public class TestJob {
    @Autowired
    private BlogTypeService blogTypeService;

    //@Scheduled(cron = "0/1 * * * * ?")
    public void haha(){
        System.out.println("aaaaaaaaaaaaaaaaaa");
        BlogType blogType = new BlogType();
        blogType.setBlogTypeName("aaaa");
        blogTypeService.add(blogType);
    }
}
