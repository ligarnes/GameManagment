package models.campaign.conversation;

import java.text.ParseException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.campaign.Player;
import controllers.Util;

@Entity
@Table(name = "campaign_conversation_message")
public class Message {
	@Id
	@Column(name = "message_id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", referencedColumnName = "player_id")
	private Player author;

	@Column(name = "publish_date")
	private String publishDate;

	@Column(columnDefinition = "TEXT")
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getAuthor() {
		return author;
	}

	public void setAuthor(Player author) {
		this.author = author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSinceDate() {
		try {
			return "publié il y à "
					+ Util.since(Util.stringToDate(publishDate));
		} catch (ParseException e) {
			// if we are not able to parse date we just return null
		}
		return null;
	}
}
