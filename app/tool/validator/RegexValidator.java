package tool.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.data.validation.Constraints;
import play.libs.F.Tuple;

public abstract class RegexValidator extends Constraints.Validator<String> {

	private final Pattern pattern;
	private final String errorMessage;

	public RegexValidator(String regex) {
		this(regex, "The pattern was not recognized");
	}

	public RegexValidator(String regex, String errorMsg) {
		pattern = Pattern.compile(regex);
		errorMessage = errorMsg;
	}

	@Override
	public boolean isValid(String value) {
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		return new Tuple<String, Object[]>(errorMessage, new Object[] {});
	}

}
