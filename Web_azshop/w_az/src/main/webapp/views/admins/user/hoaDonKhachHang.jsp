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

<form action="/w_az/hoaDon/DongYDonHang" method="post">
		<table class="table table-hover mb-5">
				<thead>
					<th>#</th>					
					<th>Họ tên khách hàng</th>
					<th>Giới tính</th>
					<th>Địa chỉ</th>
					<th>Số điện thoại</th>
					<th>email</th>					
					<th>Tổng tiền</th>
					<th colspan="2">Thao tác</th>
				</thead>
				<tbody>					
					<c:forEach items="${ hd }" var="hd">
						<tr>
							<td>
								<input type="checkbox" class="form-check-input" name="cboDongy" value="${ hd.id }">
							</td>
							<td>${ hd.user.hoTen }</td>
							<td>${ hd.user.gioiTinh }</td>
							<td>${ hd.user.diaChi }</td>
							<td>${ hd.user.sdt }</td>
							<td>${ hd.user.email }</td>
							<td>${ hd.tongTien }</td>
							<td>
								<a class="btn btn-primary" 
									href="/w_az/hoaDon/showHoaDonChiTiet?id=${ hd.id }">
										Xem hóa đơn chỉ tiết
								</a>
							</td>
							<td>
								<a class="btn btn-danger" 
									href="/w_az/hoaDon/HuyBoDonHang?id=${ hd.id }">
										Hủy bỏ đơn hàng
								</a>
							</td>							
						</tr>
					</c:forEach>
					<tr>			
						<td><button class="btn btn-primary">Đồng ý nhận đơn hàng</button></td>
					</tr>						
				</tbody>					
		</table>
</form>	