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
    
    <title>My JSP 'Hs_edit.jsp' starting page</title>
    
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
				<s:if test='#request.hs==null'>添加成员信息</s:if>
				<s:else>编辑成员信息</s:else>
				</legend>
			</fieldset>

			<form class="layui-form" action="hs_edit.action" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">管理员名称：</label>
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input"
						value="${requestScope.hs.name }">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码：</label>
					<div class="layui-input-inline">
						<input type="text" name="passwd" lay-verify="title" autocomplete="off" placeholder="请输入密码" class="layui-input"
						value="${requestScope.hs.passwd }">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态：</label>
					<div class="layui-input-inline">
						<select name="state" lay-filter="aihao">
							<s:if test='#request.hs.state=="0"'>
							<option value="0" selected="selected">不可用</option>
							<option value="1">可用</option>
						</s:if>
						<s:elseif test='#request.hs.state=="1"'>
							<option value="0">不可用</option>
							<option value="1" selected="selected">可用</option>
						</s:elseif>
						<s:else>
							<option value=""></option>
							<option value="0">不可用</option>
							<option value="1">可用</option>
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
