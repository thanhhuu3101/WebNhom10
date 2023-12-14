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
<table class="table table-hover mb-5">
				<thead>				
					<th>Họ tên khách hàng</th>
					<th>Giới tính</th>
					<th>Địa chỉ</th>
					<th>Số điện thoại</th>
					<th>email</th>					
					<th>Tổng tiền</th>
					<th>Trạng thái</th>
					<th>Thao tác</th>
				</thead>
				<tbody>					
					<c:forEach items="${ hd }" var="hd">
						<tr>
							<td>${ hd.user.hoTen }</td>
							<td>
								<c:if test="${ hd.user.gioiTinh == 1 }">Nam</c:if>
								<c:if test="${ hd.user.gioiTinh == 0 }">Nữ</c:if>
							</td>
							<td>${ hd.user.diaChi }</td>
							<td>${ hd.user.sdt }</td>
							<td>${ hd.user.email }</td>
							<td>${ hd.tongTien }</td>
							<td>
								<c:if test="${ hd.status == 1 }">đặt hàng thành công</c:if>
								<c:if test="${ hd.status == 0 }">đơn đang chờ xử lí</c:if>
								
							</td>
							<td>
								<a class="btn btn-primary" 
									href="/w_az/hoaDon/showHoaDonChiTiet?id=${ hd.id }">
										Xem hóa đơn chỉ tiết
								</a>
							</td>						
						</tr>
					</c:forEach>					
				</tbody>					
	</table>