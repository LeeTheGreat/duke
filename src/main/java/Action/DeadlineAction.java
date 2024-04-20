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
    public void execute(Hashtable<String, String> fields, List<Task> tasks) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        Deadline task = new Deadline(fields, dtf);
        if(tasks.contains(task)){
            throw new IllegalArgumentException("unable to add deadline. Deadline already exists");
        }
        tasks.add(task);
        System.out.println("added deadline: " + task.print());
    }
}
