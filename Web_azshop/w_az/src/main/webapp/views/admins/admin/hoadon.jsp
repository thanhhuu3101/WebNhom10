<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<h2>
	<fmt:formatDate value="${ now }" pattern="dd/MM/yyyy"/>
</h2>

<c:if test="${ !empty sessionScope.error }">
	<div class="alert alert-danger">
		${ sessionScope.error }
	</div>
	<c:remove var="error" scope="session"/>
</c:if>

<c:if test="${ !empty sessionScope.message }">
	<div class="alert alert-success">
		${ sessionScope.message }
	</div>
	<c:remove var="message" scope="session"/>
</c:if>

<form method="post" action="/w_az/hoaDon/store">
	<div class="form-group">
		<label>Tên người dùng:</label> 
		<input type="text" name="userId" value="${ sessionScope.user.hoTen }" disabled="disabled" class="form-control">
	</div>
	<div class="form-group">
		<label>Ngày bán:</label> 
		<input type="text" name="ngayBan" value="${ sessionScope.now }" disabled="disabled" class="form-control">
	</div>
	<div class="form-group">
		<label>Tổng tiền:</label> 
		<input type="text" name="tongTien" value="${ sessionScope. }" disabled="disabled" class="form-control">
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
		<button class="btn btn-primary" class="form-control">Thêm mới</button>
		<button class="btn border-dark" type="reset" class="form-control">Tạo mới</button>
	</div>
</form>

<c:if test="${ empty dsCategory }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty dsCategory }">
	<table class="table table-hover">
		<thead>
			<th>Tên thể loại:</th>
			<th>Tên người tạo:</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ dsCategory }" var="ds">
				<tr>
					<td>${ ds.ten }</td>
					<td>${ ds.user.hoTen }</td>
					<td>
						<a class="btn btn-primary" 
						href="/w_az/category/editCategories?id=${ ds.id }">
						Cập nhật</a>
						<a class="btn btn-danger" 
						href="/w_az/category/delete?id=${ ds.id }">
							Xóa
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
