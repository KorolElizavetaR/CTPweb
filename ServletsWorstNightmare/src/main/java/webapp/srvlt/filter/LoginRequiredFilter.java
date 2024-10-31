package webapp.srvlt.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class LoginRequiredFilter
 */
@WebFilter(urlPatterns = "/GroupListServlet")
public class LoginRequiredFilter extends HttpFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
		if (session.getAttribute("name") != null) {
			chain.doFilter(request, response);
		} else {

// httpResp.sendRedirect(httpReq.getContextPath() + "/LoginServlet");
			session.invalidate();
			request.getRequestDispatcher("LoginServlet").forward(request, response);
		}

	}

}
