package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;
import Exception.BigChungusException;
public class EventAction implements IExecutable {

    public EventAction() {
    }

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidDateFormat, BigChungusException.InvalidTimeFormat, BigChungusException.InvalidDateTimeFormat {
        //event project meeting /sd 11/11/11 /st 11pm /ed 11/12/11 /et 12pm
        Event event = new Event(fields);
        tasks.add(event);
        System.out.println("added event: " + event.print());
    }
}
