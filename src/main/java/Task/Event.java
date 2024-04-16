package main.java.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import main.java.Syntax.SyntaxKeyword;
import main.java.CustomException.BigChungusException;

public class Event extends TaskDateTime {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    public Event(Hashtable<String,String> fields, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException {
        super(fields);
        this.setStartDateTime(fields.get(SyntaxKeyword.startDateTimeKeyword), dtf);
        this.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
    }

    public String print(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.printDateTimeFormat);
        String info = String.format("start %s, end %s", this.getStartDateTime().format(dtf), this.getEndDateTime().format(dtf));
        return String.format("[E]%s (%s)", super.print(), info);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String input, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException
    {
        try {
            LocalDateTime sdt = LocalDateTime.parse(input, dtf);
            super.checkLogicalDateTime(sdt, this.getEndDateTime(), dtf);
            this.startDateTime = sdt;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("invalid date time format: %s", input), input, 0, e);
        }
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String input, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException
    {
        try {
            LocalDateTime edt = LocalDateTime.parse(input, dtf);
            super.checkLogicalDateTime(this.getStartDateTime(), edt, dtf);
            this.endDateTime = edt;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("invalid date time format: %s", input), input, 0, e);
        }
    }
}
