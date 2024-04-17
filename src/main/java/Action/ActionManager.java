package main.java.Action;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Syntax.SyntaxParser;
import main.java.Task.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * ActionManager is to expose a single class that handles all the actions.
 * It uses the "action" key from the fields parameter to determine the action object to create.
 * Then, it creates the corresponding action object, and call the execute function of the action object.
 */

public class ActionManager {

    public ActionManager(){}

    /**
     *
     * @param fields hashtable of parsed fields
     * @param tasks list of tasks
     * @throws Exception
     */
    public void executeAction(Hashtable<String, String> fields, List<Task> tasks) throws Exception {
        String action = fields.get(SyntaxKeyword.action);
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
        } else if (action.equals("find")){
            FindAction act = new FindAction();
            act.execute(fields, tasks);
        } else if (action.equals("reschedule")){
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
    }
}
