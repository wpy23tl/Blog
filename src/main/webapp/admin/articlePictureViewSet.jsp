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
        function closeDialog(){
            $("#dlg1").dialog("close");
        }

        function saveArticlePictureViewSet(){
            var g = $('#cc1').combogrid('grid');
            var rz = g.datagrid('getSelected');	// 获取选择的行
            var id =rz.id;
            $("#fm1").form("submit",{
                url:"${pageContext.request.contextPath}/articlePictureViewSettingAdminController/save.do?id="+id,
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

        function openSetArticlePictureViewDialog(){
            $("#dlg1").dialog("open").dialog("setTitle","添加推荐博客");
            var selectedRows = $("#dg").datagrid("getSelections");
            var row=selectedRows[0];
            var sc=row.id
            $("#cc1").combogrid("setValue",sc);
            var bannerName =row.bannerName;
            var srcbanner ="${pageContext.request.contextPath}/bannerImages/"+bannerName;
            $("#preview1").attr("src",srcbanner);
        }

        function formatBlogType(val,row){
            return val.typeName;
        }

        function formatTitle(val,row){
            return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.id+".html'>"+val+"</a>"
        }

        function openBlogModifyTab(){

            var selectedRows=$("#dg").datagrid("getSelections");
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一个要修改的博客！");
                return;
            }
            var row=selectedRows[0];
            window.parent.openTab('修改博客','/admin/blog/updateBlog.do?id='+row.id,'icon-writeblog');


        }

        //下面用于图片上传预览功能
        function setImagePreview1(avalue) {
            var docObj=document.getElementById("doc1");
            var imgObjPreview=document.getElementById("preview1");
            if(docObj.files &&docObj.files[0])
            {
                //火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '285px';
                imgObjPreview.style.height = '220px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();

                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
            }
            else
            {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                var localImagId = document.getElementById("localImag");
                //必须设置初始大小
                localImagId.style.width = "285px";
                localImagId.style.height = "220px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try{
                    localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch(e)
                {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
            return true;
        }




	</script>
</head>
<body style="margin: 1px">
<table id="dg" title="博客管理" class="easyui-datagrid"
	   fitColumns="true" pagination="true" rownumbers="true"
	   url="${pageContext.request.contextPath}/blogAdminController/getAllList.do" fit="true" toolbar="#tb">
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
<div id="dlg1" class="easyui-dialog" style="width:820px;height:420px;padding: 10px 20px"
	 closed="true" buttons="#dlg-buttons1">

	<form id="fm1" method="post" enctype="multipart/form-data">
		博客选择:
		<select id="cc1" class="easyui-combogrid" name="dept" style="width:250px;" pagination="true"  readonly="true"
				data-options="
            panelWidth:650,
            panelHeight:320,
            value:'',
            idField:'id',
            textField:'blogTitle',
            url:'${pageContext.request.contextPath}/blogAdminController/getAllList.do',
            columns:[[
                {field:'id',title:'编号',width:60},
                {field:'blogTitle',title:'标题',width:200},
                {field:'createTime',title:'发布日期',width:120},
                {field:'blogTypeName',title:'博客类别',width:100}
            ]]
        "></select><br/>

		图片       :<input type="file" name="pictureFile1" id="doc1" style="width:150px;" onchange="javascript:setImagePreview1();">
		<div id="localImag"><img id="preview1" src="${pageContext.request}"  width="150" height="180" style="display: block; width: 150px; height: 180px;"></div>
	</form>
</div>

<div id="tb">
	<div>
		<a href="javascript:void(0)" onclick=" openSetArticlePictureViewDialog()"  class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置预览图</a>
	</div>
	<div>
		&nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>
<div id="dlg-buttons1">
	<a href="javascript:saveArticlePictureViewSet()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>