package models.campaign.conversation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "campaign_conversation")
public class Conversation {
	@Id
	@Column(name = "conversation_id")
	private Integer id;

	private String title;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Message> messages;

	public Conversation() {
		this.messages = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message msg) {
		this.messages.add(msg);
	}

	public void removeMessage(Message msg) {
		this.messages.remove(msg);
	}
}
