package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;

public class ListUserController implements Controller {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            resp.sendRedirect("/users/loginForm");
            return;
        }

        req.setAttribute("users", DataBase.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
