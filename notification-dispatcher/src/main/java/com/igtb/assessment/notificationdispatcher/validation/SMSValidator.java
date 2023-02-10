package com.igtb.assessment.notificationdispatcher.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class SMSValidator.
 */
public class SMSValidator {

    /** The Constant PHONE_NUMBER_REGEX. */
    private static final String PHONE_NUMBER_REGEX = "^\\+[1-9]\\d{1,14}$";

    /**
	 * Checks if recipient is valid.
	 *
	 * @param recipient the recipient
	 * @return true, if is valid
	 */
    public static boolean isValid(final String recipient) {
        final Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        final Matcher matcher = pattern.matcher(recipient);
        return matcher.matches();
    }
}