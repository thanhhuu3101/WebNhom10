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

<div>
	<div class="container">
		<c:if test="${ !empty dsProduct }">
			<div class="col-3 offset-9 mt-5 mb-3">
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
				<c:forEach items="${ dsProduct }" var="product">		
						<div class="col-4 mt-3 mb-3">
							<div class="card">
							  	<img src="/w_az/img/${ product.img }" class="card-img-top" alt="...">
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
										<a class="btn btn-light border-dark mt-2 col-6" 
											href="/w_az/home/createProduct?id=${ product.id }">
										Thêm vào giỏ hàng</a>	
										<a class="btn btn-primary mt-2 col-6 " 
											href="/w_az/createMuaNgay?id=${ product.id }">
										Mua ngay</a>
									</div>
								</div>
							</div>
						</div>						
					</c:forEach>
				</div>
			</c:if>
		</div>
</div>    

    
 