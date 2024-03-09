package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;
import Exception.BigChungusException;

public class DeadlineAction implements IExecutable {

    public DeadlineAction() {

    }

    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidTimeFormat, BigChungusException.InvalidDateFormat {
        Deadline deadline = new Deadline(fields);
        tasks.add(deadline);
        System.out.println("added deadline: " + deadline.print());
    }
}
