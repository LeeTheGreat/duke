package main.java.Action;

import java.util.Hashtable;
import java.util.List;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Task.*;

public class MarkAction implements IExecutable {
    public MarkAction(){
    }

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidTaskIndexException {
        try {
            int index = Integer.parseInt(fields.get(SyntaxKeyword.num));
            Task task = tasks.get(index - 1);
            task.setDone(true);
            System.out.printf("task %d done: %s%n", index, task.print());
        }
        catch (IndexOutOfBoundsException e){
            throw new BigChungusException.InvalidTaskIndexException();
        }
    }

}
