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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "ValidationCategoryFilter",
	urlPatterns={
		"/category/store",
		"/category/update",
		})
public class ValidationCategoryFilter extends HttpFilter implements Filter {

    public ValidationCategoryFilter() {
       
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse)  response;
		HttpSession session = httpReq.getSession();
		String ten = request.getParameter("ten");
		
		if(ten.isEmpty()) {
		session.setAttribute("error", "ô nhập liệu ten không được bỏ trống");
		httpRes.sendRedirect("/w_az/category/categories");
		return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
