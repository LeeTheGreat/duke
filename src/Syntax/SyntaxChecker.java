package Syntax;

import Exception.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SyntaxChecker {
    private List<String> tokens;

    public SyntaxChecker(String input){
        tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
        //System.out.println(tokens.toString());
    }

    public void checkSyntax() throws BigChungusException.InvalidListSyntaxException
            , BigChungusException.InvalidMarkSyntaxException
            , BigChungusException.InvalidTodoSyntaxException
            , BigChungusException.InvalidDeadlineSyntaxException
            , BigChungusException.InvalidEventSyntaxException
            , BigChungusException.InvalidActionException {
        if(tokens.get(0).equals("list")){
            isValidListSyntax();
        }
        else if(tokens.get(0).equals("mark") || tokens.get(0).equals("unmark")){
            isValidTaskNumSyntax();
        }
        else if(tokens.get(0).equals("todo")){
            isValidTodoSyntax();
        }
        else if(tokens.get(0).equals("deadline")){
            isValidDeadlineSyntax();
        }
        else if(tokens.get(0).equals("event")){
            isValidEventSyntax();
        }
        else{
            throw new BigChungusException.InvalidActionException();
        }

    }

    protected void isValidListSyntax() throws BigChungusException.InvalidListSyntaxException {
        if(getTokens().size() != 1 && !getTokens().get(0).equals("list")){
            throw new BigChungusException.InvalidListSyntaxException();
        }
    }

    protected void isValidTaskNumSyntax() throws BigChungusException.InvalidMarkSyntaxException {
        if(getTokens().size() != 2){
            throw new BigChungusException.InvalidMarkSyntaxException();
        }
    }

    protected void isValidTodoSyntax() throws BigChungusException.InvalidTodoSyntaxException {
        if(getTokens().size() < 2){
            throw new BigChungusException.InvalidTodoSyntaxException();
        }
    }

    protected void isValidDeadlineSyntax() throws BigChungusException.InvalidDeadlineSyntaxException {
        if(!isValidSyntaxWithKeywords("deadline")){
            throw new BigChungusException.InvalidDeadlineSyntaxException();
        }
    }

    protected void isValidEventSyntax() throws BigChungusException.InvalidEventSyntaxException {
        if(!isValidSyntaxWithKeywords("event")){
            throw new BigChungusException.InvalidEventSyntaxException();
        }
    }

    protected boolean isValidSyntaxWithKeywords(String action){
        String[] keywords;
        if(action.equals("deadline")){
            keywords = SyntaxUtil.getDeadlineKeywords();
        }
        else if(action.equals("event")){
            keywords = SyntaxUtil.getEventKeywords();
        }
        else{
            return false;
        }
        List<Integer> keywordsIndex = new ArrayList<Integer>();
        int maxIndex = 0;
        for(String keyword : keywords) {
            maxIndex = Math.max(maxIndex, getTokens().indexOf(keyword));
            keywordsIndex.add(getTokens().indexOf(keyword));
            if(!getTokens().contains(keyword)){ // keyword missing
                return false;
            }
            if(getTokens().indexOf(keyword) == 1){ // no description
                return false;
            }
        }
        Collections.sort(keywordsIndex);
        for(int i = 0; i < keywordsIndex.size() - 1; i++) { // check if there's any extra words between the keywords
            int diff = keywordsIndex.get(i + 1) - keywordsIndex.get(i);
            if(diff != 2){
                return false;
            }
        }
        if(getTokens().size() != (maxIndex + 2)){ // check if there's any text after the last keyword
            return false;
        }
        return true;
    }
    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
