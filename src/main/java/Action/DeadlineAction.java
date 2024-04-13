package main.java.Action;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

import main.java.Syntax.SyntaxKeyword;
import main.java.Task.*;
import main.java.CustomException.BigChungusException;

public class DeadlineAction implements IExecutable {

    public DeadlineAction() {

    }

    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws BigChungusException.InvalidDateTimeFormatException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        Deadline deadline = new Deadline(fields, dtf);
        tasks.add(deadline);
        System.out.println("added deadline: " + deadline.print());
    }
}
