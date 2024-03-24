package CustomException;

import Syntax.SyntaxKeyword;

public class BigChungusException extends Exception {
    public static class InvalidEventSyntaxException extends Exception{
        public InvalidEventSyntaxException(){
            super(String.format("invalid event syntax. usage example: event <desc> /sdt <%s> /edt <%s>", SyntaxKeyword.dateTimeFormat, SyntaxKeyword.dateTimeFormat));
        }
    }

    public static class InvalidDeadlineSyntaxException extends Exception{
        public InvalidDeadlineSyntaxException(){
            super(String.format("invalid deadline syntax. usage example: deadline <desc> /edt <%s>", SyntaxKeyword.dateTimeFormat));
        }
    }

    public static class InvalidTodoSyntaxException extends Exception{
        public InvalidTodoSyntaxException(){
            super("invalid todo syntax. usage example: todo <desc>");
        }
    }

    public static class InvalidMarkOrDeleteSyntaxException extends Exception{
        public InvalidMarkOrDeleteSyntaxException(){
            super("invalid mark/unmark syntax. usage example: <mark | unmark | delete> <num>");
        }
    }
    public static class InvalidActionException extends Exception {
        public InvalidActionException() {
            super("action keyword not found. available actions: <list | mark | ummark | todo | deadline | event | delete | save >");
        }
    }

    public static class InvalidListSyntaxException extends Exception{
        public InvalidListSyntaxException(){
            super("invalid list syntax. usage example: list");
        }
    }

    public static class JsonTypeKeyNotATaskClass extends Exception{
        public JsonTypeKeyNotATaskClass(String input){
            super(String.format("value of JSON \"Type\" key is not a Task class or its subclass: %s", input));
        }
    }

    public static class InvalidTaskIndexException extends Exception{
        public InvalidTaskIndexException(){
            super("invalid task index. Number must be in the numbered list");
        }
    }

    public static class InvalidDateTimeFormatException extends Exception{
        public InvalidDateTimeFormatException(String input){
            super(String.format("invalid date time format: %s. Expected format is dd-MM-yyyy HHmm", input));
        }
    }

    public static class EndDateTimeBeforeStartDateTimeException extends Exception{
        public EndDateTimeBeforeStartDateTimeException(String input){
            super(String.format("invalid end date time: %s. Must be after start date time", input));
        }
    }

    public static class StartDateTimeAfterEndDateTimeException extends Exception {
        public StartDateTimeAfterEndDateTimeException(String input) {
            super(String.format("invalid start date time: %s. Must be before end date time", input));
        }
    }
}




