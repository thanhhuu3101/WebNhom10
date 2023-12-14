package w_az.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import w_az.dao.CategoryDAO;
import w_az.dao.ProductDAO;
import w_az.dao.UserDAO;
import w_az.dao.hoaDonChiTietDAO;
import w_az.dao.hoaDonDAO;
import w_az.entities.Hoadon;
import w_az.entities.Hoadonchitiet;

@WebServlet({
	"/hoaDon/hoaDonKhachHang",
	"/hoaDon/DongYDonHang",
	"/hoaDon/HuyBoDonHang",
	"/hoaDon/showHoaDonChiTiet",
	"/hoaDon/lichSuDatHang",
})
public class hoaDonKhachHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO ProductDAO;
	private CategoryDAO CategoryDAO;
	private UserDAO UserDAO;
	private hoaDonChiTietDAO hoaDonChiTietDAO;
	private hoaDonDAO hoaDonDAO;
	
    public hoaDonKhachHangServlet() {
        super();
        this.ProductDAO = new ProductDAO();
        this.CategoryDAO = new CategoryDAO();
        this.UserDAO = new UserDAO();
        this.hoaDonChiTietDAO = new hoaDonChiTietDAO();
        this.hoaDonDAO = new hoaDonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("hoaDonKhachHang")) {
			this.hoaDonKhachHang(request, response);
		}if (uri.contains("HuyBoDonHang")) {
			this.HuyBoDonHang(request, response);
		}if(uri.contains("showHoaDonChiTiet")) {
			this.showHoaDonChiTiet(request, response);
		}if(uri.contains("lichSuDatHang")) {
			this.lichSuDatHang(request, response);
		}else {
			//404
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("DongYDonHang")) {
			this.DongYDonHang(request, response);
		}
	}
	
	private void lichSuDatHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Date now = new Date();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Hoadon> hd = this.hoaDonDAO.selectLichSuDatHangByUserId(id);
			request.setAttribute("hd", hd);
			request.setAttribute("now",now);
			request.setAttribute("view", "/views/admins/user/lichSuDatHang.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showHoaDonChiTiet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Hoadonchitiet> hdct = this.hoaDonChiTietDAO.showHoaDonChiTietInHoaDon(id);
			request.setAttribute("hdct", hdct);
			request.setAttribute("view", "/views/admins/user/showHDCT.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void DongYDonHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try{
			String[] valueId = request.getParameterValues("cboDongy");
			for(String x: valueId) {
				Hoadon hd = this.hoaDonDAO.findById(Integer.parseInt(x));
				hd.setStatus(1);
				this.hoaDonDAO.update(hd);
			}			
			session.setAttribute("message", "Đồng ý đơn hàng thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Đồng ý đơn hàng thất bại");
		}
		response.sendRedirect("/w_az/hoaDon/hoaDonKhachHang");
		
	}

	private void hoaDonKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Date now = new Date();
		try {
			List<Hoadon> hd = this.hoaDonDAO.selectHoaDonChuaThanhToan();
			request.setAttribute("hd", hd);
			request.setAttribute("now",now);
			request.setAttribute("view", "/views/admins/user/hoaDonKhachHang.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void HuyBoDonHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Hoadon hd = this.hoaDonDAO.findById(id);
		int hoi = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa không?", "Hệ thống", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
		try {
			if(hoi == JOptionPane.YES_OPTION) {
				this.hoaDonDAO.delete(hd);
				session.setAttribute("message", "hủy đơn thành công");
			}		
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "hủy đơn thất bại");
		}
		response.sendRedirect("/w_az/hoaDon/hoaDonKhachHang");
	}
}

