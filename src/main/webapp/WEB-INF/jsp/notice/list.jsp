<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style type="text/css">
#updatePassword{box-shadow: 10px 10px 5px #888888;top:30%;left:29%;position:fixed;z-index:111;width:470px;height:220px;display: none;border:1px solid #0080ff;background:#fff;}
.formControls{width: 80%;}
</style>
<title>公告列表</title>
</head>
<body>
<div class="pd-20">
    <table class="table table-border table-bordered table-bg table-hover">
        <thead>
            <tr>
                <th>公告内容</th>
                <th>状&nbsp;&nbsp;态</th>
                <th>操&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn size-MINI btn-secondary radius" onclick="window.location='noticeAdd'"> 添加</button></th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="noticeList" items="${noticeList }">
                <tr>
                    <td>${noticeList.account}</td>
                  
                    <c:choose>
                    	<c:when test="${noticeList.isDisabled}">
                    		<td><span class="label label-success radius" style="background-color: red">禁用</span></td>
                    	</c:when>
                    	<c:otherwise>
                    		<td><span class="label label-success radius">已启用</span></td>
                    	</c:otherwise>
                    </c:choose>
                    
                    <td>
                        <shiro:hasPermission name="/notice/delete.do">
                        <button class="btn size-MINI btn-danger radius" onclick="noticeDel(${noticeList.id})">删除</button>&nbsp;                        
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/notice/updateDisabled.do">
                        <button class="btn size-MINI btn-warning radius" onclick="window.location='updateDisabled.do?id=${noticeList.id}'">禁用/启用</button>
                        </shiro:hasPermission>
                    </td>
                </tr>
             </c:forEach>  

        </tbody>
    </table>
    <jsp:include page="/WEB-INF/jsp/page.jsp" flush="true"/>
</div>
          
            
<script type="text/javascript" src="<%=ctxPath %>/js/notice.js"></script>

</body>
</html>