package w_az.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.websocket.Session;

import org.apache.commons.beanutils.BeanUtils;

import w_az.dao.CategoryDAO;
import w_az.dao.ProductDAO;
import w_az.dao.UserDAO;
import w_az.dao.hoaDonChiTietDAO;
import w_az.dao.hoaDonDAO;
import w_az.entities.Category;
import w_az.entities.Hoadon;
import w_az.entities.Hoadonchitiet;
import w_az.entities.Product;
import w_az.entities.User;


@WebServlet({
	"/home/trangChuNoLogin",
	"/home/trangChu",
	"/home/danhMuc",
	"/home/findByKeyWord",
	"/home/thanhToan",
	"/home/createProduct",
	"/home/createHDCT",
	"/muaNgay",
	"/createMuaNgay",
	"/home/deleteProductByUser",
	"/home/gioHangInUser",
	})

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO ProductDAO;
	private CategoryDAO CategoryDAO;
	private UserDAO UserDAO;
	private hoaDonChiTietDAO hoaDonChiTietDAO;
	private hoaDonDAO hoaDonDAO;
	
    public HomeServlet() {
        super();
        this.ProductDAO = new ProductDAO();
        this.CategoryDAO = new CategoryDAO();
        this.UserDAO = new UserDAO();
        this.hoaDonChiTietDAO = new hoaDonChiTietDAO();
        this.hoaDonDAO = new hoaDonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("trangChuNoLogin")) {
			this.trangChuNoLogin(request, response);
		}else if (uri.contains("danhMuc")) {
			this.danhMuc(request, response);
		}else if (uri.contains("createMuaNgay")) {
			this.createMuaNgay(request, response);
		}else if (uri.contains("findByKeyWord")) {
			this.findByKeyWord(request, response);
		}else if (uri.contains("createProduct")) {
			this.createProduct(request, response);
		}else if (uri.contains("deleteProductByUser")) {
			this.deleteProductByUser(request, response);
		}else if (uri.contains("trangChu")) {
			this.trangChu(request, response);
		}else if (uri.contains("gioHangInUser")) {
			this.gioHangInUser(request, response);
		}else{
			// 404
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("createHDCT")) {
			this.createHDCT(request, response);
		}else if (uri.contains("muaNgay")) {
			this.muaNgay(request, response);
		}else if (uri.contains("thanhToan")) {
			this.thanhToan(request, response);
		}
	}
	
	private void gioHangInUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Date now = new Date();
		User user = (User) session.getAttribute("user");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Hoadonchitiet> hdctLstInUser = this.hoaDonChiTietDAO.selectHoaDonChiTietInUserId(id);
			request.setAttribute("hdctLstInUser", hdctLstInUser);
			request.setAttribute("user", user);
			request.setAttribute("now", now);
			request.setAttribute("view", "/views/admins/user/gioHang.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	private void createMuaNgay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Date now = new Date();
		User user = (User) session.getAttribute("user");
		try {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Product product = this.ProductDAO.findById(id);	
			request.setAttribute("user", user);
			request.setAttribute("product", product);
			request.setAttribute("now", now);
			request.setAttribute("view", "/views/admins/user/thanhToanNgay.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void muaNgay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Date now = new Date();
		User user = (User) session.getAttribute("user");
		try {
			Hoadonchitiet hdct = new Hoadonchitiet();
			Hoadon hd = new Hoadon();
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = this.ProductDAO.findById(id);
			BeanUtils.populate(hdct, request.getParameterMap());
			hdct.setUser(user);
			hd.setUser(user);
			hd.setStatus(0);
			hdct.setProduct(product);
			hdct.setStatus(1);
			int thanhTien = product.getDonGia() * hdct.getSoLuong();
			hdct.setThanhTien(thanhTien);
			hdct.setHoadon(hd);
			hd.setTongTien(hdct.getThanhTien());
			this.hoaDonDAO.create(hd);
			this.hoaDonChiTietDAO.create(hdct);
			request.setAttribute("hdctUserSession", hdct);
			session.setAttribute("message", "mua thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "mua thất bại");
		}
		response.sendRedirect("/w_az/home/trangChu");
		
	}

	private void danhMuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			List<Category> Category = this.CategoryDAO.selectAllTen();
			List<Product> dsP = this.ProductDAO.selectAllProductByCategoryId(id);
			request.setAttribute("user", user);
			request.setAttribute("categoryName", Category);
			request.setAttribute("dsP", dsP);
			request.setAttribute("view",
					"/views/admins/user/danhMuc.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void trangChuNoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			Date now = new Date();					
			List<Product> dsProduct = this.ProductDAO.all();
			List<Category> dsCategory = this.CategoryDAO.all();
			List<Category> dsCategoryName = this.CategoryDAO.selectAllTen();
			request.setAttribute("categoryName", dsCategoryName);
			request.setAttribute("dsCategory", dsCategory);
			request.setAttribute("dsProduct", dsProduct);
			request.setAttribute("now", now);		
			request.setAttribute("view", "/views/admins/user/homeNoLogin.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void trangChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			Date now = new Date();					
			List<Product> dsProduct = this.ProductDAO.all();
			List<Category> dsCategory = this.CategoryDAO.all();
			List<Category> dsCategoryName = this.CategoryDAO.selectAllTen();
						
			request.setAttribute("user", user);
			request.setAttribute("categoryName", dsCategoryName);
			request.setAttribute("dsCategory", dsCategory);
			request.setAttribute("dsProduct", dsProduct);
			request.setAttribute("now", now);		
			request.setAttribute("view", "/views/admins/user/home.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void findByKeyWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Date now = new Date();
		try {			
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Product product = this.ProductDAO.findById(id);				
			request.setAttribute("product", product);
			request.setAttribute("user", user);
			request.setAttribute("now", now);
			request.setAttribute("view",
				"/views/admins/user/datHang.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createHDCT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Date now = new Date();
		try {			
			int id = Integer.parseInt(request.getParameter("id"));
			Hoadonchitiet hdct = new Hoadonchitiet();
			Product product = this.ProductDAO.findById(id);
			BeanUtils.populate(hdct, request.getParameterMap());
			hdct.setProduct(product);
			hdct.setUser(user);
			hdct.setStatus(1);
			int thanhTien = product.getDonGia() * hdct.getSoLuong();
			hdct.setThanhTien(thanhTien);			
			this.hoaDonChiTietDAO.create(hdct);
			request.setAttribute("user", user);
			request.setAttribute("hdctUser", hdct);
			request.setAttribute("hdctUsserSession", hdct);
			session.setAttribute("message", "Đặt hàng thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Đặt hàng thất bại");
		}
		response.sendRedirect("/w_az/home/trangChu");
	}
	
	private void thanhToan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try{
			Hoadon hd = new Hoadon();
			hd.setUser(user);
			hd.setTongTien(0);
			hd.setStatus(0);
			this.hoaDonDAO.create(hd);
			String[] valueIdHdctStr = request.getParameterValues("cboThanhToan");	
			int tongTien = 0;
			for(String x: valueIdHdctStr) {
				Hoadonchitiet hdct = this.hoaDonChiTietDAO.findById(Integer.parseInt(x));
				hdct.setHoadon(hd);
				hdct.setStatus(1);
				tongTien += hdct.getThanhTien();
				hd.setTongTien(tongTien);
				this.hoaDonChiTietDAO.update(hdct);	
			}
			this.hoaDonDAO.update(hd);
			session.setAttribute("message", "Đặt hàng thành công bạn hãy chuẩn bị số tiền là: " +hd.getTongTien());
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Đặt hàng thất bại");
		}	
		response.sendRedirect("/w_az/home/trangChu");
	}
	
	private void deleteProductByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Hoadonchitiet hdct = this.hoaDonChiTietDAO.findById(id);
			hdct.setStatus(0);
			this.hoaDonChiTietDAO.update(hdct);
			session.setAttribute("message", "bỏ sản phẩm thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "bỏ sản phẩm thất bại");
		}
		response.sendRedirect("/w_az/home/trangChu");
	}
}
