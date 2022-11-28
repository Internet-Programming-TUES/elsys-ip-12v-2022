package org.elsys.ip.timedateformatbg;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DateFormat {
    public String format(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatDay(date.getDay()));
        sb.append(" ");
        sb.append(formatMonth(date.getMonth()));
        sb.append(" ");
        sb.append(formatYear(date.getYear()));

        return sb.toString();
    }

    private String formatYear(int year) {
        List<String> withSeparator = formatYearInternal(year, new ArrayList<>());
        if (withSeparator.size() > 1) {
            withSeparator.add(withSeparator.size() - 1, "и");
        }
        return String.join(" ", withSeparator);
    }

    private List<String> append(List<String> segments, String segment) {
        ArrayList<String> result = new ArrayList<>(segments);
        result.add(segment);
        return result;
    }

    private List<String> formatYearInternal(int year, List<String> segments) {
        switch (year) {
            case 1:
                return append(segments, "първа");
            case 2:
                return append(segments, "втора");
            case 3:
                return append(segments, "трета");
            case 4:
                return append(segments, "четвърта");
            case 5:
                return append(segments, "пета");
            case 6:
                return append(segments, "шеста");
            case 7:
                return append(segments, "седма");
            case 8:
                return append(segments, "осма");
            case 9:
                return append(segments, "девета");
            case 10:
                return append(segments, "десета");
            case 11:
                return append(segments, "единадесета");
            case 12:
                return append(segments, "дванадесета");
            case 13:
                return append(segments, "тринаседета");
            case 14:
                return append(segments, "четиринадесета");
            case 15:
                return append(segments, "петнадесета");
            case 16:
                return append(segments, "шестнадесета");
            case 17:
                return append(segments, "седемнадесета");
            case 18:
                return append(segments, "осемнадесета");
            case 19:
                return append(segments, "деветнадесета");
            case 20:
                return append(segments, "двадесета");
            case 30:
                return append(segments, "тридесета");
            case 40:
                return append(segments, "четиридесета");
            case 50:
                return append(segments, "петдесета");
            case 60:
                return append(segments, "шестдесета");
            case 70:
                return append(segments, "седемдесета");
            case 80:
                return append(segments, "осемдесета");
            case 90:
                return append(segments, "деветдесета");
            case 100:
                return append(segments, "стотна");
            case 200:
                return append(segments, "двестна");
            case 300:
                return append(segments, "тристна");
            case 400:
                return append(segments, "четиристотна");
            case 500:
                return append(segments, "петстотна");
            case 600:
                return append(segments, "шестстотна");
            case 700:
                return append(segments, "седемстотна");
            case 800:
                return append(segments, "осемстотна");
            case 900:
                return append(segments, "деветстотна");
            case 1000:
                return append(segments, "хилядна");
            case 2000:
                return append(segments, "две хилядна");
        }
        int millennial = year / 1000;
        int century = (year % 1000) / 100;
        int decades = (year % 100) / 10;
        switch (millennial) {
            case 1:
                return formatYearInternal(year % 1000, append(segments, "хиляда"));
            case 2:
                return formatYearInternal(year % 1000, append(segments, "две хиляди"));
        }
        switch (century) {
            case 1:
                return formatYearInternal(year % 100, append(segments, "сто"));
            case 2:
                return formatYearInternal(year % 100, append(segments, "двеста"));
            case 3:
                return formatYearInternal(year % 100, append(segments, "триста"));
            case 4:
                return formatYearInternal(year % 100, append(segments, "четиристотин"));
            case 5:
                return formatYearInternal(year % 100, append(segments, "петстотин"));
            case 6:
                return formatYearInternal(year % 100, append(segments, "шестстотин"));
            case 7:
                return formatYearInternal(year % 100, append(segments, "седемстотин"));
            case 8:
                return formatYearInternal(year % 100, append(segments, "осемстотин"));
            case 9:
                return formatYearInternal(year % 100, append(segments, "деветстотин"));
        }
        switch (decades) {
            case 1:
                return formatYearInternal(year % 100, segments);
            case 2:
                return formatYearInternal(year % 10, append(segments,  "двадесет"));
            case 3:
                return formatYearInternal(year % 10, append(segments, "тридесет"));
            case 4:
                return formatYearInternal(year % 10, append(segments, "четиридесет"));
            case 5:
                return formatYearInternal(year % 10, append(segments, "петдесет"));
            case 6:
                return formatYearInternal(year % 10, append(segments, "шестдесет"));
            case 7:
                return formatYearInternal(year % 10, append(segments, "седемдесет"));
            case 8:
                return formatYearInternal(year % 10, append(segments, "осемдесет"));
            case 9:
                return formatYearInternal(year % 10, append(segments, "деветдесет"));
        }

        throw new NumberFormatException();
    }

    private String formatMonth(int month) {
        switch (month) {
            case 1:
                return "януари";
            case 2:
                return "февруари";
            case 3:
                return "март";
            case 4:
                return "април";
            case 5:
                return "май";
            case 6:
                return "юни";
            case 7:
                return "юли";
            case 8:
                return "август";
            case 9:
                return "септември";
            case 10:
                return "октомври";
            case 11:
                return "ноември";
            case 12:
                return "декември";
            default:
                throw new NumberFormatException();
        }
    }

    private String formatDay(int day) {
        switch (day) {
            case 1:
                return "първи";
            case 2:
                return "втори";
            case 3:
                return "трети";
            case 4:
                return "четвърти";
            case 5:
                return "пети";
            case 6:
                return "шести";
            case 7:
                return "седми";
            case 8:
                return "осми";
            case 9:
                return "девети";
            case 10:
                return "десети";
            case 11:
                return "единадесети";
            case 12:
                return "дванадесети";
            case 13:
                return "тринаседети";
            case 14:
                return "четиринадесети";
            case 15:
                return "петнадесети";
            case 16:
                return "шестнадесети";
            case 17:
                return "седемнадесети";
            case 18:
                return "осемнадесети";
            case 19:
                return "деветнадесети";
            case 20:
                return "двадесети";
            case 30:
                return "тридесети";
        }
        int decades = day / 10;
        int numeral = day % 10;
        switch (decades) {
            case 2:
                return "двадесет и " + formatDay(numeral);
            case 3:
                return "тридесет и " + formatDay(numeral);
        }

        throw new NumberFormatException();
    }
}
