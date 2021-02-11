package au.gov.nehta.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton class that formats information in a standard and consistent way.
 */
public final class StandardFormatter {

    /*
     * Singleton instance.
     */
    private static final StandardFormatter INSTANCE = new StandardFormatter();

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";

    private static final String UNKNOWN_DATE = "<UnknownDate>";

    private static final String UNKNOWN_CLASS = "<UnknownClass>";

    private static final String UNKNOWN_METHOD = "<UnknownMethod>";

    /**
     * Returns an instance of StandardFormatter.
     *
     * @return StandardFormatter instance.
     */
    public static StandardFormatter getInstance() {
        return INSTANCE;
    }

    /*
     * Private constructor to prevent instantiation.
     */
    private StandardFormatter() {
    }

    /**
     * Formats a date-time, e.g. 2008-12-30 12:45:00.000+1000.
     *
     * @param timeInMillis time in milliseconds
     * @return formatted string representing date-time
     */
    public String formatDateTime(long timeInMillis) {
        return formatDateTime(new Date(timeInMillis));
    }

    /**
     * Formats a date-time, e.g. 2008-12-30 12:45:00.000+1000.
     *
     * @param date date object
     * @return formatted string representing date-time or an empty string if date
     * is null
     */
    public String formatDateTime(Date date) {
        String formattedStr = UNKNOWN_DATE;
        if (date != null) {
            DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
            formattedStr = formatter.format(date);
        }
        return formattedStr;
    }

    /**
     * Formats a code location.
     *
     * @param className  class name
     * @param methodName method name
     * @return formatted string representing the code location
     */
    public String formatLocation(String className, String methodName) {
        // Class name
        String classNameStr = className;
        if (ArgumentUtils.isNullOrBlank(className)) {
            classNameStr = UNKNOWN_CLASS;
        }

        // Method name
        String methodNameStr = methodName;
        if (ArgumentUtils.isNullOrBlank(methodName)) {
            methodNameStr = UNKNOWN_METHOD;
        }

        return String.format("%s.%s()", classNameStr, methodNameStr);
    }
}
