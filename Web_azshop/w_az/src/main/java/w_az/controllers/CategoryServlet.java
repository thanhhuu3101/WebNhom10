package w_az.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.commons.beanutils.BeanUtils;

import w_az.dao.CategoryDAO;
import w_az.dao.ProductDAO;
import w_az.dao.UserDAO;
import w_az.entities.Category;
import w_az.entities.Product;
import w_az.entities.User;

@WebServlet({
	"/category/categories",
	"/category/create",
	"/category/store",
	"/category/editCategories",
	"/category/update",
	"/category/delete",
	})
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;  
	private CategoryDAO cateDAO;
	private ProductDAO productDAO;
    
    public CategoryServlet() {
        super();
        this.userDAO = new UserDAO();
        this.cateDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("categories")) {
			this.categories(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("editCategories")) {
			this.editCategories(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else {
			// 404
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			// 404
		}		
		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			int id = Integer.parseInt(idStr);
			
			Category newValue = new Category();
			
			String ten = request.getParameter("ten");
			int idUser = Integer.parseInt(request.getParameter("user_id"));
			User user = this.userDAO.findById(idUser);
			newValue.setTen(ten);
			newValue.setUser(user);
			
			BeanUtils.populate(newValue,
				request.getParameterMap());
			
			this.cateDAO.update(newValue);
			session.setAttribute("message", "cập nhật thành công");
			response.sendRedirect("/w_az/category/categories");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "cập nhật thất bại");
			response.sendRedirect("/w_az/category/editCategories?id=" + idStr);
		}
		
	}

	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Category cate = new Category();
		String ten = request.getParameter("ten");
		int idUser = Integer.parseInt(request.getParameter("user_id"));
		User user = this.userDAO.findById(idUser);
		cate.setTen(ten);
		cate.setUser(user);
		try {
			this.cateDAO.create(cate);
			session.setAttribute("message", "Thêm mới thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại");
		}		
		response.sendRedirect("/w_az/category/categories");
	}	

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Category entity = this.cateDAO.findById(id);
		int hoi = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa không?", "Hệ thống", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		try {
			if(hoi == JOptionPane.YES_OPTION) {
				this.cateDAO.delete(entity);				
			}
			session.setAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Xóa thất bại");
		}
		response.sendRedirect("/w_az/category/categories");
	}

	private void editCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		
		Category category = this.cateDAO.findById(id);
		List<User> dsUser = this.userDAO.all();
		
		request.setAttribute("category", category);
		request.setAttribute("dsUser", dsUser);
		
		request.setAttribute("view",
			"/views/admins/admin/helper/editCategory.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);		
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("view", "/views/admins/admin/categories.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void categories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Date now = new Date();
		List<User> dsUser = this.userDAO.all();
		List<Category> dsCategory = this.cateDAO.all();
		request.setAttribute("dsUser", dsUser);
		request.setAttribute("dsCategory", dsCategory);
		request.setAttribute("now", now);
		request.setAttribute("view", "/views/admins/admin/categories.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

}
