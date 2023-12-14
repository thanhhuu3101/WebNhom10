package w_az.filters;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import w_az.dao.UserDAO;
import w_az.entities.User;

@WebFilter(filterName = "ValidationUserFilter",
	urlPatterns={
		"/user/store",
		"/user/update",
		
})
public class ValidationUserFilter extends HttpFilter implements Filter {
	private UserDAO UserDAO;
	
	public ValidationUserFilter() {
    	this.UserDAO = new UserDAO();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse)  response;
		HttpSession session = httpReq.getSession();
		String hoTen = request.getParameter("hoTen"),
				diaChi = request.getParameter("diaChi"),
						sdt = request.getParameter("sdt"),
								email = request.getParameter("email"),
										gioiTinh = request.getParameter("gioiTinh"),
												role = request.getParameter("role");
		
		if(hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || email.isEmpty()
				|| gioiTinh.isEmpty() || role.isEmpty()) {
			session.setAttribute("error", "ô nhập liệu không được bỏ trống");
			httpRes.sendRedirect("/w_az/user/users");
			return ;
		}
		for(int i=0;i< hoTen.length();i++){
			if(!Character.isLetter(hoTen.charAt(i))) {
				session.setAttribute("error", "ô nhập liệu họ tên không được thêm số");
				httpRes.sendRedirect("/w_az/user/users");
				return;
			} 
		}
		String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
		boolean kt = sdt.matches(reg);
		if(kt==false) {
			session.setAttribute("error", "ô nhập liệu số điện thoại sai định dạng");
			httpRes.sendRedirect("/w_az/user/users");
			return;
		}
		while(sdt.length() != 10) {
			session.setAttribute("error", "ô nhập liệu số điện thoại phải là 10 số");
			httpRes.sendRedirect("/w_az/user/users");
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
