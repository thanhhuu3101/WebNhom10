<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AZ</title>
	<link rel="stylesheet"
			href="/w_az/css/bootstrap.min.css" />
</head>
<body>
<div class="row mt-5">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<c:if test="${ empty sessionScope.user }">
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/w_az/home/trangChuNoLogin">Trang chủ</a></li>
						</c:if>
						<c:if test="${ !empty sessionScope.user }">
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/w_az/home/trangChu">Trang chủ</a></li>
						</c:if>
						
						<c:if test="${ sessionScope.user.role == 1 }">							
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Quản lý</a>
								<ul class="dropdown-menu" aria-labelled	by="navbarDropdown">
									<li><a class="dropdown-item" href="/w_az/user/users">Quản lý người dùng</a></li>
									<li><a class="dropdown-item" href="/w_az/category/categories">Quản lý danh mục</a></li>
									<li><a class="dropdown-item" href="/w_az/product/products">Quản lý sản phẩm</a></li>
								</ul>
							</li>
						</c:if>															
						<li class="nav-item dropdown">						
						<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownUser" role="button" 
						data-bs-toggle="dropdown" aria-expanded="false" >
							<c:if test="${ !empty sessionScope.user  }">
								${sessionScope.user.hoTen}
							</c:if>
							<c:if test="${ empty sessionScope.user  }">
								Tài khoản
							</c:if>
						</a>		         
						   <ul class="dropdown-menu" aria-labelledby="navbarDropdownUser">
							    <c:if test="${ !empty sessionScope.user  }">
							    	<li><a class="dropdown-item" href="/w_az/dmk" >Đổi mật khẩu</a></li> 
							    	<li><a class="dropdown-item" href="/w_az/logout" >Đăng xuất</a></li>   
							    </c:if>
							    <c:if test="${ empty sessionScope.user  }">
							    	<li><a class="dropdown-item" href="/w_az/register">Đăng ký</a></li>	
							    	<li><a class="dropdown-item" href="/w_az/login">Đăng nhập</a></li>
							    </c:if>
						   </ul>
						</li>
						<c:if test="${ sessionScope.user.role==0 }">
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/w_az/home/gioHangInUser?id=${ sessionScope.user.id }">giỏ hàng của bạn</a></li>
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/w_az/hoaDon/lichSuDatHang?id=${ sessionScope.user.id }">Lịch sử đặt hàng</a></li>
						</c:if>
						<c:if test="${ sessionScope.user.role==1 }">
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/w_az/hoaDon/hoaDonKhachHang">Hóa đơn khách hàng</a></li>
						</c:if>		
					</ul>											  			        									                        
				</div>
			</div>
	</nav>
</div>
	
<div class="row">
  <img src="https://tuyensinh.tvu.edu.vn/uploads/news/2021_09/quy-trinh-1.jpg" class="w-100" alt="...">
</div>


<div class="container">
	<div class="col-12">
		<jsp:include page="${ view }"></jsp:include>
	</div>
</div>




<div class="row mt-2">
      <img src="https://avatars.githubusercontent.com/u/75773455?s=280&v=4" style="width: 100%;" alt="">
      </div>
    </footer>
</div>				

		<script src="/w_az/js/jquery.min.js"></script>
		<script src="/w_az/js/popper.min.js"></script>
		<script src="/w_az/js/bootstrap.min.js"></script>
</body>
</html>