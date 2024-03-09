package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import Syntax.SyntaxUtil;
import Exception.BigChungusException;
public class Deadline extends Task {

    private LocalDate endDate;
    private LocalTime endTime;
    private LocalDateTime endDateTime;
    public Deadline(Hashtable<String,String> fields) throws BigChungusException.InvalidTimeFormat, BigChungusException.InvalidDateFormat {
        super(fields);
        this.setEndDate(fields.get(SyntaxUtil.endDateKeyword));
        this.setEndTime(fields.get(SyntaxUtil.endTimeKeyword));
        //this.setEndDateTime(fields.get(SyntaxUtil.endDateTimeKeyword));
    }
    @Override
    public String print(){
        String info = String.format("end %s @ %s", this.getEndDate(), this.getEndTime());
        return String.format("[D]%s (%s)", super.print(), info);
    }

    @Override
    public String toString(){
        String[] info = {"event", super.getDescription(), String.valueOf(super.getDone()), String.valueOf(this.getEndDate()), String.valueOf(this.getEndTime())};
        return String.join(";;", info);
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

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = LocalDateTime.parse(endDateTime);
    }
}
