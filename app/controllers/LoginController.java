package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {
	// -- Authentication
	public static class Login {

		public String username;
		public String password;

		public String validate() {
			User user = Secured.connect(username, password);
			if (user != null) {
				return null;
			}
			return "Mot de passe ou nom d'utilisateur invalide";
		}
	}

	/**
	 * Login page.
	 */
	public static Result login() {
		return ok(views.html.login.render(new Form<Login>(
				controllers.LoginController.Login.class)));
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {
		Form<Login> loginForm = new Form<>(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm)).as(
					"text/html");
		} else {
			User user = Secured.connect(loginForm.data().get("username"),
					loginForm.data().get("password"));

			session(Secured.SESSION_USER_ID, user.getId().toString());
			return redirect(controllers.campaign.routes.ConversationController
					.viewConversation(1, 1));
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.LoginController.login());
	}
}
