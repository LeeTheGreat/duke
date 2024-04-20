package main.java.Action;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

import main.java.Syntax.SyntaxKeyword;

import main.java.CustomException.BigChungusException;
import main.java.Task.*;

public class EventAction implements IExecutable {

    public EventAction() {
    }

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.IllogicalDateTimeException
    {
        //event e1 /sdt 10-01-2024 1122 /edt 22-05-2024 1123
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        Event task = new Event(fields, dtf);
        if(tasks.contains(task)){
            throw new IllegalArgumentException("unable to add event. Event already exists");
        }
        tasks.add(task);
        System.out.println("added event: " + task.print());
    }
}
