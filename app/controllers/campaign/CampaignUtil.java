package controllers.campaign;

import models.User;
import models.campaign.Campaign;
import models.campaign.Player;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;

public class CampaignUtil {
	/**
	 * Found the player of the campaign
	 * 
	 * @param cpmg
	 * @param user
	 * @return
	 */
	public static Player getCampaignPlayer(Campaign cpmg, User user) {
		Expression FIND_PLAYER = Expr.and(
				Expr.eq("campaign_campaign_id", cpmg.getId()),
				Expr.eq("real_user_id", user.getId()));

		return Ebean.find(Player.class).where().add(FIND_PLAYER).findUnique();
	}
}
