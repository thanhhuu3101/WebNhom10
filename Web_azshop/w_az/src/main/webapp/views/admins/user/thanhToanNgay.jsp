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
		action="/w_az/muaNgay?id=${ product.id }">
		<div class="col-12 row p-5 bg-light">
			<div class="col-4">
				<img src="/w_az/img/${product.img}" class="card-img-top" alt="...">
			</div>
			<div class="col-8">
				<div class="mt-3">
					<h3>tên sản phẩm: ${ product.ten }</h3>
				</div>
				<div class="mt-3">
					<h5>Họ tên khách hàng: ${ sessionScope.user.hoTen }</h5>
				</div>
				<div class="mt-3">
					<h5>Số điện thoại: ${ sessionScope.user.sdt }</h5>
				</div>	
				<div class="mt-3">
					<h5>Địa chỉ: ${ sessionScope.user.diaChi }</h5>
				</div>		
				<div class="mt-3">
					<h5>kích thước: ${ product.kichThuoc }</h5>
				</div>
				<div class="mt-3">
					<h5>Màu sắc: ${ product.mauSac }</h5>
				</div>
				<div class="mt-3">
					<h5>Đơn giá: ${ product.donGia }</h5>
				</div>
				<div class="form-group mt-3">
					<label>Số lượng:</label>
					<input type="number" name="soLuong" class="form-control"/>
				</div>
				<div class="form-group mt-3">
					<button class="btn btn-primary col-12 mt-2">Đặt hàng</button>
				</div>
			</div>	
		</div>	
</form>