package models.campaign;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.campaign.conversation.Conversation;

@Entity
@Table(name = "campaign")
public class Campaign {

	@Id
	@Column(name = "campaign_id")
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Player> players;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Conversation> conversations;

	@OneToMany(cascade = CascadeType.ALL)
	private final List<GameSession> gameSessions;

	public Campaign() {
		players = new ArrayList<>();
		conversations = new ArrayList<>();
		gameSessions = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public void removePlayer(Player p) {
		this.players.remove(p);
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public void addConversation(Conversation conv) {
		this.conversations.add(conv);
	}

	public void removeConversation(Conversation conv) {
		this.conversations.remove(conv);
	}

	public List<GameSession> getGameSessions() {
		return gameSessions;
	}

	public void addGameSession(GameSession gameSession) {
		this.gameSessions.add(gameSession);
	}

}
