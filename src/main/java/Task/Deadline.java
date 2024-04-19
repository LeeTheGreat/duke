package main.java.Task;

import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class Deadline extends TaskDateTime {
    private LocalDateTime endDateTime;
    public Deadline(Hashtable<String,String> fields, DateTimeFormatter dtf) throws DateTimeParseException
    {
        super(fields);
        assert(fields.containsKey(SyntaxKeyword.endDateTimeKeyword));
        this.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
    }

    public String print(){
        assert(this.getEndDateTime() != null);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.printDateTimeFormat);
        String info = String.format("end %s", this.getEndDateTime().format(dtf));
        return String.format("[D]%s (%s)", super.print(), info);
    }

    public String toString(){
        assert(this.getEndDateTime() != null);
        String[] info = {"deadline", this.getDescription(), String.valueOf(this.getDone()), String.valueOf(this.getEndDateTime())};
        return String.join(";;", info);
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String input, DateTimeFormatter dtf) {
        try {
            this.endDateTime = LocalDateTime.parse(input, dtf);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("invalid date time %s. Expected format %s", input, SyntaxKeyword.inputDateTimeFormat), input, 0, e);
        }
    }
}
