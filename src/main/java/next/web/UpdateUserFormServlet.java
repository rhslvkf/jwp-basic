package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getParameter("userId");
		log.debug("userId : {}", userId);
		User user = DataBase.findUserById(userId);
		log.debug("user : {}", user);
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
				req.getParameter("email"));
		log.debug("user : {}", user);
		log.debug("originUserId : {}", req.getParameter("originUserId"));
		DataBase.updateUser(req.getParameter("originUserId"), user);
		resp.sendRedirect("/user/list");
	}

}
