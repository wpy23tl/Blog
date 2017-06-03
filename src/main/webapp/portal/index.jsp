<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>老王头个人博客</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${pageContext.request.contextPath}/blogResources/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/css/index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/css/learn.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/blogResources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/blogResources/js/sliders.js"></script>
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<!-- 返回顶部调用 begin -->
<script type="text/javascript" src="js/up/jquery.js"></script>
<script type="text/javascript" src="js/up/js.js"></script>
<!-- 返回顶部调用 end-->
</head>
<body>
<jsp:include page="/common/head.jsp"/>

<article>
  <div class="l_box f_l">
    <div class="banner">
      <div id="slide-holder">
        <div id="slide-runner"> 
        <c:forEach var="bannerBlog" items="${bannerBlogList }">
        	 <a href="${pageContext.request.contextPath}/blogController/article.do?id=${bannerBlog.id}" target="_blank"><img id="${bannerBlog.id}" width="100%" src="${pageContext.request.contextPath}/bannerImages/${bannerBlog.bannerName}"  alt="" /></a>
        </c:forEach>
          <div id="slide-controls">
            <p id="slide-client" class="text"><strong></strong><span></span></p>
            <p id="slide-desc" class="text"></p>
            <p id="slide-nav"></p>
          </div>
        </div>
      </div>
      <script>
	  if(!window.slider) {
		var slider={};
	}

	slider.data= [
	   <c:forEach var="bannerBlog" varStatus="stat" items="${bannerBlogList }" >
	   <c:choose>
		<c:when test="${!st.last}">
		{
	        "id":"${bannerBlog.id}", // 与slide-runner中的img标签id对应
	        "client":"",
	        "desc":"${bannerBlog.blogTitle}" //这里修改描述
	    },
		</c:when>
		<c:otherwise>
		{
	        "id":"${bannerBlog.id}", // 与slide-runner中的img标签id对应
	        "client":"标题1",
	        "desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
	    }
		</c:otherwise>
	</c:choose>
	  
	   </c:forEach>
    
	];

	  </script> 
    </div>
    <!-- banner代码 结束 -->
    <div class="topnews">
      <h2><span><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a></span><b>文章</b>推荐</h2>
      <c:forEach var="blog" items="${blogList }" >
	      <div class="blogs">
	        <figure>
                <c:forEach var="path" items="${blog.path}">
                    <img src="${pageContext.request.contextPath}/articlePictureView/${path}" title="${path}" alt="${path}">
                </c:forEach>
            </figure>
	        <ul>
	          <h3><a href="${pageContext.request.contextPath}/blogController/article.do?id=${blog.id}">${blog.blogTitle}</a></h3>
	          <p>${blog.summary }......</p>
	          <p class="autor"><span class="lm f_l"><a href="/">${blog.blogTypeName}</a></span><span class="dtime f_l">${blog.createTime}</span><span class="viewnum f_r">浏览（<a href="/">${blog.clickHit }</a>）</span><span class="pingl f_r">评论（<a href="/">30</a>）</span></p>
	        </ul>
	      </div>
      </c:forEach>
     
     ${pageCode }
    
    </div>
  </div>
  <%@ include file="/common/right.jsp"%>
</article>

<jsp:include page="/common/foot.jsp"/>
</body>
</html>
