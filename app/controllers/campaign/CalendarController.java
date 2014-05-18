package controllers.campaign;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.campaign.Campaign;
import models.campaign.GameSession;
import models.campaign.GameSessionPlayerState;
import models.campaign.GameSessionPlayerState.GameSessionUserStatus;
import models.campaign.Player;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import com.avaje.ebean.Ebean;

import controllers.Secured;

@Security.Authenticated(Secured.class)
public class CalendarController extends Controller {

	public static Result viewCalendar(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		return Results.ok(views.html.calendar.CalendarView.render(cpmg));
	}

	public static class NewGameSession {
		public String date;
		public String duration;
	}

	public static Result action(int campaignId, int gameSessionId, String action) {
		Result res = null;

		switch (action) {
		case "present":
			res = actionAtDate(campaignId, gameSessionId,
					GameSessionUserStatus.PRESENT);
			break;
		case "missing":
			res = actionAtDate(campaignId, gameSessionId,
					GameSessionUserStatus.MISSING);
			break;
		}

		return res;
	}

	public static Result addDateView(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		return Results.ok(views.html.calendar.CalendarViewAddDate.render(cpmg));
	}

	public static Result addDate(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		Form<NewGameSession> loginForm = new Form<>(NewGameSession.class)
				.bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.calendar.CalendarView.render(cpmg));
		} else {
			GameSession session = new GameSession();
			session.setDate(loginForm.get().date);
			session.setDuration(loginForm.get().duration);
			session.setValidate(false);

			List<Player> players = cpmg.getPlayers();

			ArrayList<GameSessionPlayerState> playersSession = new ArrayList<>();
			for (Player player : players) {
				GameSessionPlayerState userSession = new GameSessionPlayerState();
				userSession.setPlayer(player);
				userSession.setStatus(GameSessionUserStatus.NO_ANSWER);
				playersSession.add(userSession);
			}

			session.setPlayers(playersSession);

			cpmg.addGameSession(session);

			Ebean.save(cpmg);
			Ebean.save(session);
			Ebean.save(playersSession);

			return Results.ok(views.html.calendar.CalendarView.render(cpmg));
		}
	}

	private static Result actionAtDate(int campaignId, int gameSessionId,
			GameSessionUserStatus status) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		GameSession gameSession = Ebean.find(GameSession.class, gameSessionId);

		User user = Secured.getLoggedUser(session());
		GameSessionPlayerState found = null;
		for (GameSessionPlayerState player : gameSession.getPlayers()) {
			if (user.getId().equals(player.getPlayer().getUser().getId())) {
				found = player;
			}
		}

		if (found != null) {
			found.setStatus(status);
			Ebean.save(found);

			return redirect(controllers.campaign.routes.CalendarController
					.viewCalendar(campaignId));
		}

		return badRequest(views.html.calendar.CalendarView.render(cpmg));
	}
}
