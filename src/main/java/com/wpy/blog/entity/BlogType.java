package com.wpy.blog.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_blogtype")
public class BlogType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//����id
	private String blogTypeName;//������������
	@Transient
	private Integer blogTypeCount;//博客类型数量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlogTypeName() {
		return blogTypeName;
	}
	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}
	public Integer getBlogTypeCount() {
		return blogTypeCount;
	}
	public void setBlogTypeCount(Integer blogTypeCount) {
		this.blogTypeCount = blogTypeCount;
	}
	
}
