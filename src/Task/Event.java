package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import Exception.BigChungusException;
import Syntax.SyntaxUtil;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    public Event(Hashtable<String,String> fields) throws BigChungusException.InvalidDateFormat, BigChungusException.InvalidTimeFormat {
        super(fields);
        this.setStartDate(fields.get(SyntaxUtil.startDateKeyword));
        this.setStartTime(fields.get(SyntaxUtil.startTimeKeyword));
        this.setEndDate(fields.get(SyntaxUtil.endDateKeyword));
        this.setEndTime(fields.get(SyntaxUtil.endTimeKeyword));
        //this.setEndDateTime(fields.get(SyntaxUtil.endDateTimeKeyword));
    }

    @Override
    public String print(){
        String info = String.format("start %s @ %s, end %s @ %s", getStartDate(), getStartTime(), getEndDate(), getEndTime());
        return String.format("[E]%s (%s)", super.print(), info);
    }

    @Override
    public String toString(){
        String[] info = {"event", super.getDescription(), String.valueOf(super.getDone()), String.valueOf(this.getStartDate()), String.valueOf(this.getStartTime()), String.valueOf(this.getEndDate()), String.valueOf(this.getEndTime())};
        return String.join(";;", info);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(String input) throws BigChungusException.InvalidDateFormat {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.dateFormat);
            this.startDate = LocalDate.parse(input, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidDateFormat(input);
        }
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String input) throws BigChungusException.InvalidTimeFormat {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.timeFormat);
            this.startTime = LocalTime.parse(input, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidTimeFormat(input);
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String input) throws BigChungusException.InvalidDateFormat {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.dateFormat);
            this.endDate = LocalDate.parse(input, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidDateFormat(input);
        }
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(String input) throws BigChungusException.InvalidTimeFormat {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.timeFormat);
            this.endTime = LocalTime.parse(input, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidTimeFormat(input);
        }
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String input) throws BigChungusException.InvalidDateTimeFormat {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.dateTimeFormat);
            this.startDateTime = LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidDateTimeFormat(input);
        }
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String input) throws BigChungusException.InvalidDateTimeFormat {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SyntaxUtil.dateTimeFormat);
            this.endDateTime = LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BigChungusException.InvalidDateTimeFormat(input);
        }
    }
}
