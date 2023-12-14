package w_az.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import w_az.dao.CategoryDAO;
import w_az.dao.ProductDAO;
import w_az.entities.Category;
import w_az.entities.Product;
import w_az.entities.User;

@WebServlet({
		"/product/products",
		"/product/create",
		"/product/store",
		"/product/editProduct",
		"/product/update",
		"/product/delete",
})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO ProductDAO;
	private CategoryDAO CategoryDAO;

    public ProductServlet() {
        super();
        this.ProductDAO = new ProductDAO();
        this.CategoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("products")) {
			this.products(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("editProduct")) {
			this.editProduct(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		}else {
			// 404
		}
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			// 404
		}
	}
	


	private void products(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Date now = new Date();
		List<Product> dsP = this.ProductDAO.all();
		List<Category> dsC = this.CategoryDAO.all();
		request.setAttribute("dsP", dsP);
		request.setAttribute("dsC", dsC);
		request.setAttribute("now", now);
		request.setAttribute("view",
			"/views/admins/admin/products.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
		.forward(request, response);
	}
		
	private void create(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Category> dsC = this.CategoryDAO.all();
		request.setAttribute("dsC", dsC);
		request.setAttribute("view", "/views/admins/admin/products.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void editProduct(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Product entity = this.ProductDAO.findById(id);
		List<Category> dsC = this.CategoryDAO.all();
		request.setAttribute("product", entity);
		request.setAttribute("dsC", dsC);
		request.setAttribute("view",
			"/views/admins/admin/helper/editProduct.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}
	
	private void delete(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Product entity = this.ProductDAO.findById(id);
		int hoi = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa không?", "Hệ thống", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		try {
			if(hoi == JOptionPane.YES_OPTION) {
				this.ProductDAO.delete(entity);
			}
			session.setAttribute("message", "xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "xóa thất bại");
		}
		response.sendRedirect("/w_az/product/products");
	}
	
	private void update(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			int id = Integer.parseInt(idStr);
			int idCategory = Integer.parseInt(request.getParameter("category_id"));
			Product oldValue = this.ProductDAO.findById(id);
			Category category = this.CategoryDAO.findById(idCategory);
			Product newValue = new Product();
			BeanUtils.populate(newValue,
				request.getParameterMap());
			newValue.setCategory(category);
			this.ProductDAO.update(newValue);
			session.setAttribute("message", "cập nhật thành công");
			response.sendRedirect("/w_az/product/products");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "cập nhật thất bại");
			response.sendRedirect("/w_az/product/editProduct?id=" + idStr);
		}

	}
	
	private void store(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			Product entity = new Product();
			
			int idCategory = Integer.parseInt(request.getParameter("category_id"));
			Category category = this.CategoryDAO.findById(idCategory);
			
			BeanUtils.populate(entity, request.getParameterMap());
			entity.setCategory(category);
			this.ProductDAO.create(entity);
			session.setAttribute("message", "thêm mới thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "thêm mới thất bại");
		}
		response.sendRedirect("/w_az/product/products");
	}

	
}
