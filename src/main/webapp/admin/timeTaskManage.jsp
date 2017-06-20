<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客类别管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	
	function deleteBlog(){
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
							url:"${pageContext.request.contextPath}/blogTypeAdminController/delete.do",
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
	
	function openBlogTypeModifyWindow(){
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要修改的数据！");
			return;
		}else if(selectedRows.length>1){
			$.messager.alert("系统提示","最多选择一条数据！");
			return;
		}else{
			$("#dlg").dialog("open").dialog("setTitle","编辑博客类别信息");
			 var row=selectedRows[0];
			 $("#fm").form("load",row);
		}
	}
	
	function openBlogTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加博客类别信息");
	}
	
	function saveBlogType(){
		$("#fm").form("submit",{
			url:"${pageContext.request.contextPath}/blogTypeAdminController/save.do",
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(data){
				var data=eval('('+data+')');
				if(data.success){
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败！");
					resetValue();
					$("#dlg").dialog("close");
					return;
				}
				
			},
			error:function(){
				$.messager.alert("系统提示","操作失败！");
				resetValue();
				$("#dlg").dialog("close");
				return;
			}
		});
	}

    function start(){
        $.ajax({
            type:"POST",
            url:"https://www.oschina.net/action/openapi/blog_list",
			data:{
                "access_token":"abf05bf7-4502-4560-942d-b2a98c837532",
                "page":1,
                "pageSize":20,
                "dataType":"json"
			},
			dataType:"JSON",
			success:function (data) {
				JSON.stringify(data);
            }
		});
    }

	function resetValue(){
		 $("#blogTypeName").val("");
		 $("#id").val("");
	 }
	
	function closeBlogTypeDialog(){
		$("#dlg").dialog("close");
	}
	
</script>
</head>
<body style="margin: 1px">
 <div id="tb">
 	<div>
 		<a href="javascript:void(0)" onclick="start()" class="easyui-linkbutton" iconCls="icon-search" plain="true">开始</a>
 	</div>
	 <div>
		 <a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">停止</a>
	 </div>
	 <div>
		 <a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	 </div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:500px;height:180px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>博客类别名称：</td>
   			<td><input type="text" id="blogTypeName" name="blogTypeName" class="easyui-validatebox" required="true"/></td>
   		</tr>
   	</table>
   	<input type="hidden" id="id" name="id" >
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveBlogType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
</body>
</html>