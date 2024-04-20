package main.java.Task;

import main.java.CustomException.BigChungusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

/**
 * An abstract class to provide a common date time checking function for tasks that has data time data.
 */
public abstract class TaskDateTime extends Task{

    public TaskDateTime(Hashtable<String, String> fields) {
        super(fields);
    }
}
