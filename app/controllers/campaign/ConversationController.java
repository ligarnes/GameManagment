package controllers.campaign;

import models.User;
import models.campaign.Campaign;
import models.campaign.conversation.Conversation;
import models.campaign.conversation.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import com.avaje.ebean.Ebean;

import controllers.Secured;
import controllers.Util;

@Security.Authenticated(Secured.class)
public class ConversationController extends Controller {
	public static Result viewConversationsList(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		return Results.ok(views.html.conversations.ConversationList
				.render(cpmg));
	}

	public static Result viewConversation(int campaignId, int conversationId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);
		Conversation conversation = Ebean.find(Conversation.class,
				conversationId);

		return Results.ok(views.html.conversations.Conversation.render(cpmg,
				conversation));
	}

	public static class NewMessage {
		public String message;

	}

	public static Result addMessage(int campaignId, int conversationId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);
		Conversation conversation = Ebean.find(Conversation.class,
				conversationId);

		Form<NewMessage> loginForm = new Form<>(NewMessage.class)
				.bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.conversations.Conversation.render(
					cpmg, conversation));
		} else {
			User user = Secured.getLoggedUser(session());

			Message msg = new Message();
			msg.setAuthor(CampaignUtil.getCampaignPlayer(cpmg, user));
			msg.setMessage(loginForm.get().message);
			msg.setPublishDate(Util.now());

			conversation.addMessage(msg);

			Ebean.save(conversation);

			return redirect(controllers.campaign.routes.ConversationController
					.viewConversation(campaignId, conversationId));
		}
	}

	public static class NewConversation {
		public String title;
		public String message;
	}

	public static Result addConversationView(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		return Results.ok(views.html.conversations.ConversationListAdd
				.render(cpmg));
	}

	public static Result addConversation(int campaignId) {
		Campaign cpmg = Ebean.find(Campaign.class, campaignId);

		Form<NewConversation> addConversationForm = new Form<>(
				NewConversation.class).bindFromRequest();
		if (addConversationForm.hasErrors()) {

			return badRequest(views.html.conversations.ConversationList
					.render(cpmg));

		} else {
			User user = Secured.getLoggedUser(session());

			Message msg = new Message();
			msg.setAuthor(CampaignUtil.getCampaignPlayer(cpmg, user));
			msg.setMessage(addConversationForm.get().message);
			msg.setPublishDate(Util.now());

			Conversation conversation = new Conversation();
			conversation.setTitle(addConversationForm.get().title);
			conversation.addMessage(msg);

			cpmg.addConversation(conversation);

			Ebean.save(cpmg);
			Ebean.save(conversation);

			return redirect(controllers.campaign.routes.ConversationController
					.viewConversation(campaignId, conversation.getId()));
		}
	}
}
