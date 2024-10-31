package webapp.srvlt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webapp.srvlt.dao.PersonDAO;
import webapp.srvlt.model.Person;
import webapp.srvlt.service.PersonService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/GroupListServlet")
public class GroupListServlet extends HttpServlet {

	private PersonService todoService = new PersonService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonDAO daoPerson = new PersonDAO();

		try {
			String nname = request.getParameter("nname");
			String nphone = request.getParameter("nphone");
			String nemail = request.getParameter("nemail");
			if (nname.isEmpty() || nphone.isEmpty() || nemail.isEmpty()) {
				request.setAttribute("errorMessage", "Заполните все поля");
			} else {
				daoPerson.insertPerson(new Person(nname, nphone, nemail));
			}
			request.setAttribute("group", daoPerson.getPeople());
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonDAO daoPerson = new PersonDAO();
		try {
			request.setAttribute("group", daoPerson.getPeople());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
	}

}
