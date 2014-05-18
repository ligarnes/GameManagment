package models.campaign;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.campaign.GameSessionPlayerState.GameSessionUserStatus;

@Entity
@Table(name = "campaign_game_session")
public class GameSession {
	@Id
	@Column(name = "game_session_id")
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<GameSessionPlayerState> players;

	@Column(name = "is_validate")
	private boolean isValidate;

	@Column(name = "date")
	private String date;

	@Column(name = "duration")
	private String duration;

	public GameSession() {
		players = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<GameSessionPlayerState> getPlayers() {
		return players;
	}

	private ArrayList<Player> getSessionPlayer(GameSessionUserStatus status) {
		// play.Logger.debug(" ====================");
		// play.Logger.debug("Get players: " + players);
		// play.Logger.debug(" ====================");

		ArrayList<Player> presents = new ArrayList<>();

		for (GameSessionPlayerState user : getPlayers()) {
			if (status.equals(user.getStatus())) {
				presents.add(user.getPlayer());
			}
		}

		return presents;
	}

	public ArrayList<Player> getPresent() {
		return getSessionPlayer(GameSessionUserStatus.PRESENT);
	}

	public ArrayList<Player> getMissing() {
		return getSessionPlayer(GameSessionUserStatus.MISSING);
	}

	public ArrayList<Player> getNotAnswer() {
		return getSessionPlayer(GameSessionUserStatus.NO_ANSWER);
	}

	public void setPlayers(List<GameSessionPlayerState> players) {
		this.players = players;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
