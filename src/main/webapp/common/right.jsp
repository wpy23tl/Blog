<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


  <div class="r_box f_r">
    <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:admin@admin.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad300x100"> <img src="${pageContext.request.contextPath}/blogResources/images/ad300x100.jpg"> </div>
    <div class="moreSelect" id="lp_right_select"> 
      <script>
window.onload = function ()
{
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
}
</script>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/">点击排行</a></li>
          <li><a href="/">最新文章</a></li>
          <li><a href="/">随机文章</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul>
          	<c:forEach var="clickRank" items="${clickHitRank }">
          		<li><a href="${pageContext.request.contextPath}/blogController/article.do?id=${clickRank.id}" target="_blank">${clickRank.blogTitle}</a></li>
          	</c:forEach>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
          <c:forEach var="createRank" items="${createTimeRank}">
          		<li><a href="${pageContext.request.contextPath}/blogController/article.do?id=${createRank.id}" target="_blank">${createRank.blogTitle}</a></li>
          	</c:forEach>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
          <c:forEach var="random" items="${randomBlogs}">
          		<li><a href="${pageContext.request.contextPath}/blogController/article.do?id=${random.id}" target="_blank">${random.blogTitle}</a></li>
          	</c:forEach>
          </ul>
        </div>
      </div>
      <!--ms-main end --> 
    </div>
    <!--切换卡 moreSelect end -->
    
    <div class="cloud">
      <h3>标签云</h3>
      <ul>
       <c:forEach var="blogType" items="${blogTypeList}" varStatus="stat">
       	<li><a href="${pageContext.request.contextPath}/blogController/index.do?blogTypeId=${blogType.id}">${blogType.blogTypeName}(${blogType.blogTypeCount })</a></li>
       </c:forEach>
      </ul>
    </div>
    <div class="tuwen">
      <h3>博主推荐</h3>
      <ul>
      <c:forEach var="recommend" items="${bloggerRecommends}">
       	<li>
            <a href="${pageContext.request.contextPath}/blogController/article.do?id=${recommend.id}">
                <c:forEach var="path" items="${recommend.path }"><img src="${pageContext.request.contextPath}/articlePictureView/${path}" title="${path}" alt="${path}"></c:forEach><b>${recommend.blogTitle }</b>
            </a>
          <p><span class="tulanmu"><a href="/">${recommend.blogTypeName}</a></span><span class="tutime">${recommend.createTime}</span></p>
        </li>
       </c:forEach>
      </ul>
    </div>
    <div class="ad"> <img src="${pageContext.request.contextPath}/blogResources/images/03.jpg"> </div>
    <div class="links">
      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <c:forEach var="link" items="${linkList}" >
            <li><a href="${link.linkUrl}">${link.linkName}</a></li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <!--r_box end -->