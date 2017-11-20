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
    
    <title>My JSP 'Article_Findll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/table.css" />
		<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>

  </head>
  
   <body>
		<div class="admin-main">
			<blockquote class="layui-elem-quote">
				<a class="layui-btn layui-btn-small" id="add" href="article_editor.action?arid">
					<i class="layui-icon">&#xe608;</i> 添加信息
				</a>
				<a class="layui-btn layui-btn-small" id="import">
					<i class="layui-icon">&#xe608;</i> 导入信息
				</a>
				<a class="layui-btn layui-btn-small">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息
				</a>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>新闻信息</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>
								栏目名
								</th>
								<th>新闻序号</th>
								<th>新闻标题</th>
								<th>新闻内容</th>
								<th>发布者</th>
								<th>发布日期</th>
								<th>
									<select>
										<option>状态</option>
										<option>可用</option>
										<option>不可用</option>
									</select>
								</th>
								<th>点击数</th>
								<th>置顶</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="arlist">
						<s:iterator value="#session.ar_list" var="ar">
							<tr>
								<td><input type="checkbox"></td>
								<td><s:property value="#ar.catalog.caname"/></td>
								<td><s:property value="#ar.arnumber"/></td>
								<td><s:property value="#ar.artitle"/></td>
								<td><s:property value="#ar.arcontent"/></td>
								<td><s:property value="#ar.aruser"/></td>
								<td><s:property value="#ar.artime"/></td>
								<td>
								<s:if test='#ar.arstate=="1"'>可用</s:if>
								<s:else>不可用</s:else>
								</td>
								<td><s:property value="#ar.clicks"/></td>
								<td style="text-align:center;"><i class="layui-icon" style="color:green;"></i></td>
								<td>
									<a href="#" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
									<a href="article_editor.action?arid=<s:property value="#ar.arid"/>" class="layui-btn layui-btn-mini">编辑</a>
									<a href="article_delete.action?arid=<s:property value="#ar.arid"/>" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page"></div>
			</div>
		</div>
	</body>
	<script>
			layui.config({
				base: 'plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {

				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});

				//page
				laypage({
					cont: 'page',
					pages: ${requestScope.row} //总页数
						,
					groups: 3 //连续显示分页数
						,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});

				$('#search').on('click', function() {
					parent.layer.alert('你点击了搜索按钮')
				});

				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
			});
		</script>
		<script type="text/javascript">
		$("#arlist").find("tr").each(function(){
		var td=$(this).children();
		var artitle=td.eq(3).text();
		var arcontent=td.eq(4).text();
		arcontent=arcontent.replace(/<[^>]+>/g, "");
		var lentitle=artitle.length;
		var lencontent=arcontent.length;
		var dian="";
		if(lencontent>10){
				lencontent=10;
				dian="...";
			}
			if(lentitle>5){
				lentitle=5;
				dian="...";
			}
		td.eq(3).html(artitle.substr(0, lentitle)+dian);
		td.eq(4).html(arcontent.substr(0, lencontent)+dian);
		})
		</script>
</html>
