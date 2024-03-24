package Task;

import CustomException.BigChungusException;
import Syntax.SyntaxKeyword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class Deadline extends TaskDateTime {
    private LocalDateTime endDateTime;
    public Deadline(Hashtable<String,String> fields, DateTimeFormatter dtf) throws
            BigChungusException.InvalidDateTimeFormatException
    {
        super(fields);
        this.setEndDateTime(fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
    }

    public String print(){
        String info = String.format("end %s", this.getEndDateTime());
        return String.format("[D]%s (%s)", super.print(), info);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String input, DateTimeFormatter dtf) throws
            BigChungusException.InvalidDateTimeFormatException
    {
        try {
            this.endDateTime = LocalDateTime.parse(input, dtf);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidDateTimeFormatException(input);
        }
    }
}
