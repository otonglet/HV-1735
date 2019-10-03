package otonglet.HV1735.testcase.configuration;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.springframework.util.StringUtils;

public class NoOpMessageInterpolator implements MessageInterpolator {
    @Override
    public String interpolate(String messageTemplate, Context context) {
        return removeCurlyBraces(messageTemplate);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return removeCurlyBraces(messageTemplate);
    }

    public static String removeCurlyBraces(String s) {
        if (StringUtils.isEmpty(s))
            return s;

        StringBuilder sb = new StringBuilder(s);
        if ('{' == sb.charAt(0))
            sb.deleteCharAt(0);
        if ('}' == sb.charAt(sb.length() - 1))
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

