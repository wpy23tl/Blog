package com.wpy.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/timeTaskController")
public class TimeTaskController {


	/**
	 * 跳转到博客管理界面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		return "admin/timeTaskManage";
	}

	@RequestMapping("/start")
	public String start(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

//		//QuartzUtil quartzUtil = new QuartzUtil();
//		quartzUtil.addJob("1","1", XX.class,"0/2 * * * * ?");
//		quartzUtil.start();
		return null;
	}
}
