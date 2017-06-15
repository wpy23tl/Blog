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
	private UserMapper userMapper;

	@Override
	public Response<User> login(String uesrname, String password, String verifCode) {

		Response<User> response = new Response<>();
		User user = new User();
		user.setUserName(uesrname);
		User newUser = userMapper.selectOne(user);
		response.setData(newUser);
		return response;
	}

	@Override
	public Response updatePassword(String id, String password) {
		Response response = new Response();
		User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
		user.setPassword(password);
		try {
			userMapper.updateByPrimaryKey(user);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public User getObjectById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Response saveAboutMe(String id,String info) {
		Response response = new Response();
		try {
			User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
			user.setInfo(info);
			userMapper.updateByPrimaryKey(user);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
	}
}
