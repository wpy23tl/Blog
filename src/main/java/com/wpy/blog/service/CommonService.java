package com.wpy.blog.service;


import com.wpy.blog.entity.Picture;
import com.wpy.blog.framework.model.DataGrid;
import com.wpy.blog.framework.model.Response;

import java.util.List;
import java.util.Map;

/**
 * 针对单表的CRUD
 */
public interface CommonService<T> {
	/**增加**/
	void save(T t);
	/**修改**/
	void update(T t);
	/**删除**/
	void delete(Integer id);
	/**批量删除**/
	void batchDelete(String ids);
	/**获得所有数据**/
	List<T> getList();
	/**根据条件获得所有数据**/
	List<T> getListByCondition(T t);
	/**分页后获得数据**/
	List<T> getListByConditionWithPage(Map<String,Object> map);
	/**分页后获得数据**/
	List<T> getListWithPage(Map<String,Object> map);
	/**获得计数**/
	Integer getCount(T t);



}
