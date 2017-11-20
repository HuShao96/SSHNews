<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Images_edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<script type="text/javascript" src="plugins/layui/layui.js"></script>

  </head>
  
  <body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>
				<s:if test='#request.images==null'>添加图片信息</s:if>
				<s:else>编辑图片信息</s:else>
				</legend>
			</fieldset>

			<form class="layui-form" action="images_edit.action" enctype="multipart/form-data" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">图片标题：</label>
					<div class="layui-input-inline">
						<input type="text" name="imtitle"   <s:if test='#request.images.imtitle!=null&&#request.images.imstate==1'>readonly='readonly'</s:if>  placeholder="请输入图片标题" class="layui-input"
						value="${requestScope.images.imtitle }">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">图片名：</label>
					<div class="layui-input-inline">
						<input type="text" name="imname"   <s:if test='#request.images.imname!=null&&#request.images.imstate==1'>readonly='readonly'</s:if>  placeholder="请输入图片名"  class="layui-input"
						value="${requestScope.images.imname } ">   
					</div>
					    上传文件：<input type="file" name="image"><br/>
				</div>
				<s:if test='#request.images.imstate==1'>
					<img src="SystemPicture/${requestScope.images.imname } "/>
				</s:if>
				<s:elseif test='#request.images.imstate==2'>
					<img src="GeneralPicture/${requestScope.images.imname } " />
				</s:elseif>
					
				<div class="layui-form-item">
					<label class="layui-form-label">类别：</label>
					<div class="layui-input-inline">
						<select name="imstate" lay-filter="aihao">
						<s:if test='#request.images.imstate=="1"'>
							<option value="1" selected="selected">系统文件</option>
							<option value="2">普通文件</option>
						</s:if>
						<s:elseif test='#request.images.imstate=="2"'>
							<option value="2" selected="selected">普通文件</option>
							<option value="1">系统文件</option>
						</s:elseif>
						<s:else>
							<option value=""></option>
							<option value="1">系统文件</option>
							<option value="2">普通文件</option>
						</s:else>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>

	</body>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;

			});
		</script>
</html>
