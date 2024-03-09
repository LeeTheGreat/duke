package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;

public class DeleteAction implements IExecutable {

    public DeleteAction(){

    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks) {
        int index = Integer.parseInt(fields.get("num"));
        Task task = tasks.remove(index - 1);
        System.out.printf("task %d deleted: %s%n", index, task.print());
    }
}
