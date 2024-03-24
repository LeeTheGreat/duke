package Action;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

import Syntax.SyntaxKeyword;

import CustomException.BigChungusException;
import Task.*;

public class EventAction implements IExecutable {

    public EventAction() {
    }

    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws
            BigChungusException.InvalidDateTimeFormatException
            , BigChungusException.StartDateTimeAfterEndDateTimeException
            , BigChungusException.EndDateTimeBeforeStartDateTimeException
    {
        //event e1 /sdt 10-01-2024 1122 /edt 22-05-2024 1123
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.dateTimeFormat);
        Event event = new Event(fields, dtf);
        tasks.add(event);
        System.out.println("added event: " + event.print());
    }
}
