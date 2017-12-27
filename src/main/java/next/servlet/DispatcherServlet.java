package next.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.Controller;
import next.requestMapping.RequestMapping;

@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("DispatcherServlet doPost");
		log.debug("url : {}", req.getRequestURI());
		Controller controller = RequestMapping.getController(req.getRequestURI());
		controller.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("DispatcherServlet doGet");
		log.debug("url : {}", req.getRequestURI());
		Controller controller = RequestMapping.getController(req.getRequestURI());
		controller.doGet(req, resp);
	}
	
}
