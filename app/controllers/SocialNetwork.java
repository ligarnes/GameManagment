package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.avaje.ebean.Ebean;

@Security.Authenticated(Secured.class)
public class SocialNetwork extends Controller {

	public static Result index() {
		return redirect(routes.LoginController.login());
	}

	public static Result wall() {
		String userId = session().get(Secured.SESSION_USER_ID);

		User current = Ebean.find(User.class, userId);

		return ok(views.html.main.render("title", current,
				views.html.wall.render(current)));
	}
}
