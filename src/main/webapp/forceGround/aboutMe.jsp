<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>老王头个人博客</title>
<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="${pageContext.request.contextPath}/blogResources/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/css/index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/newcss/new.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/newcss/embed.default.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/newcss/lrtk.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/blogResources/newcss/share_style1_32.css" rel="stylesheet">

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
    <h2 class="c_titile">${blog.blogTitle}</h2>
    <p class="box_c"><span class="d_time">发布时间：${blog.createTime}</span><span>编辑：<a href="mailto:dancesmiling@qq.com">老王头</a></span><span>阅读（<script src="/e/public/ViewClick/?classid=8&amp;id=530&amp;addclick=1"></script>${blog.clickHit}）</span></p>
    <ul class="infos">
		${aboutMe}
    </ul>
    <div class="keybq">
    <p><span>关键字词</span>：学习,web,前端开发,web前端,学习web前端</p>    
    </div>
    <div class="ad"><script type="text/javascript">
/*自定义标签云myqq，创建于2013-8-2*/
var cpro_id = "u1335521";
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

</div>
    <div class="otherlink">
      <h2>相关文章</h2>
      <ul>
 <li><a href="/jstt/css3/2014-05-12/664.html" title="关于响应式Web设计技巧以及入门">关于响应式Web设计技巧以及入门</a></li><li><a href="/news/s/2013-09-04/606.html" title="程序员应该如何高效的工作学习">程序员应该如何高效的工作学习</a></li><li><a href="http://www.bestcssbuttongenerator.com" title="Webarti CSS3 Button Maker">Webarti CSS3 Button Maker</a></li><li><a href="/jstt/css3/2013-07-10/370.html" title="html5 css3 web标准基础教程入门">html5 css3 web标准基础教程入门</a></li><li><a href="/jstt/css3/2013-07-07/363.html" title="学习Html5建站教程（一）Html5简介">学习Html5建站教程（一）Html5简介</a></li><li><a href="/jstt/css3/2013-07-07/364.html" title="学习Html5建站教程（二）Html5 语法与规则">学习Html5建站教程（二）Html5 语法与规则</a></li><li><a href="/jstt/css3/2013-07-07/365.html" title="学习Html5建站教程（三）Html5博客页面设计之理论">学习Html5建站教程（三）Html5博客页面设计之理论</a></li><li><a href="/jstt/css3/2013-07-08/368.html" title="学习Html5建站教程（四）用HTML5做个人的网站">学习Html5建站教程（四）用HTML5做个人的网站</a></li><li><a href="/download/case/company/2013-06-13/262.html" title="3dst web">3dst web</a></li><li><a href="/jstt/bj/2013-06-08/178.html" title="Web Color Design——设计师谈网页配色艺术">Web Color Design——设计师谈网页配色艺术</a></li>      </ul>
    </div>
<div class="blank"></div>
<div class="ad">
<script type="text/javascript">
var cpro_id="u2064486";
(window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"700",rsi1:"250",pat:"6",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"1",adp:"1",ptt:"0",titFF:"%E5%BE%AE%E8%BD%AF%E9%9B%85%E9%BB%91",titFS:"",rss2:"#000000",titSU:"0",ptbg:"90",piw:"0",pih:"0",ptp:"0"}
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script></div>

  </div>
  </div>
    <%@ include file="/common/right.jsp"%>
</article>
<jsp:include page="/common/foot.jsp"/>
</body>
</html>
