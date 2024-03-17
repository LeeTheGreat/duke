package Syntax;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class SyntaxUtil {
    public static String startDateKeyword = "/sd";
    public static String endDateKeyword = "/ed";
    public static String startTimeKeyword = "/st";
    public static String endTimeKeyword = "/et";

    public static String startDateTimeKeyword = "/sdt";
    public static String endDateTimeKeyword = "/edt";
    public static String dateTimeFormat = "dd-MM-yyyy HHmm";

    public static String description = "desc";
    public static String isDone = "isDone";

    public static String[] getDeadlineKeywords(){
        return new String[]{endDateKeyword, endTimeKeyword};
    }

    public static String[] getEventKeywords(){
        return new String[]{startDateKeyword, startTimeKeyword, endDateKeyword, endTimeKeyword};
    }
}
