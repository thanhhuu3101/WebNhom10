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
		action="/w_az/product/store">
		<div>
			<div class="form-group">
				<label>tên sản phẩm:</label>
				<input type="text" name="ten" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Tên danh mục:</label> 
				<select name="category_id" class="form-select">
					<c:forEach items="${ dsC }" var="category">
						<option value="${ category.id }">${ category.ten }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Màu sắc:</label>
				<input type="text" name="mauSac" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Kích thước:</label>
				<input type="text" name="kichThuoc" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Đơn giá:</label>
				<input type="number" name="donGia" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Số lượng:</label>
				<input type="number" name="soLuong" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Hình ảnh:</label>
				<input type="file" name="img" class="form-control"/>
			</div>
			<div class="col-12 form-group mt-2">
				<button class="btn btn-primary">Thêm mới</button>
				<button class="btn border-dark" type="reset">Xóa form</button>
			</div>
		</div>
</form>
	
<c:if test="${ empty dsP }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty dsP }">
	<table class="table table-hover">
		<thead>
			<th>Tên sản phẩm</th>
			<th>danh mục</th>
			<th>Màu sắc</th>
			<th>Kích thước</th>
			<th>Đơn giá</th>
			<th>Số lượng</th>
			<th>Hình ảnh</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ dsP }" var="product">
				<tr>
					<td>${ product.ten }</td>
					<td>${ product.category.ten }</td>
					<td>${ product.mauSac }</td>
					<td>${ product.kichThuoc }</td>
					<td>${ product.donGia }</td>
					<td>${ product.soLuong }</td>
					<td>${ product.img }</td>
					<td>
						<a class="btn btn-primary" 
						href="/w_az/product/editProduct?id=${ product.id }">
						Cập nhật</a>
						<a class="btn btn-danger" 
						href="/w_az/product/delete?id=${ product.id }">
							Xóa
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>