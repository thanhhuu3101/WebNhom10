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

<a href="/w_az/hoaDon/hoaDonKhachHang" class="btn btn-default border-dark">Quay về</a>	

<table class="table table-hover mb-5">
				<thead>				
					<th>Tên người mua</th>
					<th>Giới tính</th>
					<th>Địa chỉ</th>
					<th>số điện thoại</th>
					<th>Email</th>
					<th>Tên sản phẩm</th>
					<th>Màu sắc</th>
					<th>Kích thước</th>
					<th>Đơn giá</th>
					<th>Số lượng</th>
					<th>Thành tiền</th>
					<th>hình ảnh</th>
				</thead>
				<tbody>					
					<c:forEach items="${ hdct }" var="hdct">
						<tr>
							<td>${ hdct.user.hoTen }</td>
							<td>
								<c:if test="${ hdct.user.gioiTinh==1 }">Nam</c:if>
								<c:if test="${ hdct.user.gioiTinh==0 }">Nữ</c:if>
							</td>
							<td>${ hdct.user.diaChi }</td>
							<td>${ hdct.user.sdt }</td>
							<td>${ hdct.user.email }</td>
							<td>${ hdct.product.ten }</td>
							<td>${ hdct.product.mauSac }</td>
							<td>${ hdct.product.kichThuoc }</td>
							<td>${ hdct.product.donGia }</td>
							<td>${ hdct.soLuong }</td>
							<td>${ hdct.thanhTien }</td>
							<td>
								<img class="card-img-top" style="width: 100px;height: 150px" src="/w_az/img/${ hdct.product.img }">
							</td>
						</tr>
					</c:forEach>					
			</tbody>										
</table>