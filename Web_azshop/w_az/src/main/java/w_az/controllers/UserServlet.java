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

import w_az.dao.UserDAO;
import w_az.entities.User;
import w_az.utils.EncryptUtil;

@WebServlet({
	"/user/users",
	"/user/create",
	"/user/store",
	"/user/editUser",
	"/user/update",
	"/user/delete",
})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO UserDAO;
    
    public UserServlet() {
        super();
        this.UserDAO = new UserDAO();
    }

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("users")) {
			this.users(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("editUser")) {
			this.editUser(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else {
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
	
	private void users(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {		
		Date now = new Date();
		List<User> ds = this.UserDAO.all();
		request.setAttribute("ds", ds);
		request.setAttribute("now", now);
		request.setAttribute("view",
			"/views/admins/admin/users.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
		.forward(request, response);
	}
		
	private void create(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setAttribute("view", "/views/admins/admin/users.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void editUser(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.UserDAO.findById(id);
		request.setAttribute("user", entity);
		request.setAttribute("view",
			"/views/admins/admin/helper/editUser.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}
	
	private void delete(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.UserDAO.findById(id);
		int hoi = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa không?", "Hệ thống", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		try {
			if(hoi == JOptionPane.YES_OPTION) {
				this.UserDAO.delete(entity);
			}
			session.setAttribute("message", "xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "xóa thất bại");
		}
		response.sendRedirect("/w_az/user/users");
	}
	
	private void update(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(idStr);
			User oldValue = this.UserDAO.findById(id);
			User newValue = new User();
			BeanUtils.populate(newValue,
				request.getParameterMap());
			
			newValue.setPassword( oldValue.getPassword() );
			 
			this.UserDAO.update(newValue);
			session.setAttribute("message", "cập nhật thành công");
			response.sendRedirect("/w_az/user/users");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "cập nhật thất bại");
			response.sendRedirect("/w_az/user/editUser?id=" + idStr);
		}

	}
	
	private void store(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			User entity = new User();
			BeanUtils.populate(entity, request.getParameterMap());
			String encryted = EncryptUtil.encrypt("1");
			entity.setPassword(encryted);
			this.UserDAO.create(entity);
			session.setAttribute("message", "Thêm mới thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại");
		}
		response.sendRedirect("/w_az/user/users");
	}

}
