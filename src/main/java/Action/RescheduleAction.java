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
        boolean hasKeyword = false;
        if(task instanceof Deadline) {
            Deadline t = (Deadline)task;
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                t.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
                hasKeyword = true;
            }
            if(!hasKeyword){
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
        }
        if(task instanceof Event){
            Event t = (Event)task;
            LocalDateTime sdt = t.getStartDateTime();
            LocalDateTime edt = t.getEndDateTime();
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                String time = fields.get(SyntaxKeyword.startDateTimeKeyword);
                try {
                    sdt = LocalDateTime.parse(time, dtf);
                }
                catch (DateTimeParseException e){
                    throw new DateTimeParseException(String.format("invalid date time: format %s", time), time, 0, e);
                }
                hasKeyword = true;
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                String time = fields.get(SyntaxKeyword.endDateTimeKeyword);
                try {
                    edt = LocalDateTime.parse(time, dtf);
                }
                catch (DateTimeParseException e){
                    throw new DateTimeParseException(String.format("invalid date time: format %s", time), time, 0, e);
                }
                hasKeyword = true;
            }
            if(sdt.isAfter(edt) || sdt.equals(edt)){
                throw new BigChungusException.IllogicalDateTimeException();
            }
            if(!hasKeyword){
                throw new BigChungusException.InvalidRescheduleSyntaxException();
            }
            if(fields.containsKey(SyntaxKeyword.startDateTimeKeyword)) {
                t.setStartDateTime(fields.get(SyntaxKeyword.startDateTimeKeyword), dtf);
            }
            if(fields.containsKey(SyntaxKeyword.endDateTimeKeyword)) {
                t.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
            }
        }
        System.out.printf("rescheduled task: %s%n", task.print());
    }
}
