package Action;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

import Syntax.SyntaxKeyword;
import Task.*;
import CustomException.BigChungusException;

public class DeadlineAction implements IExecutable {

    public DeadlineAction() {

    }

    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidDateTimeFormatException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.dateTimeFormat);
        Deadline deadline = new Deadline(fields, dtf);
        tasks.add(deadline);
        System.out.println("added deadline: " + deadline.print());
    }
}
