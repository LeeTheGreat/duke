package main.java.CustomException;

import main.java.Syntax.SyntaxKeyword;

public class BigChungusException {

    public static class InvalidEventSyntaxException extends Exception{
        public InvalidEventSyntaxException(){
            super(String.format("invalid event syntax. usage example: event <desc> /sdt <%s> /edt <%s>", SyntaxKeyword.inputDateTimeFormat, SyntaxKeyword.inputDateTimeFormat));
        }
    }

    public static class InvalidDeadlineSyntaxException extends Exception{
        public InvalidDeadlineSyntaxException(){
            super(String.format("invalid deadline syntax. usage example: deadline <desc> /edt <%s>", SyntaxKeyword.inputDateTimeFormat));
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

    public static class InvalidDeleteSyntaxException extends Exception{
        public InvalidDeleteSyntaxException(){
            super("invalid mark/unmark syntax. usage example: delete <num>");
        }
    }

    public static class InvalidFindSyntaxException extends Exception{
        public InvalidFindSyntaxException(){
            super("invalid find syntax. usage example: find <search terms>");
        }
    }
    public static class InvalidActionException extends Exception {
        public InvalidActionException() {
            super("action keyword not found. available actions: <list | mark | ummark | todo | deadline | event | delete | save>");
        }
    }

    public static class JsonTypeKeyNotATaskClass extends Exception{
        public JsonTypeKeyNotATaskClass(String input){
            super(String.format("value of JSON \"Type\" key is not a main.java.Task class or its subclass: %s", input));
        }
    }

    public static class InvalidTaskIndexException extends Exception{
        public InvalidTaskIndexException(){
            super("invalid task index. Number must be in the numbered list");
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




