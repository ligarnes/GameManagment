package models.campaign;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "campaign_game_session_player_state")
public class GameSessionPlayerState {

	public enum GameSessionUserStatus {
		PRESENT, MISSING, NO_ANSWER
	};

	@Id
	@Column(name = "game_session_player_state_id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player_id", referencedColumnName = "player_id")
	private Player player;

	private GameSessionUserStatus status;

	@ManyToOne
	private GameSession gameSession;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameSessionUserStatus getStatus() {
		return status;
	}

	public void setStatus(GameSessionUserStatus status) {
		this.status = status;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

}
