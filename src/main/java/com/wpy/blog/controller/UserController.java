package com.wpy.blog.controller;

import com.wpy.blog.entity.*;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.*;
import com.wpy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blogController")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 *
	 * @param userName 用户名
	 * @param password 密码
	 * @param verifyCod 验证码
	 * @return
	 */
 public String login(String userName,String password,String verifyCod,Model model){

 	Response<User> response = userService.login(userName,password,verifyCod);
 	User user = response.getData();
 	if (user == null){
 		model.addAttribute("errorInfo","用户不存在！");
 		return "login/login";
	}

	if (!password.equals(user.getPassword())){
		model.addAttribute("errorInfo","密码输入错误！");
		return "login/login";

	}

	return  "index";

 }

}
