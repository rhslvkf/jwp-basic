package next.requestMapping;

import java.util.HashMap;
import java.util.Map;

import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginController;
import next.controller.LogoutController;
import next.controller.ProfileController;
import next.controller.UpdateUserController;

public class RequestMapping {

	private static Map<String, Controller> controllers = new HashMap<String, Controller>();
	
	static {
		controllers.put("/", new HomeController());
		controllers.put("/users/create", new CreateUserController());
		controllers.put("/users/form", new CreateUserController());
		controllers.put("/users/login", new LoginController());
		controllers.put("/users/loginForm", new LoginController());
		controllers.put("/users/logout", new LogoutController());
		controllers.put("/users", new ListUserController());
		controllers.put("/users/profile", new ProfileController());
		controllers.put("/users/update", new UpdateUserController());
		controllers.put("/users/updateForm", new UpdateUserController());
	}
	
	public static Controller getController(String requestUrl) {
		return controllers.get(requestUrl);
	}
	
}
