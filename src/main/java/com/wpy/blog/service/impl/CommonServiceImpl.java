package com.wpy.blog.service.impl;


import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.wpy.blog.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



public class CommonServiceImpl<T> implements CommonService<T> {
	@Autowired
	private Mapper baseMapper;


	/**
	 * 增加
	 *
	 * @param t
	 **/
	@Override
	public void save(T t) {
		baseMapper.insert(t);
	}

	/**
	 * 修改
	 *
	 * @param t
	 **/
	@Override
	public void update(T t) {
		baseMapper.updateByPrimaryKey(t);
	}

	/**
	 * 删除
	 *
	 * @param id
	 **/
	@Override
	public void delete(Integer id) {
		baseMapper.delete(id);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 **/
	@Override
	public void batchDelete(String ids) {
		String[] idsArray = ids.split(",");
		for (int i = 0;i<idsArray.length;i++){
			baseMapper.delete(idsArray[i]);
		}
	}

	/**
	 * 获得所有数据
	 **/
	@Override
	public List<T> getList() {
		return baseMapper.select(null);
	}

	/**
	 * 根据条件获得所有数据
	 *
	 * @param map
	 **/
	@Override
	public List<T> getListByCondition(T t) {
		return baseMapper.select(t);
	}

	/**
	 * 分页后获得数据
	 *
	 * @param map
	 **/
	@Override
	public List<T> getListByConditionWithPage(Map<String, Object> map) {
		return null;
	}

	/**
	 * 分页后获得数据
	 *
	 * @param map
	 **/
	@Override
	public List<T> getListWithPage(Map<String, Object> map) {
//		String start = map.get("start");
//		String size = map.get("size");
//		PageHelper.startPage(1,Integer.valueOf(size).intValue());
//		List<T> list = baseMapper.select(null);
		return null;
	}

	/**
	 * 获得计数
	 **/
	@Override
	public Integer getCount(T t) {
		return Integer.valueOf(baseMapper.selectCount(t));
	}
}

