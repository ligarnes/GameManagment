import java.util.Date;

import models.User;
import models.campaign.Campaign;
import models.campaign.Player;
import models.campaign.conversation.Conversation;
import models.campaign.conversation.Message;
import play.Application;
import play.GlobalSettings;
import tool.encrypt.BCrypt;

import com.avaje.ebean.Ebean;

import controllers.Util;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public void onStart(Application app) {
			InitialData.insert(app);
		}

		private static User user1;
		private static User user2;
		private static User user3;

		private static void generateUsers() {
			user1 = new User();
			user1.setUsername("Cody");
			user1.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
			user1.setEmail("cody.stoutenburg@gmail.com");
			user1.setPhoneCell("418 906 0449");

			user2 = new User();
			user2.setUsername("Hugo");
			user2.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
			user2.setEmail("hugo.terrisse@gmail.com");
			user2.setPhoneCell("04 75 87 24 79");

			user3 = new User();
			user3.setUsername("Robin");
			user3.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
			user3.setEmail("robin.terrisse@gmail.com");
			user3.setPhoneCell("04 75 68 69 00");
		}

		public static void insert(Application app) {
			if (Ebean.find(User.class).findRowCount() < 2
					|| Ebean.find(Message.class).findRowCount() < 3
					|| Ebean.find(Conversation.class).findRowCount() < 1) {

				generateUsers();

				Player p1 = createPlayer(user1, "#9ed5ff");
				Player p2 = createPlayer(user2, "#d9ffa8");

				Message msg1 = createMessage(
						p1,
						"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec aliquam iaculis lectus sed pretium. Praesent in libero placerat, scelerisque ligula a, hendrerit justo. Curabitur ultrices enim eu venenatis viverra. Nulla tincidunt odio eu enim imperdiet suscipit. Duis dignissim faucibus risus, et fermentum metus laoreet non. Integer sit amet risus at metus ornare condimentum. Nam tempor diam ac magna mollis, non dapibus elit mattis. Aenean volutpat lorem nec egestas pulvinar.");

				Message msg2 = createMessage(p2, "Réponse a Lorem ipsum");

				Message msg3 = createMessage(p1,
						"Finalement Lorem est meilleur");

				Conversation conversation = createConversation(
						"Longue conversation à propos de Lorem", msg1, msg2,
						msg3);

				Campaign cpmg = createCampaign(conversation, p1, p2);

				// save users
				Ebean.save(user1);
				Ebean.save(user2);
				Ebean.save(user3);

				// save campaigns
				Ebean.save(cpmg);

				// save players
				Ebean.save(p1);
				Ebean.save(p2);

				// save conversations
				Ebean.save(conversation);

				// save messages
				Ebean.save(msg1);
				Ebean.save(msg2);
				Ebean.save(msg3);

			}
		}
	}

	public static Player createPlayer(User user, String color) {
		Player player = new Player();
		player.setUser(user);
		player.setColor(color);

		return player;
	}

	public static Campaign createCampaign(Conversation conversation,
			Player... players) {

		Campaign cpmg = new Campaign();
		cpmg.addConversation(conversation);

		for (Player player : players) {
			cpmg.addPlayer(player);
		}
		return cpmg;
	}

	public static Conversation createConversation(String title,
			Message... messages) {

		Conversation conversation = new Conversation();
		conversation.setTitle("Longue conversation à propos de Lorem");

		for (Message msg : messages) {
			conversation.addMessage(msg);
		}

		return conversation;
	}

	public static Message createMessage(Player author, String msg) {
		return createMessage(author, msg, Util.dateToString(new Date()));
	}

	public static Message createMessage(Player author, String msg, String date) {
		Message msg1 = new Message();

		msg1.setAuthor(author);
		msg1.setMessage(msg);
		msg1.setPublishDate(date);

		return msg1;
	}

}
