package main.java.Action;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Task.Deadline;
import main.java.Task.Event;
import main.java.Task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class RescheduleAction implements IExecutable {

    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws Exception {
        int index;
        Task task;
        try{
            index = Integer.parseInt(fields.get(SyntaxKeyword.num)) - 1;
            task =  tasks.get(index);
        }
        catch (NumberFormatException e){
            throw new BigChungusException.InvalidRescheduleSyntaxException();
        }
        catch (IndexOutOfBoundsException e){
            throw new BigChungusException.InvalidTaskIndexException();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        if(!(task instanceof Deadline || task instanceof Event)){
            throw new BigChungusException.UnschedulableTaskException();
        }
        if(task instanceof Deadline t) {
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                String oldEdt = t.getEndDateTime().format(dtf);
                t.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
                List<Task> newTasks = new ArrayList<>(tasks);
                newTasks.remove(index);
                if(newTasks.contains(t)){
                    t.setEndDateTime(oldEdt, dtf);
                    throw new IllegalArgumentException("unable to reschedule deadline. Deadline already exists");
                }
            }
            System.out.printf("rescheduled deadline: %s%n", task.print());
        }
        else if(task instanceof Event t){
            String oldSdt = t.getStartDateTime().format(dtf);
            String oldEdt  = t.getEndDateTime().format(dtf);
            t.setDateTime(fields.get(SyntaxKeyword.startDateTimeKeyword), fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
            List<Task> newTasks = new ArrayList<>(tasks);
            newTasks.remove(index);
            if(newTasks.contains(t)){
                t.setDateTime(oldSdt, oldEdt, dtf);
                throw new IllegalArgumentException("unable to reschedule event. Event already exists");
            }
            System.out.printf("rescheduled event: %s%n", task.print());
        }

    }
}

