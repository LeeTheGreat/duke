package Action;

import java.util.Hashtable;
import java.util.List;

import CustomException.BigChungusException;
import Task.*;

public class DeleteAction implements IExecutable {

    public DeleteAction(){

    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidTaskIndexException {
        try {
            int index = Integer.parseInt(fields.get("num"));
            Task task = tasks.remove(index - 1);
            System.out.printf("task %d deleted: %s%n", index, task.print());
        }
        catch (IndexOutOfBoundsException e) {
            throw new BigChungusException.InvalidTaskIndexException();
        }
    }
}
