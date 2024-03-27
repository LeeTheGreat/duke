package main.java.Action;

import main.java.CustomException.BigChungusException;
import main.java.Task.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * A class to create the corresponding action object to handle the input
 * It uses the "action" key from the fields hashtable to determine the action object to create.
 */
public class ActionManager {

    public ActionManager(){}

    /**
     *
     * @param fields hashtable after parsing the input
     * @param tasks list of tasks
     * @throws BigChungusException.InvalidTaskIndexException for input that acts on the index but index is invalid
     * @throws BigChungusException.InvalidDateTimeFormatException for date time that doesn't follow the stated format
     * @throws BigChungusException.StartDateTimeAfterEndDateTimeException self-explanatory
     * @throws BigChungusException.EndDateTimeBeforeStartDateTimeException self-explanatory
     * @throws IOException for file save
     */
    public void executeAction(Hashtable<String, String> fields, List<Task> tasks) throws
            BigChungusException.InvalidTaskIndexException
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
