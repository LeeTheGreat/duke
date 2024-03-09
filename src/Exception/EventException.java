package Exception;

import Syntax.SyntaxUtil;

public class EventException {

    public static class InvalidSyntaxException extends Exception{
        public InvalidSyntaxException(){
            super(String.format("event syntax invalid. usage example: event <desc> /sd <%s> /st <%s> /ed <%s> /et <%s>", SyntaxUtil.dateFormat, SyntaxUtil.timeFormat, SyntaxUtil.dateFormat, SyntaxUtil.timeFormat));
        }
    }

    public static class InvalidDateFormat extends Exception{
        public InvalidDateFormat(String input){
            super(String.format("Invalid date format: %s. Expected format is dd-MM-yyyy", input));
        }
    }

    public static class InvalidTimeFormat extends Exception{
        public InvalidTimeFormat(String input){
            super(String.format("Invalid time format: %s. Expected format is HHmm", input));
        }
    }
}
