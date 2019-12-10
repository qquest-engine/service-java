package ua.ithillel.evo.questengine.web.validation;

import org.springframework.util.StringUtils;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.exception.UserValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String EMPTY_PROPERTY_EXCEPTION_MESSAGE = "User field parameter '%s' must be provided";
    private static final String REGEX_EXCEPTION_MESSAGE = "User field parameter '%s' not correct. '%s' ";
    private static String VALID_EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static void validate(User user) throws UserValidationException {
        validateWithRegularExpression(user.getEmail(), VALID_EMAIL_REGEX, "email", "Email address not correct.");
        validateNotEmptyProperty(user.getEmail(), "email");
    }

    private static void validateNotEmptyProperty(Object value, String propertyName) throws UserValidationException {
        if (value == null || StringUtils.isEmpty(value)) {
            throw new UserValidationException(String.format(EMPTY_PROPERTY_EXCEPTION_MESSAGE, propertyName));
        }
    }

    private static void validateWithRegularExpression(Object value,
                                                      String regex,
                                                      String propertyName,
                                                      String exceptionMessage) throws UserValidationException {

        Matcher matcher = Pattern.compile(regex).matcher(String.valueOf(value));
        if (!matcher.matches()) {
            throw new UserValidationException(String.format(REGEX_EXCEPTION_MESSAGE, propertyName, exceptionMessage));
        }
    }
}