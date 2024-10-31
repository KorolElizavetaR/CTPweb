package webapp.srvlt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webapp.srvlt.coder.HashPassword;
import webapp.srvlt.dao.UserDAO;
import webapp.srvlt.model.User;

import java.io.IOException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("newLoginName");
		String password = request.getParameter("newPassword");

		User user = new User(name, HashPassword.getHash(password));
		try {
			UserDAO daoUser = new UserDAO();
			if (daoUser.insertUser(user)) {

				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			} else {

				request.setAttribute("errorRegister", "Выберите другое имя, такой пользователь существет");
				request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

	}

}
