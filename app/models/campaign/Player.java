package models.campaign;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.User;

@Entity
@Table(name = "campaign_player")
public class Player {
	@Id
	@Column(name = "player_id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "real_user_id", referencedColumnName = "user_id")
	private User user;

	private String color;

	@Column(name = "is_manager")
	private boolean isManager;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

}
