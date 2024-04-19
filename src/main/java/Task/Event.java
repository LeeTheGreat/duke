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
        assert(fields.containsKey(SyntaxKeyword.startDateTimeKeyword));
        assert(fields.containsKey(SyntaxKeyword.endDateTimeKeyword));
        this.setDateTime(fields.get(SyntaxKeyword.startDateTimeKeyword), fields.get(SyntaxKeyword.endDateTimeKeyword), dtf);
    }

    public String print(){
        assert(this.getStartDateTime() != null);
        assert(this.getEndDateTime() != null);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.printDateTimeFormat);
        String info = String.format("start %s, end %s", this.getStartDateTime().format(dtf), this.getEndDateTime().format(dtf));
        return String.format("[E]%s (%s)", super.print(), info);
    }

    public String toString(){
        assert(this.getStartDateTime() != null);
        assert(this.getEndDateTime() != null);
        String[] info = {"event", this.getDescription(), String.valueOf(this.getDone()), String.valueOf(this.getStartDateTime()), String.valueOf(this.getEndDateTime())};
        return String.join(";;", info);
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    private void setStartDateTime(String input, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException
    {
        try {
            this.startDateTime = LocalDateTime.parse(input, dtf);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("invalid date time %s. Expected format %s", input, SyntaxKeyword.inputDateTimeFormat), input, 0, e);
        }
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    private void setEndDateTime(String input, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException
    {
        try {
            this.endDateTime = LocalDateTime.parse(input, dtf);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("invalid date time %s. Expected format %s", input, SyntaxKeyword.inputDateTimeFormat), input, 0, e);
        }
    }

    /**
     *
     * @param startDateTime start date time string. Leave it as a blank string if not setting it
     * @param endDateTime end date time string. Leave it as a blank string if not setting it
     * @param dtf date time formatter
     * @throws BigChungusException.IllogicalDateTimeException exception if start date time is after end date time
     */

    public void setDateTime(String startDateTime, String endDateTime, DateTimeFormatter dtf) throws BigChungusException.IllogicalDateTimeException {
        if(startDateTime == null){
            startDateTime = "";
        }
        if(endDateTime == null){
            endDateTime = "";
        }
        if(!startDateTime.isBlank()){
            this.setStartDateTime(startDateTime, dtf);
        }
        if(!endDateTime.isBlank()){
            this.setEndDateTime(endDateTime, dtf);
        }
        if (this.getStartDateTime().isAfter(this.getEndDateTime())) {
            throw new BigChungusException.IllogicalDateTimeException();
        }
    }
}
