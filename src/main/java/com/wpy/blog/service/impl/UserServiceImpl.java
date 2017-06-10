package com.wpy.blog.service.impl;


import com.wpy.blog.dao.UserMapper;
import com.wpy.blog.entity.User;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ����Serviceʵ����
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper bloggerMapper;

	@Override
	public Response<User> login(String uesrname, String password, String verifCode) {

		Response<User> response = new Response<>();
		User user = new User();
		user.setUserName(uesrname);
		User newUser = bloggerMapper.selectOne(user);
		response.setData(newUser);
		return response;
	}
}
