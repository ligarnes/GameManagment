package tool.validator;


public class MailValidator extends RegexValidator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public MailValidator() {
		super(EMAIL_PATTERN, "Le courriel n'est pas valide");
	}
}
