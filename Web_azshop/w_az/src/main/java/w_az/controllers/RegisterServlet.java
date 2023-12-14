package w_az.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import w_az.dao.UserDAO;
import w_az.entities.User;
import w_az.utils.EncryptUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO UserDAO;
	
    public RegisterServlet() {
        super();
        this.UserDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/views/admins/user/register.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			User entity = new User();
			BeanUtils.populate(entity, request.getParameterMap());
			entity.setRole(0);
			String encryted = EncryptUtil.encrypt(request.getParameter("password"));
			entity.setPassword(encryted);
			this.UserDAO.create(entity);
			session.setAttribute("message", "Đăng ký thành công");
			response.sendRedirect("/w_az/login");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Đăng ký thất bại");
		}
	}

}
