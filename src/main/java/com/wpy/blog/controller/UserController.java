package com.wpy.blog.controller;

import com.wpy.blog.entity.*;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.*;
import com.wpy.blog.vo.BlogVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userController")
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
	@RequestMapping("/login")
 public String login(String userName,String password,String verifyCode,Model model,HttpServletRequest request){

	if (StringUtils.isEmpty(userName)){
		model.addAttribute("errorInfo","请输入用户名！");
		return "login";
	}
	if (StringUtils.isEmpty(password)){
		model.addAttribute("errorInfo","请输入密码！");
		return "login";
	}
	if (StringUtils.isEmpty(verifyCode)){
		model.addAttribute("errorInfo","请输入验证码！");
		return "login";
	}

	//校验验证码
	String sessionVerifyCode = (String) request.getSession().getAttribute("sRand");
	if (!verifyCode.equals(sessionVerifyCode)){
		model.addAttribute("errorInfo","验证码错误！");
		return "login";
	}
 	Response<User> response = userService.login(userName,password,verifyCode);
 	User user = response.getData();
 	if (user == null){
 		model.addAttribute("errorInfo","用户不存在！");
 		return "login";
	}

	if (!password.equals(user.getPassword())){
		model.addAttribute("errorInfo","密码输入错误！");
		return "login";

	}

	request.getSession().setAttribute("currentUser",user);

	return  "admin/main";

 }

	/**
	 * 修改博主密码
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public Response modifyPassword(String id,String newPassword,HttpServletResponse response)throws Exception {

		Response resp = userService.updatePassword(id, newPassword);
		return resp;

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:login.jsp";

	}

	/**
	 * 跳转到关于我页面
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping("/aboutMe")
	public String aboutMe( HttpServletRequest request, Model model){
		User user = userService.getObjectById(1);
		model.addAttribute("aboutMe",user.getInfo());
		return "forceGround/aboutMe";
	}

	/**
	 * 跳转到修改关于我界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifyAboutMePage")
	public String modifyAboutMePage(String id,HttpServletRequest request,HttpServletResponse response,Model model){
		User user = userService.getObjectById(Integer.valueOf(id));
		model.addAttribute("aboutMe",user.getInfo());
		return "/admin/modifyAboutMe";

	}

	/**
	 * 保存关于我
	 *
	 * @return
	 */
	@RequestMapping("/saveAboutMe")
	@ResponseBody
	public Response saveAboutMe(String id,HttpServletRequest request,HttpServletResponse response,String aboutMe) throws Exception {

		return userService.saveAboutMe(id,aboutMe);
	}
}
