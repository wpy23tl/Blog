<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>写博客</title>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div{
            width:100%;
        }
    </style>
<script type="text/javascript">
	
	function submitData(){
		var title= $("#blogTitle").val();
		var blogTypeId=$("#blogTypeId").combobox("getValue");
		var content=UE.getEditor('editor').getContent();
		var id="${id}";
		if(title==null || title==''){
			alert("请输入标题！");
		}else if(blogTypeId==null || blogTypeId==''){
			alert("请选择博客类别！");
		}else if(content==null || content==''){
			alert("请输入内容！");
		}else{
			$.ajax({
				
				type:"POST",
				data:{"blogTitle":title,"blogTypeId":blogTypeId,"blogContent":content,"id":id,"summary":UE.getEditor('editor').getContentTxt().substr(0,155)},
				url:"${pageContext.request.contextPath}/blogAdminController/save.do",
				dataType:"JSON",
				success:function(data){
					if(data.success){
						$.messager.alert("系统提示","发布成功！");
					}else{
						$.messager.alert("系统提示","发布失败！");
					}
				},
				error:function(){
					$.messager.alert("系统提示","操作失败！");
				}
			});
		}
	}

</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
<form id="fm" >
 <br/>
   			<br/>
   			博客标题：
   			<input type="text" id="blogTitle" name="blogTitle" style="width: 400px;" value="${blogTitle}"/><br/>
   			<br/>
   			<br/>
   			所属类别：
   				<select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="blogTypeId" editable="false" panelHeight="auto" >
					<option value="">请选择博客类别...</option>	
					<c:forEach var="blogType" items="${blogTypeList}" >
				   		<option value="${blogType.id }">${blogType.blogTypeName }</option>	
				   </c:forEach>
                </select><br/>
             <br/>
   			<br/>
   			博客内容：
   			 <br/>
   			<br/>
   			<br/>
				   <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
				    <br/>
   			<br/>
   				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a>
   			
   	
</form>
</div>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
   
    ue.ready(function() {
    	//向编辑器设置内容
        ue.setContent('${blogContent}');
        var btid ="${blogTypeId}";
        //设置所属类别
    	$("#blogTypeId").combobox("setValue",btid);
    });
    
    
</script>
</body>
</html>