package controllers;

import java.util.Date;

import models.User;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;
import play.mvc.Security;
import tool.encrypt.BCrypt;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class Secured extends Security.Authenticator {
	public static final String SESSION_USER_ID = "user_id";

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get(SESSION_USER_ID);
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		// Add message -> page not allowed
		return redirect(routes.LoginController.login());
	}

	public static User getLoggedUser(Session session) {
		String userId = session.get(Secured.SESSION_USER_ID);

		return Ebean.find(User.class, userId);
	}

	public static boolean isMailValid(String mail) {
		String sql = "select count(*) as count from user where email='" + mail
				+ "'";
		SqlRow row = Ebean.createSqlQuery(sql).findUnique();
		Integer i = row.getInteger("count");

		return i == 0;
	}

	public static boolean isUsernameValid(String username) {
		String sql = "select count(*) as count from user where username='"
				+ username + "'";
		SqlRow row = Ebean.createSqlQuery(sql).findUnique();
		Integer i = row.getInteger("count");

		return i == 0;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return null if no username or if the password is wrong
	 */
	public static User connect(String username, String password) {
		User found = Ebean.find(User.class).where("username=:username")
				.setParameter("username", username).findUnique();

		if (found != null) {
			if (!BCrypt.checkpw(password, found.getPassword())) {
				found = null;
			} else {
				found.setLastConnection(Util.dateToString(new Date()));
				Ebean.save(found);
			}
		}

		return found;
	}

}
