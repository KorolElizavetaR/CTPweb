package webapp.srvlt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webapp.srvlt.coder.HashPassword;
import webapp.srvlt.dao.UserDAO;
import webapp.srvlt.service.PersonService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try {
			UserDAO daoUser = new UserDAO();
			if (daoUser.isValidUser(name, HashPassword.getHash(password))) {

				request.getSession().setAttribute("name", name);

				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie c : cookies) {
						Cookie cookie = c;
						System.out.println(cookie.getName() + cookie.getValue());
						if (name.equals(cookie.getName())) {
							request.getSession().setAttribute("lastdate", cookie.getValue());
						}
					}
				}

				Cookie userCookie = new Cookie(name, LocalDateTime.now().toString());
				userCookie.setMaxAge(60 * 60 * 24 * 365); // хранить куки год
				response.addCookie(userCookie);

				response.sendRedirect(request.getContextPath() + "/GroupListServlet");

			} else {
				request.setAttribute("errorMessage", "Invalid Login and password!!");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("admin") && password.equals("admin");
	}

}
