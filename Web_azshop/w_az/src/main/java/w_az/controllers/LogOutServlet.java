package w_az.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import w_az.dao.CategoryDAO;
import w_az.dao.ProductDAO;
import w_az.entities.Category;
import w_az.entities.Product;


@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO ProductDAO;
	private CategoryDAO CategoryDAO;
	
    public LogOutServlet() {
        super();
        this.ProductDAO = new ProductDAO();
        this.CategoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	
		List<Product> dsProduct = this.ProductDAO.all();
		request.setAttribute("product", dsProduct);
		List<Category> dsCategory = this.CategoryDAO.all();
		List<Category> dsCategoryName = this.CategoryDAO.selectAllTen();
		request.setAttribute("categoryName", dsCategoryName);
		request.setAttribute("dsCategory", dsCategory);
		request.setAttribute("dsProduct", dsProduct);
		request.setAttribute("view", "/views/admins/user/home.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
