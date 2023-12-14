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

<form action="/w_az/home/thanhToan" method="post">
	<h3>Giỏ hàng của ${sessionScope.user.hoTen}</h3>
		<p class="alert alert-info">tap rồi mới được mua nhé bạn iu ♥</p>
		<table class="table table-hover mb-5">
				<thead>
					<th>#</th>
					<th>Tên sản phẩm</th>
					<th>Màu sắc</th>
					<th>Kích thước</th>
					<th>Đơn giá</th>
					<th>Số lượng</th>
					<th>Thành tiền</th>
					<th>hình ảnh</th>
					<th>Thao tác</th>
				</thead>
				<tbody>					
					<c:forEach items="${ hdctLstInUser }" var="hdctLstInUser">
						<tr>
							<td>
								<input type="checkbox" class="form-check-input" name="cboThanhToan" value="${ hdctLstInUser.id }">
							</td>
							<td>${ hdctLstInUser.product.ten }</td>
							<td>${ hdctLstInUser.product.mauSac }</td>
							<td>${ hdctLstInUser.product.kichThuoc }</td>
							<td>${ hdctLstInUser.product.donGia }</td>
							<td>${ hdctLstInUser.soLuong }</td>
							<td>${ hdctLstInUser.thanhTien }</td>
							<td>
								<img class="card-img-top" style="width: 100px;height: 150px" src="/w_az/img/${ hdctLstInUser.product.img }">
							</td>
							<td>
								<a class="btn btn-danger" 
									href="/w_az/home/deleteProductByUser?id=${ hdctLstInUser.id }">
										bỏ sản phẩm này
								</a>
							</td>
						</tr>
					</c:forEach>
					<tr>			
						<td colspan="8"></td>
						<td><button class="btn btn-primary">Thanh toán</button></td>
					</tr>						
				</tbody>					
		</table>
</form>	