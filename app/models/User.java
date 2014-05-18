package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import tool.validator.MailValidator;
import tool.validator.PasswordValidator;

/**
 * User entity managed by Ebean
 */
@Entity
@Table(name = "user")
public class User extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private Integer id;

	@Constraints.Required
	@Constraints.ValidateWith(MailValidator.class)
	@Formats.NonEmpty
	private String email;

	@Constraints.Required
	@Constraints.MaxLength(20)
	private String username;

	@Constraints.Required
	@Constraints.MaxLength(80)
	@Constraints.ValidateWith(PasswordValidator.class)
	private String password;

	@Column(name = "image_path")
	private String imagePath;

	private String name;

	private String surname;

	@Column(name = "phone_home")
	private String phoneHome;

	@Column(name = "phone_cell")
	private String phoneCell;

	@Column(name = "phone_other")
	private String phoneOther;

	private String address;

	@Column(name = "last_connection")
	private String lastConnection;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getPhoneCell() {
		return phoneCell;
	}

	public void setPhoneCell(String phoneCell) {
		this.phoneCell = phoneCell;
	}

	public String getPhoneOther() {
		return phoneOther;
	}

	public void setPhoneOther(String phoneOther) {
		this.phoneOther = phoneOther;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
	}

	// -- Queries
	private static Model.Finder<String, User> find = new Model.Finder<String, User>(
			String.class, User.class);

	/**
	 * Retrieve all users.
	 */
	public static List<User> findAll() {
		return find.all();
	}

	/**
	 * Retrieve a User from email.
	 */
	public static User findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	/**
	 * Authenticate a User.
	 */
	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password)
				.findUnique();
	}
}
