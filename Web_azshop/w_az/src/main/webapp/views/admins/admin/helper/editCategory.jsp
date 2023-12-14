<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:if test="${ !empty sessionScope.error }">
	<div class="alert alert-danger">
		${ sessionScope.error }
	</div>
	<c:remove var="error" scope="session"/>
</c:if>

<form method="post" action="/w_az/category/update?id=${ category.id }">
	<div class="form-group">
		<label>Tên danh mục:</label> 
		<input type="text" name="ten" class="form-control" value="${ category.ten }">
	</div>
	<div class="form-group">
		<label>Tên người tạo:</label> 
		<select name="user_id" class="form-select">
			<c:forEach items="${ dsUser }" var="user">
				<option value="${ user.id }">${ user.hoTen }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group mt-2">
		<button class="btn btn-primary" class="form-control">Cập nhật</button>
		<button class="btn border-dark" type="reset" class="form-control">Tạo mới</button>
	</div>
</form>
