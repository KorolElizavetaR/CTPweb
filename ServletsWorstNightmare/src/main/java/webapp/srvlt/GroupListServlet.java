package webapp.srvlt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webapp.srvlt.model.Person;
import webapp.srvlt.service.PersonService;

import java.io.IOException;

@WebServlet("/GroupListServlet")
public class GroupListServlet extends HttpServlet {
	
	private PersonService todoService = new PersonService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("group", PersonService.retrieveList());
		request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nname = request.getParameter("nname");
		String nphone = request.getParameter("nphone");
		String nemail = request.getParameter("nemail");
		if (("".equals(nname)) || ("".equals(nphone)) || ("".equals(nemail))) {
			request.setAttribute("errorMessage", "Заполните все поля");
		} else {
			PersonService.addPerson(new Person(nname, nphone, nemail));
		}
		request.setAttribute("group", PersonService.retrieveList());
		request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
	}

}
