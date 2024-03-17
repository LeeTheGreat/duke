package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;
import CustomException.BigChungusException;

public class UnmarkAction implements IExecutable {

    public UnmarkAction(){

    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidIndexException {
        try {
            int index = Integer.parseInt(fields.get("num"));
            Task task = tasks.get(index - 1);
            task.setDone(false);
            System.out.printf("task %o undone: %s%n", index, task.print());
        }

        catch (IndexOutOfBoundsException e){
            throw new BigChungusException.InvalidIndexException();
        }
    }
}
