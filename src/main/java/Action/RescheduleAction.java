package main.java.Action;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Task.Deadline;
import main.java.Task.Event;
import main.java.Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.List;

public class RescheduleAction implements IExecutable {

    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws Exception {
        int index;
        Task task;
        try{
            index = Integer.parseInt(fields.get(SyntaxKeyword.num));
            task =  tasks.get(index - 1);
        }
        catch (NumberFormatException e){
            throw new BigChungusException.InvalidRescheduleSyntaxException();
        }
        catch (IndexOutOfBoundsException e){
            throw new BigChungusException.InvalidTaskIndexException();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        if(task instanceof Deadline t) {
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                t.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
            }
        }
        if(task instanceof Event t){
            t.setDateTime(fields.get(SyntaxKeyword.startDateTimeKeyword), fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
            /*
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                String time = fields.get(SyntaxKeyword.startDateTimeKeyword);
                try {
                    sdt = LocalDateTime.parse(time, dtf);
                    fields.put(SyntaxKeyword.startDateTimeKeyword, time);
                }
                catch (DateTimeParseException e){
                    throw new DateTimeParseException(String.format("invalid date time %s. Expecting format %s", time, SyntaxKeyword.inputDateTimeFormat), time, 0, e);
                }
            }
            else{
                fields.put(SyntaxKeyword.startDateTimeKeyword, sdt.format(dtf));
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                String time = fields.get(SyntaxKeyword.endDateTimeKeyword);
                try {
                    edt = LocalDateTime.parse(time, dtf);

                }
                catch (DateTimeParseException e){
                    throw new DateTimeParseException(String.format("invalid date time %s. Expecting %s", time, SyntaxKeyword.inputDateTimeFormat), time, 0, e);
                }
            }
            else{
                fields.put(SyntaxKeyword.endDateTimeKeyword, edt.format(dtf));
            }

            // create new event as setting existing dateTime will trigger the isBefore check which may cause issues for certain condition
            Event tNew = new Event(fields, dtf);
            tNew.setDescription(t.getDescription());
            tNew.setDone(t.getDone());
            t.setStartDateTime(LocalDateTime.MIN.format(dtf), dtf);
            t.setEndDateTime(LocalDateTime.MIN.format(dtf), dtf);
            t.setStartDateTime(sdt.format(dtf), dtf);
            t.setEndDateTime(edt.format(dtf), dtf);
            */
            tasks.set(index - 1, t);
        }
        System.out.printf("rescheduled task: %s%n", task.print());
    }
}
