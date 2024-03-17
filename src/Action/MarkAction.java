package Action;

import java.util.Hashtable;
import java.util.List;

import CustomException.BigChungusException;
import Task.*;

public class MarkAction implements IExecutable {

    public static String numKey = "num";
    public MarkAction(){
    }

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidIndexException {
        try {
            int index = Integer.parseInt(fields.get(MarkAction.numKey));
            Task task = tasks.get(index - 1);
            task.setDone(true);
            System.out.printf("task %d done: %s%n", index, task.print());
        }
        catch (IndexOutOfBoundsException e){
            throw new BigChungusException.InvalidIndexException();
        }
    }

}
