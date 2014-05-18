package tool.validator;

public class PasswordValidator extends RegexValidator {
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$";

	// ^ # start-of-string
	// (?=.*[0-9]) # a digit must occur at least once
	// (?=.*[a-zA-Z]) # a lower or upper case letter must occur at least once
	// (?=.*[a-z]) # a lower case letter must occur at least once
	// (?=.*[A-Z]) # an upper case letter must occur at least once
	// (?=.*[@#$%^&+=]) # a special character must occur at least once
	// (?=\S+$) # no whitespace allowed in the entire string
	// .{8,} # anything, at least eight places though
	// $ # end-of-string

	public PasswordValidator() {
		super(PASSWORD_PATTERN, "Le mot de passe n'est pas valide");
	}

}
