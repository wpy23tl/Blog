<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	function formatBlogType(val,row){
		return val.typeName;
	}
	
	function formatTitle(val,row){
		return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.id+".html'>"+val+"</a>"
	}
	
	function openUpdateNoDialog(){
		
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要修改的数据！");
			return;
		}else if(selectedRows.length>1){
			$.messager.alert("系统提示","最多选择一条数据！");
			return;
		}else{
			$("#dlg1").dialog("open").dialog("setTitle","编辑推荐编号");
			 var row=selectedRows[0];
			 $("#fm1").form("load",row);
		}
		
	
	}
	
	
	function deleteRecommend(){
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds =[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id)
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",
				function(r){
					if(r){
						$.ajax({
							type:"POST",
							url:"${pageContext.request.contextPath}/bloggerRecommendAdminController/delete.do",
							data:{ids:ids},
							success:function(){
								$.messager.alert("系统提示","数据已成功删除！");
								$("#dg").datagrid("reload");
							},
							error:function(){
								$.messager.alert("系统提示","数据删除失败！");
							}
							
						});	
					}
				});
		
	}
	
	function openRecommendDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加推荐博客");
	}
	
	function closeBlogTypeDialog(){
		$("#dlg").dialog("close");
	}
	
	function closeDialog(){
		$("#dlg1").dialog("close");
	}
	
	function saveRecommendBlog(){
		var selectedRows = $("#dg1").datagrid("getSelections");
		var strIds =[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id)
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要添加这<font color=red>"+selectedRows.length+"</font>条数据吗？",
				function(r){
					if(r){
						$.ajax({
							type:"POST",
							url:"${pageContext.request.contextPath}/bloggerRecommendAdminController/add.do",
							data:{ids:ids},
							success:function(){
								$.messager.alert("系统提示","数据已添加成功！");
								$("#dlg").dialog("close");
								$("#dg").datagrid("reload");
							},
							error:function(){
								$.messager.alert("系统提示","数据添加失败！");
							}
							
						});	
					}
				});
	}
	
	function resetValue(){
		 $("#blogTypeName").val("");
		 $("#id").val("");
	 }
	
	function updateRecommendNo(){
		$("#fm1").form("submit",{
			url:"${pageContext.request.contextPath}/bloggerRecommendAdminController/update.do",
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(data){
				var data=eval('('+data+')');
				if(data.success){
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg1").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败！");
					resetValue();
					$("#dlg1").dialog("close");
					return;
				}
				
			},
			error:function(){
				$.messager.alert("系统提示","操作失败！");
				resetValue();
				$("#dlg1").dialog("close");
				return;
			}
		});
	}
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="推荐博客管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/bloggerRecommendAdminController/getAllList.do" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="20" align="center">编号</th>
   		<th field="blogTitle" width="200" align="center" >标题</th>
   		<th field="createTime" width="50" align="center">发布日期</th>
   		<th field="blogTypeName" width="50" align="center" >博客类别</th>
   		<th field="recommendNo" width="50" align="center" >推荐序号</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:void(0)" onclick="openRecommendDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:void(0)" onclick="openUpdateNoDialog()"  class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:void(0)" onclick="deleteRecommend()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 	<div>
 		&nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
 		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:820px;height:420px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table id="dg1" title="博客管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/blogAdminController/getAllList.do" fit="false" >
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="20" align="center">编号</th>
   		<th field="blogTitle" width="200" align="center" >标题</th>
   		<th field="createTime" width="50" align="center">发布日期</th>
   		<th field="blogTypeName" width="50" align="center" >博客类别</th>
   	</tr>
   </thead>
 </table>
   	<input type="hidden" id="id" name="id" >
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveRecommendBlog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
 <div id="dlg1" class="easyui-dialog" style="width:500px;height:180px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons1">
   
   <form id="fm1" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>推荐编号：</td>
   			<td><input type="text" id="recommendNo" name="recommendNo" class="easyui-validatebox" required="true"/></td>
   		</tr>
   	</table>
   	<input type="hidden" id="id" name="id" >
   </form>
 </div>
 
 <div id="dlg-buttons1">
 	<a href="javascript:updateRecommendNo()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
</body>
</html>