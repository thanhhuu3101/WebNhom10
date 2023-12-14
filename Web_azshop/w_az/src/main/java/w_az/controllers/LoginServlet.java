package w_az.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import w_az.dao.UserDAO;
import w_az.entities.User;
import w_az.utils.EncryptUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public LoginServlet() {
        super();
        this.userDAO = new UserDAO();
    }

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setAttribute("view", "/views/admins/user/login.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email"),
			pwd = request.getParameter("password");
		
		User user = this.userDAO.findByEmail(email);
		
		boolean check = EncryptUtil.check(pwd, user.getPassword());
		
		if (check == true) {
			session.setAttribute("message", "Đăng nhập thành công");
			session.setAttribute("user", user);
			response.sendRedirect(
					"/w_az/home/trangChu"
					);
		} else{
			session.setAttribute("error", "Đăng nhập thất bại");
			response.sendRedirect(
					"/w_az/login"
				);
		}
	}
}
