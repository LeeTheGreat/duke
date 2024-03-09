package Exception;

public class MarkException {
    public static class InvalidMarkSyntaxException extends Exception{
        public InvalidMarkSyntaxException(){
            super("mark/unmark syntax invalid. usage example: <mark | unmark> <num>");
        }
    }

    public static class InvalidIndexException extends Exception{
        public InvalidIndexException(){
            super("Number entered is invalid. Number must be in the numbered list");
        }
    }
}
