import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;

import main.java.Syntax.SyntaxKeyword;
import main.java.Task.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void createDeadlineIsDoneFalse() {
        Hashtable<String,String> fields = new Hashtable<>();
        ArrayList<Task> tasks = new ArrayList<>();
        String desc = "createDeadlineIsDoneFalse";
        String isDone = "false";
        String dateTime = "01-06-2024 1122";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        fields.put(SyntaxKeyword.description, desc);
        fields.put(SyntaxKeyword.isDone, isDone);
        fields.put(SyntaxKeyword.endDateTimeKeyword, dateTime);
        Deadline task = new Deadline(fields, dtf);
        assertEquals(desc, task.getDescription());
        assertFalse(task.getDone());
        assertEquals(LocalDateTime.parse(dateTime, dtf), task.getEndDateTime());
    }

    @Test
    public void createDeadlineIsDoneTrue() {
        Hashtable<String,String> fields = new Hashtable<>();
        ArrayList<Task> tasks = new ArrayList<>();
        String desc = "createDeadlineIsDoneFalse";
        String isDone = "true";
        String dateTime = "01-06-2024 1122";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        fields.put(SyntaxKeyword.description, desc);
        fields.put(SyntaxKeyword.isDone, isDone);
        fields.put(SyntaxKeyword.endDateTimeKeyword, dateTime);
        Deadline task = new Deadline(fields, dtf);
        assertEquals(desc, task.getDescription());
        assertTrue(task.getDone());
        assertEquals(LocalDateTime.parse(dateTime, dtf), task.getEndDateTime());
    }

    @Test
    public void createDeadlineInvalidDateTime() {
        Hashtable<String,String> fields = new Hashtable<>();
        ArrayList<Task> tasks = new ArrayList<>();
        String desc = "createDeadlineIsDoneFalse";
        String isDone = "true";
        String dateTime = "01-06-2024 1177";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        fields.put(SyntaxKeyword.description, desc);
        fields.put(SyntaxKeyword.isDone, isDone);
        fields.put(SyntaxKeyword.endDateTimeKeyword, dateTime);
        try {
            Deadline task = new Deadline(fields, dtf);
        }
        catch (Exception e){
            assertInstanceOf(DateTimeParseException.class, e);
        }
    }
}