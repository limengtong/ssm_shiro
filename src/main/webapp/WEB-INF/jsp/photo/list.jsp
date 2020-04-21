<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<style type="text/css">
#updateRole{box-shadow: 10px 10px 5px #888888;top:30%;left:29%;position:fixed;z-index:111;width:470px;height:220px;display: none;border:1px solid #0080ff;background:#fff;}
.formControls{width: 80%;}
</style>
<title>图片管理</title>
</head>
<body>
<div class="pd-20">
    <table class="table table-border table-bordered table-bg table-hover">
        <thead>
            <tr>
                <th>图片名称</th>
                <th>描&nbsp;&nbsp;述</th>
                <th>类&nbsp;&nbsp;型</th>
                <th>路&nbsp;&nbsp;径</th>
                <th>状&nbsp;&nbsp;态</th>
                <th>操&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn size-MINI btn-secondary radius" onclick="window.location='roleAdd'"> 添加</button></th>
            </tr>
        </thead>
        <tbody>
     <c:forEach var="photos" items="${photoList}">
                <tr>
                    <td>${photos.photoName }</td>
                    <td>${photos.photoAccount }</td>
                    <td id="phothType">${photos.photoType }</td>
                    <td>${photos.photoPath }</td>
                    <c:choose>
                    	<c:when test="${noticeList.isDisabled}">
                    		<td><span class="label label-success radius" style="background-color: red">禁用</span></td>
                    	</c:when>
                    	<c:otherwise>
                    		<td><span class="label label-success radius">已启用</span></td>
                    	</c:otherwise>
                    </c:choose>
                    <td>                   	
                        <button class="btn size-MINI btn-danger radius" onclick="roleDel(${photos.id})">删除</button>&nbsp;
                    </td>
                </tr>
	</c:forEach>
        </tbody>
    </table>
    <jsp:include page="/WEB-INF/jsp/page.jsp" flush="true"/>    
</div>


<!-- 修改图片 -->
<div id="updateRole" >
    <form action="" class="form form-horizontal">
        <input type="hidden" name="id" id="roleID" />
        <div class="row cl">
            <label class="form-label col-2">角色名称</label>
            <div class="formControls col-5">             
                <input type="text" class="input-text" name="roleName" id="roleName" />
            </div>    
        </div>

        <div class="row cl">
            <label class="form-label col-2">描&nbsp;&nbsp;述</label>
            <div class="formControls col-5">
            	<textarea name="description" class="textarea"  id="description" ></textarea>
            </div>         
        </div>

        <div class="row cl">
            <div class="col-9 col-offset-2">
                <input class="btn btn-primary radius" type="button" onclick="updateRole()" value="修改" />
                <input class="btn btn-default radius" type="button" value="关闭" onclick="closer()" style="margin-left: 30px;" />
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="<%=ctxPath %>/js/photo.js"></script>   

</body>
</html>