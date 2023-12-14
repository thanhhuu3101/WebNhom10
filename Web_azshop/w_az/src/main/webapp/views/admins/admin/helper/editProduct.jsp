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
		action="/w_az/product/update?id=${ product.id }">		
			<div class="form-group">
				<label>tên sản phẩm:</label>
				<input type="text" name="ten" value="${ product.ten }" class="form-control"/>
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
				<input type="text" name="mauSac" value="${ product.mauSac }" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Kích thước:</label>
				<input type="text" name="kichThuoc" value="${ product.kichThuoc }" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Đơn giá:</label>
				<input type="number" name="donGia" value="${ product.donGia }" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Số lượng:</label>
				<input type="number" name="soLuong" value="${ product.soLuong }" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Hình ảnh:</label>
				<input type="file" name="img" value="${ product.img }" class="form-control"/>
			</div>
			<div class="col-12 form-group mt-2">
				<button class="btn btn-primary">Cập nhật</button>
				<button class="btn border-dark"type="reset">Xóa form</button>
			</div>
	</form>