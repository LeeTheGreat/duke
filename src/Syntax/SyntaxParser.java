package Syntax;

import Action.MarkAction;
import CustomException.BigChungusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class SyntaxParser {

    public static Hashtable<String, String> Parse(String input) throws
            BigChungusException.InvalidTodoSyntaxException
            , BigChungusException.InvalidDeadlineSyntaxException
            , BigChungusException.InvalidEventSyntaxException
            , BigChungusException.InvalidActionException
            , BigChungusException.InvalidMarkOrDeleteSyntaxException
    {
        List<String> tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
        Hashtable<String,String> fields = new Hashtable<>();
        String action = tokens.get(0);
        fields.put("action", action);
        if(action.equals("list")){
        }
        else if(action.equals("unmark") || action.equals("mark") || action.equals("delete")){
            try {
                String num = tokens.get(1);
                fields.put(MarkAction.numKey, num);
            }
            catch (IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidMarkOrDeleteSyntaxException();
            }
        }
        else if(action.equals("todo")){
            try {
                String desc = String.join(" ", tokens.subList(1, tokens.size()));
                fields.put(SyntaxUtil.description, desc);
            }
            catch (IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidTodoSyntaxException();
            }
        }
        else if(action.equals("deadline")){
            String edt = "";
            try {
                int edtIndex = tokens.lastIndexOf(SyntaxUtil.endDateTimeKeyword);
                String desc = String.join(" ", tokens.subList(1, edtIndex));
                edt = String.join(" ", tokens.subList(edtIndex + 1, tokens.size()));
                fields.put(SyntaxUtil.description, desc);
                fields.put(SyntaxUtil.endDateTimeKeyword, edt);
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidDeadlineSyntaxException();
            }

        }
        else if(action.equals("event")){
            String sdt = "";
            String edt = "";
            try {
                int sdtIndex = tokens.lastIndexOf(SyntaxUtil.startDateTimeKeyword);
                int edtIndex = tokens.lastIndexOf(SyntaxUtil.endDateTimeKeyword);
                String desc = String.join(" ", tokens.subList(1, sdtIndex));
                sdt = String.join(" ", tokens.subList(sdtIndex + 1, edtIndex));
                edt = String.join(" ", tokens.subList(edtIndex + 1, tokens.size()));
                fields.put(SyntaxUtil.description, desc);
                fields.put(SyntaxUtil.startDateTimeKeyword, sdt);
                fields.put(SyntaxUtil.endDateTimeKeyword, edt);
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidEventSyntaxException();
            }
        }
        else if(action.equals("save")){
        }
        else{
            throw new BigChungusException.InvalidActionException();
        }
        return fields;
    }
}
