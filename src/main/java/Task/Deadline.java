package main.java.Task;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class Deadline extends TaskDateTime {
    private LocalDateTime endDateTime;
    public Deadline(Hashtable<String,String> fields, DateTimeFormatter dtf) throws
            DateTimeParseException
    {
        super(fields);
        this.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
    }

    public String print(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.printDateTimeFormat);
        String info = String.format("end %s", this.getEndDateTime().format(dtf));

        return String.format("[D]%s (%s)", super.print(), info);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String input, DateTimeFormatter dtf) throws
            DateTimeParseException
    {
        try {
            this.endDateTime = LocalDateTime.parse(input, dtf);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(e.getMessage(), input, 0, e);
        }
    }
}
