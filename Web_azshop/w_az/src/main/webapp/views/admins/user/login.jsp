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

<c:if test="${ !empty sessionScope.message }">
	<div class="alert alert-success">
		${ sessionScope.message }
	</div>
	<c:remove var="message" scope="session"/>
</c:if>

<form action="/w_az/login" method="post" class="col-6 offset-3 mt-5 mb-5 card bg-light">
	<div class="container">
		<div class="form-group">
			<label>Email</label>
			<input type="email" name="email" class="form-control">
		</div>
		<div class="form-group">
			<label>Password</label>
			<input type="password" name="password" class="form-control">
		</div>
		<div class="form-group">
			<a href="">Quên mật khẩu</a>
		</div>
		<div class="form-group mt-2">
			<button class="btn btn-primary offset-5 mb-3">Đăng nhập</button>
		</div>
	</div>
</form>