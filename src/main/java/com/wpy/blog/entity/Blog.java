package com.wpy.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Table(name = "tb_blog")
public class Blog implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//����id
	private String blogTitle;//���ͱ���
	private String blogContent;//��������
	private Date createTime;//����ʱ��
	private Date updateTime;//�޸�ʱ��
	private Integer blogTypeId;//��������id
	@Transient
	private String blogTypeName;
	private String summary;
	private Integer clickHit;//查看次数
	private Integer recommendFlag;//博主推荐标识
	private Integer recommendNo;//推荐编号
	@Transient
	private List<String> imagesList=new LinkedList<String>(); // 博客里存在的图片 主要用于列表展示显示缩略图
	private String bannerName;//轮播图名字
	private Integer bannerFlag;//轮播标志0不轮播 1轮播
	private Integer articlePictureViewId;//文章首图id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getBlogTypeId() {
		return blogTypeId;
	}
	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}
	public String getBlogTypeName() {
		return blogTypeName;
	}
	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getClickHit() {
		return clickHit;
	}
	public void setClickHit(Integer clickHit) {
		this.clickHit = clickHit;
	}
	public Integer getRecommendFlag() {
		return recommendFlag;
	}
	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
	public Integer getRecommendNo() {
		return recommendNo;
	}
	public void setRecommendNo(Integer recommendNo) {
		this.recommendNo = recommendNo;
	}
	public List<String> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public Integer getBannerFlag() {
		return bannerFlag;
	}

	public void setBannerFlag(Integer bannerFlag) {
		this.bannerFlag = bannerFlag;
	}

	public Integer getArticlePictureViewId() {
		return articlePictureViewId;
	}

	public void setArticlePictureViewId(Integer articlePictureViewId) {
		this.articlePictureViewId = articlePictureViewId;
	}
}
