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

<form method="POST"
		action="/w_az/user/update?id=${ user.id }">
	<div>
		<label>Họ tên</label>
		<input type="hidden" />
		<input type="text" name="hoTen" value="${ user.hoTen }" class="form-control"/>
	</div>
	<div>
		<label>Địa chỉ</label>
		<input type="text" name="diaChi" value="${ user.diaChi }" class="form-control"/>
	</div>
	<div>
		<label>SĐT</label>
		<input type="text" name="sdt" value="${ user.sdt }" class="form-control"/>
	</div>
	<div>
		<label>Email</label>
		<input type="email" name="email" value="${ user.email }" class="form-control"/>
	</div>
	<div>
		<label>Giới tính</label>
		<input type="radio" name="gioiTinh" value="1"
			${ user.gioiTinh == 1 ? "checked" : "" } class="form-check-input"/>
		<label>Nam</label>
		<input type="radio" name="gioiTinh" value="0"
			${ user.gioiTinh == 0 ? "checked" : "" } class="form-check-input"/>
		<label>Nữ</label>
	</div>
	<div>
		<label>Quyền quản trị</label>
		<input type="radio" name="role" value="1"
			${ user.role == 1 ? "checked" : "" } class="form-check-input"/>
		<label>Admin</label>
		<input type="radio" name="role" value="0"
			${ user.role == 0 ? "checked" : "" } class="form-check-input"/>
		<label>User</label>
	</div>
	<div class="form-group mt-2">
			<button class="btn btn-primary">Cập nhật</button>
			<button class="btn border-dark" type="reset">Xóa form</button>
		</div>
</form>