package com.wpy.blog.service;


import com.wpy.blog.entity.Blog;
import com.wpy.blog.entity.User;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;
import com.wpy.blog.vo.BlogVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 登录及权限验证
 */
public interface UserService {

	public Response<User> login(String uesrname, String password, String verifCode);
	public Response updatePassword(String id,String password);


}
