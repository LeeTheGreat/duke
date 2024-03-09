package Exception;

import Syntax.SyntaxUtil;

public class BigChungusException extends Exception {
    public static class InvalidEventSyntaxException extends Exception{
        public InvalidEventSyntaxException(){
            super(String.format("invalid event syntax. usage example: event <desc> /sd <%s> /st <%s> /ed <%s> /et <%s>", SyntaxUtil.dateFormat, SyntaxUtil.timeFormat, SyntaxUtil.dateFormat, SyntaxUtil.timeFormat));
        }
    }

    public static class InvalidDeadlineSyntaxException extends Exception{
        public InvalidDeadlineSyntaxException(){
            super(String.format("invalid deadline syntax. usage example: deadline <desc> /ed <%s> /et <%s>", SyntaxUtil.dateFormat, SyntaxUtil.timeFormat));
        }
    }

    public static class InvalidTodoSyntaxException extends Exception{
        public InvalidTodoSyntaxException(){
            super("invalid todo syntax. usage example: todo <desc>");
        }
    }

    public static class InvalidMarkSyntaxException extends Exception{
        public InvalidMarkSyntaxException(){
            super("invalid mark/unmark syntax. usage example: <mark | unmark> <num>");
        }
    }
    public static class InvalidActionException extends Exception {
        public InvalidActionException() {
            super("action keyword not found. available actions: <list | mark | ummark | todo | deadline | event | delete >");
        }
    }

    public static class InvalidListSyntaxException extends Exception{
        public InvalidListSyntaxException(){
            super("invalid list syntax. usage example: list");
        }
    }
    public static class JsonTypeKeyNotATaskClass extends Exception{
        public JsonTypeKeyNotATaskClass(String input){
            super(String.format("value of Json \"Type\" key is not a Task class or its subclass: %s", input));
        }
    }

    public static class InvalidMarkIndexException extends Exception{
        public InvalidMarkIndexException(){
            super("invalid mark/unmark index. Number must be in the numbered list");
        }
    }

    public static class InvalidDateFormat extends Exception{
        public InvalidDateFormat(String input){
            super(String.format("invalid date format: %s. Expected format is dd-MM-yyyy", input));
        }
    }

    public static class InvalidTimeFormat extends Exception{
        public InvalidTimeFormat(String input){
            super(String.format("invalid time format: %s. Expected format is HHmm", input));
        }
    }

    public static class InvalidDateTimeFormat extends Exception{
        public InvalidDateTimeFormat(String input){
            super(String.format("invalid date time format: %s. Expected format is dd-MM-yyyy HHmm", input));
        }
    }

    public static class EndDateTimeBeforeToday extends Exception{
        public EndDateTimeBeforeToday(String input){
            super(String.format("invalid end date time: %s. End date time must be after current date time", input));
        }
    }

    public static class EndDateTimeBeforeStartDateTime extends Exception{
        public EndDateTimeBeforeStartDateTime(String input){
            super(String.format("invalid end date time: %s. End date time must be after start date time", input));
        }
    }

}




