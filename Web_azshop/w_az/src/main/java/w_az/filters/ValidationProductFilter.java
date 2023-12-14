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

import org.apache.commons.beanutils.BeanUtils;

@WebFilter(filterName = "ValidationProductFilter",
	urlPatterns={
		"/product/store",
		"/product/update",
		})
public class ValidationProductFilter extends HttpFilter implements Filter {
	
    public ValidationProductFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse)  response;
		HttpSession session = httpReq.getSession();
		String ten = request.getParameter("ten"),
				tenDanhMuc = request.getParameter("category_id"),
					mauSac = request.getParameter("mauSac"),
							kichThuoc = request.getParameter("kichThuoc"),
									donGia = request.getParameter("donGia"),
											soLuong = request.getParameter("soLuong");
												
		if(ten.isEmpty()) {
			session.setAttribute("error", "ô nhập liệu ten không được bỏ trống");
			httpRes.sendRedirect("/w_az/product/products");
			return;
		}
		if( mauSac.isEmpty() ) {
			session.setAttribute("error", "ô nhập liệu mauSac không được bỏ trống");
			httpRes.sendRedirect("/w_az/product/products");
			return;
		}
		if( kichThuoc.isEmpty()) {
			session.setAttribute("error", "ô nhập liệu kichThuoc không được bỏ trống");
			httpRes.sendRedirect("/w_az/product/products");
			return;
		}
		if( donGia.isEmpty()) {
			session.setAttribute("error", "ô nhập liệu donGia không được bỏ trống");
			httpRes.sendRedirect("/w_az/product/products");
			return;
		}
		if( soLuong.isEmpty() ) {
			session.setAttribute("error", "ô nhập liệu soLuong không được bỏ trống");
			httpRes.sendRedirect("/w_az/product/products");
			return;
		}
		
		
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
