package com.igtb.assessment.notificationdispatcher.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class EmailValidator.
 */
public class EmailValidator {
    
    /** The Constant EMAIL_REGEX. */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    /** The pattern. */
    private static Pattern pattern = Pattern.compile(EMAIL_REGEX);

    /**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
    public static boolean validateEmail(final String email) {
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}