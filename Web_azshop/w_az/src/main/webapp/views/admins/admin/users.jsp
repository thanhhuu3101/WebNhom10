<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"
    %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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

<form method="POST"
		action="/w_az/user/store">
		<div class="form-group">
			<label>Họ tên:</label>
			<input type="text" name="hoTen" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Địa chỉ:</label>
			<input type="text" name="diaChi" class="form-control"/>
		</div>
		<div class="form-group">
			<label>SĐT:</label>
			<input type="text" name="sdt" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Email:</label>
			<input type="email" name="email" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Giới tính</label>
			<input type="radio" name="gioiTinh" value="1" class="form-check-input" />
			<label>Nam</label>
			<input type="radio" name="gioiTinh" value="0" class="form-check-input"/>
			<label>Nữ</label>
		</div>
		<div class="form-group">
			<label>Quyền quản trị:</label>
			<input type="radio" name="role" value="1" class="form-check-input"/>
			<label>Admin</label>
			<input type="radio" name="role" value="0" class="form-check-input"/>
			<label>User</label>
		</div>
		<div class="form-group mt-2">
			<button class="btn btn-primary">Thêm mới</button>
			<button class="btn border-dark" type="reset">Xóa form</button>
		</div>
	</form>
	
<c:if test="${ empty ds }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty ds }">
	<table class="table table-hover">
		<thead>
			<th>Họ tên</th>
			<th>Giới tính</th>
			<th>SĐT</th>
			<th>Email</th>
			<th>Địa chỉ</th>
			<th>Quyền quản trị</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ ds }" var="user">
				<tr>
					<td>${ user.hoTen }</td>
					<td>
						<c:choose>
							<c:when test="${ user.gioiTinh == 1 }">Nam</c:when>
							<c:when test="${ user.gioiTinh == 0 }">Nữ</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose>
					</td>
					<td>${ user.sdt }</td>
					<td>${ user.email }</td>
					<td>${ user.diaChi }</td>
					<td>
						<c:choose>
							<c:when test="${ user.role == 1 }">Admin</c:when>
							<c:when test="${ user.role == 0 }">User</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose>
					</td>
					<td>
						<a class="btn btn-primary" 
						href="/w_az/user/editUser?id=${ user.id }">
						Cập nhật</a>
						<a class="btn btn-danger" 
						href="/w_az/user/delete?id=${ user.id }">
							Xóa
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>