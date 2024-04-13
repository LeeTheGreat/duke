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

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws
            BigChungusException.StartDateTimeAfterEndDateTimeException
            , BigChungusException.EndDateTimeBeforeStartDateTimeException
    {
        //event e1 /sdt 10-01-2024 1122 /edt 22-05-2024 1123
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        Event event = new Event(fields, dtf);
        tasks.add(event);
        System.out.println("added event: " + event.print());
    }
}
