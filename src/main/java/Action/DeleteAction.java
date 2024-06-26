package main.java.Action;

import java.util.Hashtable;
import java.util.List;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Task.*;

public class DeleteAction implements IExecutable {

    public DeleteAction(){

    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidTaskIndexException {
        try {
            int index = Integer.parseInt(fields.get(SyntaxKeyword.num));
            Task task = tasks.remove(index - 1);
            System.out.printf("task %d deleted: %s%n", index, task.print());
        }
        catch (IndexOutOfBoundsException e) {
            throw new BigChungusException.InvalidTaskIndexException();
        }
    }
}
