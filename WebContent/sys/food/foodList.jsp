<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	<title>无线点餐平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
	<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/FoodServlet?method=findFoodName" method="post">
			<input type="text" name="keyword" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
       		<c:choose>
				<c:when test="${not empty foodList}">
					<c:forEach items="${pageBean.pageData}" var="item" varStatus="varsta">
						<tr class="TableDetail1">
							<td align="center">${item.id}</td>
							<td align="center">${item.foodName }</td>
							<td align="center">
								<c:forEach items="${foodTypes}" var="foodItem">
									<c:if test="${item.foodType_id == foodItem.id}"> ${foodItem.typeName} </c:if>
								</c:forEach>
							</td>
							<td align="center">${item.price }</td>
			                <td align="center">${item.mprice }</td>
							<td align="center">
								<a href="${pageContext.request.contextPath}/FoodServlet?method=updatePage&id=${item.id}"  class="FunctionButton">更新</a>				
								<a href="${pageContext.request.contextPath}/FoodServlet?method=delete&id=${item.id}" onClick="return delConfirm();"class="FunctionButton">删除</a>				
							</td>
						</tr>
		        	</c:forEach>
				</c:when>
				<c:otherwise >
					<tr>
						<td colspan="6" align="center">没有你要找找的数据，请先保存记录再查看！</td>
					</tr>
				</c:otherwise>	
			</c:choose>	
			<tr class="TableDetail1"></tr>
			<tr class="TableDetail1">
				<td colspan="1" align="center">第${pageBean.currentPage}/${pageBean.totalPage}页，共${pageBean.totalCount}条</td>
    			<td colspan="1"></td><td colspan="1"></td><td colspan="1"></td><td colspan="1"></td>
				<td colspan="1" align="right">
					<c:if test="${pageBean.currentPage > 1}">
						<div class="FunctionButton"><a href="${pageContext.request.contextPath}/FoodServlet?method=list&currPage=${pageBean.currentPage - 1}">上一页</a></div>
					</c:if>
					<c:if test="${pageBean.currentPage < pageBean.totalPage}">
						<div class="FunctionButton"><a href="${pageContext.request.contextPath}/FoodServlet?method=list&currPage=${pageBean.currentPage + 1}">下一页</a></div>
					</c:if>
				</td>
			</tr>
        </tbody>
    </table>
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/sys/food/saveFood.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
