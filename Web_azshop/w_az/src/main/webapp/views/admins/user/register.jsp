<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
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
		action="/w_az/register" class="col-12 row p-5 bg-light">
		<div class="mt-3">
			<label>Họ tên:</label>
			<input type="text" name="hoTen" class="form-control"/>
		</div>
		<div class="mt-3">
			<label>Password:</label>
			<input type="text" name="password" class="form-control"/>
		</div>
		<div class="mt-3">
			<label>Địa chỉ:</label>
			<input type="text" name="diaChi" class="form-control"/>
		</div>
		<div class="mt-3">
			<label>SĐT:</label>
			<input type="text" name="sdt" class="form-control"/>
		</div>
		<div class="mt-3">
			<label>Email:</label>
			<input type="email" name="email" class="form-control"/>
		</div>
		<div class="mt-3">
			<label>Giới tính</label>
			<input type="radio" name="gioiTinh" value="1" class="form-check-input"/>
			<label>Nam</label>
			<input type="radio" name="gioiTinh" value="0" class="form-check-input"/>
			<label>Nữ</label>
		</div>
		<div class="mt-3">
			<button class="btn btn-primary">Đăng ký</button>
			<button class="btn btn-default border-dark" type="reset">Xóa form</button>
		</div>
	</form>