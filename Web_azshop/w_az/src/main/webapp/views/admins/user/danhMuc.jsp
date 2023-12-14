<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<form method="get" action="">
	<div >
		<div class="container">
			<c:if test="${ !empty dsP }">
				<div class="col-3 offset-9 mt-5 mtb-3">
					<form action="/w_az/home/find">
						<label>tìm kiếm</label>
						<input type="text" name="ten">
						<button class="btn btn-primary">lọc</button>
					</form>
				</div>
				
				<div>
					<label>Danh mục:</label>
					<a class="btn btn-default border-dark" href="/w_az/home/trangChu">Tất cả</a>
					<c:forEach items="${ categoryName }" var="categoryName">
						<a class="btn btn-default border-dark" href="/w_az/home/danhMuc?id=${ categoryName.id }">${ categoryName.ten }</a>
					</c:forEach>
				</div>
				
				<div class="col-12 row">
					<c:forEach items="${ dsP }" var="product">
							<div class="col-4 mt-3 mb-3">
								<div class="card">
								  	<img src="/w_az/img/${product.img}" class="card-img-top" alt="...">
								  	<div class="card-body">
								    	<h5 class="card-title">${ product.ten }</h5>
								    	<p class="card-text">
								    		Danh mục: ${ product.category.ten } <br>
								    		Kích thước: ${ product.kichThuoc } <br>
								    		Màu sắc: ${ product.mauSac } <br>
								    		<hr>
								    		Số lượng: ${ product.soLuong } <br>
								    		Đơn giá: ${ product.donGia } / 1 cái <br>
								    	</p>
								    	<div class="row">
									    	<div class="col-5">
									    		<label class="col-6">số lượng:</label><input class="col-6" type="number" name="soLuong">
									    	</div>
									    	<div class="col-7">
									    		<label class="col-6">thành tiền:</label>
									    	</div>
								    	</div>
								    	<hr>
								    	<div class="col-12 offset-7 mt-1">
								    		<a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
								    	</div>
									</div>
								</div>
							</div>
						</c:forEach>
				</div>
				<div class="offset-10 col-2">
				<button class="btn btn-light border-dark" onclick="">Thanh toán</button>
			</div>
			</c:if>
		</div>
	</div>    
</form>
    
 