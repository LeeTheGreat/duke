package Action;

import CustomException.BigChungusException;
import Task.Task;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class ActionManager {
    public ActionManager(){}
    public void executeAction(Hashtable<String, String> fields, List<Task> tasks) throws
            BigChungusException.InvalidIndexException
            , BigChungusException.InvalidDateTimeFormatException
            , BigChungusException.StartDateTimeAfterEndDateTimeException
            , BigChungusException.EndDateTimeBeforeStartDateTimeException
            , IOException {
        String action = fields.get("action");
        if (action.equals("list")) {
            ListAction act = new ListAction();
            act.execute(fields, tasks);
        } else if (action.equals("mark")) {
            MarkAction act = new MarkAction();
            act.execute(fields, tasks);
        } else if (action.equals("unmark")) {
            UnmarkAction act = new UnmarkAction();
            act.execute(fields, tasks);
        } else if (action.equals("todo")) {
            TodoAction act = new TodoAction();
            act.execute(fields, tasks);
        } else if (action.equals("deadline")) {
            DeadlineAction act = new DeadlineAction();
            act.execute(fields, tasks);
        } else if (action.equals("event")) {
            EventAction act = new EventAction();
            act.execute(fields, tasks);
        } else if (action.equals("delete")) {
            DeleteAction act = new DeleteAction();
            act.execute(fields, tasks);
        } else if (action.equals("save")) {
            SaveAction act = new SaveAction();
            act.execute(fields, tasks);
        }
    }
}
