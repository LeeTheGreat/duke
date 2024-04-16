package main.java.Syntax;

import main.java.CustomException.BigChungusException;

import java.util.*;

/**
 * parses the input syntax to a hashtable to be used by the ActionManager
 */
public class SyntaxParser {

    /**
     *
     * @param input input from the user
     * @return hashtable containing the keywords mapped to the value
     * @throws BigChungusException.InvalidTodoSyntaxException
     * @throws BigChungusException.InvalidDeadlineSyntaxException
     * @throws BigChungusException.InvalidEventSyntaxException
     * @throws BigChungusException.InvalidActionException
     * @throws BigChungusException.InvalidDeleteSyntaxException
     * @throws BigChungusException.InvalidFindSyntaxException
     * @throws BigChungusException.InvalidMarkSyntaxException
     * @throws BigChungusException.InvalidDeleteSyntaxException
     * @throws BigChungusException.InvalidRescheduleSyntaxException
     */
    public static Hashtable<String, String> parse(String input) throws
            BigChungusException.InvalidTodoSyntaxException
            , BigChungusException.InvalidDeadlineSyntaxException
            , BigChungusException.InvalidEventSyntaxException
            , BigChungusException.InvalidActionException
            , BigChungusException.InvalidFindSyntaxException
            , BigChungusException.InvalidMarkSyntaxException
            , BigChungusException.InvalidDeleteSyntaxException
            , BigChungusException.InvalidRescheduleSyntaxException {
        List<String> tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
        Hashtable<String,String> fields = new Hashtable<>();
        String action = tokens.get(0);
        fields.put("action", action);
        if(action.equals("list")){
        }
        else if(action.equals("unmark") || action.equals("mark")){
            try {
                String num = tokens.get(1);
                fields.put(SyntaxKeyword.num, num);
            }
            catch (IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidMarkSyntaxException();
            }
        }
        else if(action.equals("delete")){
            try {
                String num = tokens.get(1);
                fields.put(SyntaxKeyword.num, num);
            }
            catch (IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidDeleteSyntaxException();
            }
        }
        else if(action.equals("todo")){
            try {
                String desc = String.join(" ", tokens.subList(1, tokens.size()));
                if(desc.isEmpty()){
                    throw new BigChungusException.InvalidTodoSyntaxException();
                }
                fields.put(SyntaxKeyword.description, desc);
            }
            catch (IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidTodoSyntaxException();
            }
        }
        else if(action.equals("deadline")){
            String edt = "";
            try {
                int edtIndex = tokens.lastIndexOf(SyntaxKeyword.endDateTimeKeyword);
                String desc = String.join(" ", tokens.subList(1, edtIndex));
                if(desc.isEmpty()){
                    throw new BigChungusException.InvalidDeadlineSyntaxException();
                }
                edt = String.join(" ", tokens.subList(edtIndex + 1, tokens.size()));
                fields.put(SyntaxKeyword.description, desc);
                fields.put(SyntaxKeyword.endDateTimeKeyword, edt);
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidDeadlineSyntaxException();
            }

        }
        else if(action.equals("event")){
            String sdt = "";
            String edt = "";
            try {
                int sdtIndex = tokens.lastIndexOf(SyntaxKeyword.startDateTimeKeyword);
                int edtIndex = tokens.lastIndexOf(SyntaxKeyword.endDateTimeKeyword);
                String desc = String.join(" ", tokens.subList(1, sdtIndex));
                if(desc.isEmpty()){
                    throw new BigChungusException.InvalidEventSyntaxException();
                }
                sdt = String.join(" ", tokens.subList(sdtIndex + 1, edtIndex));
                edt = String.join(" ", tokens.subList(edtIndex + 1, tokens.size()));
                fields.put(SyntaxKeyword.description, desc);
                fields.put(SyntaxKeyword.startDateTimeKeyword, sdt);
                fields.put(SyntaxKeyword.endDateTimeKeyword, edt);
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidEventSyntaxException();
            }
        }
        else if(action.equals("save")){
        }
        else if(action.equals("find")){
            try {
                String term = String.join(" ", tokens.subList(1, tokens.size()));
                fields.put(SyntaxKeyword.find, term);
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidFindSyntaxException();
            }
        }
        else if(action.equals("reschedule")){
            try{
                fields.put(SyntaxKeyword.num, tokens.get(1));
                List<Integer> keywordsIndex = new ArrayList<>();
                keywordsIndex.add(tokens.lastIndexOf(SyntaxKeyword.startDateTimeKeyword));
                keywordsIndex.add(tokens.lastIndexOf(SyntaxKeyword.endDateTimeKeyword));
                keywordsIndex.sort(Collections.reverseOrder());
                if(keywordsIndex.get(0) == -1){
                    throw new BigChungusException.InvalidRescheduleSyntaxException();
                }
                int endIndex = tokens.size();
                for(Integer i : keywordsIndex){
                    if(i == -1){
                        break;
                    }
                    String keyword = tokens.get(i);
                    String term = String.join(" ", tokens.subList(i + 1, endIndex));
                    fields.put(keyword, term);
                    endIndex = i;
                }
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e){
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
        }
        else{
            throw new BigChungusException.InvalidActionException();
        }
        return fields;
    }

    private List<Integer> sortKeywords(List<String> tokens){
        List<Integer> keywordsIndex = new ArrayList<>();
        keywordsIndex.add(tokens.lastIndexOf(SyntaxKeyword.startDateTimeKeyword));
        keywordsIndex.add(tokens.lastIndexOf(SyntaxKeyword.endDateTimeKeyword));
        keywordsIndex.sort(Collections.reverseOrder());
        return keywordsIndex;
    }
}
