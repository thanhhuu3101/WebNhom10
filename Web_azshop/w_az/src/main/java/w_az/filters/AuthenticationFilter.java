package w_az.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import w_az.entities.User;

@WebFilter(
	urlPatterns={
			"/user/*",
			"/product/*",
			"/category/*",
	})
public class AuthenticationFilter extends HttpFilter implements Filter {

    public AuthenticationFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain
			) throws IOException, ServletException {
		//kiểm tra trước khi đăng nhập
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse)  response;
		HttpSession session = httpReq.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null) {
			httpRes.sendRedirect("/w_az/login");
			session.setAttribute("error", "vui lòng đăng nhập tài khoản");
			return;
		}
		if(user.getRole() == 0) {
			httpRes.sendRedirect("/w_az/home/trangChu");
			session.setAttribute("error", "vui lòng đăng nhập tài khoản");
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
