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
    
    <title>My JSP 'Article_edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
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
	
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>

  </head>
  
  <body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>
				<s:if test='#request.article==null'>添加新闻信息</s:if>
				<s:else>编辑新闻信息</s:else>
				</legend>
			</fieldset>

			<form class="layui-form" action="article_edit.action" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">栏目名：</label>
					<div class="layui-input-inline">
						<select name="catalog.caid" lay-filter="aihao">
						<option></option>
							<s:iterator value="#session.ca_list" var="ca">
								<s:if test="#ca.caid==#request.article.catalog.caid">
									<option value="<s:property value="#ca.caid"/>" selected="selected"><s:property value="#ca.caname"/></option>
								</s:if>
								<s:else>
								<option value="<s:property value="#ca.caid"/>"><s:property value="#ca.caname"/></option>
								</s:else>
							</s:iterator>
						</select>
					</div>
					<label class="layui-form-label">新闻序号：</label>
					<div class="layui-input-inline">
						<input type="text" name="arnumber"   class="layui-input"
						value="${requestScope.article.arnumber }">
					</div>
				</div>
				
				<div class="layui-form-item">
						<label class="layui-form-label">验证日期</label>
						<div class="layui-input-inline">
							<input type="text" name="artime" id="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				<div class="layui-form-item">
					<label class="layui-form-label">发布者：</label>
					<div class="layui-input-inline">
						<input type="text" name="aruser"  class="layui-input"
						value="${requestScope.article.aruser }">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态：</label>
					<div class="layui-input-inline">
						<select name="arstate" lay-filter="aihao">
							<s:if test='#request.article.arstate=="0"'>
							<option value="0" selected="selected">不可用</option>
							<option value="1">可用</option>
						</s:if>
						<s:elseif test='#request.article.arstate=="1"'>
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
					<label class="layui-form-label">新闻标题：</label>
					<div class="layui-input-block">
						<input type="text" name="artitle"  autocomplete="off"  class="layui-input"
						value="${requestScope.article.artitle }">
						
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">新闻内容：</label>
					<div class="layui-input-block">
    					<script id="editor" type="text/plain" name="arcontent" style="width:800px;height:500px;">
							
						</script>
					</div>
				</div>
				
	
				<div class="layui-form-item">
					<label class="layui-form-label">点击数：</label>
					<div class="layui-input-inline">
						<input type="text" name="clicks"  autocomplete="off" class="layui-input"
						value="${requestScope.article.clicks }">
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
					var ue= UE.getEditor('editor');
					ue.ready(function(){
					ue.setContent('${requestScope.article.arcontent }');
					})
					
			});
		</script>
</html>
