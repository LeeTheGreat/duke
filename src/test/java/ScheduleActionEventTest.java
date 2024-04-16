import main.java.Action.EventAction;
import main.java.Action.RescheduleAction;
import main.java.CustomException.BigChungusException;
import main.java.Syntax.SyntaxKeyword;
import main.java.Syntax.SyntaxParser;
import main.java.Task.Event;
import main.java.Task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ScheduleActionEventTest {

    private final String oldSdt = "06-06-2024 1122";
    private final String oldEdt = "07-07-2024 1234";
    private void createTasks(List<Task> tasks) throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        String command = String.format("event e1_test /sdt %s /edt %s", oldSdt, oldEdt);
        Hashtable<String,String> fields = SyntaxParser.parse(command);
        EventAction act = new EventAction();
        act.execute(fields, tasks);
        assertInstanceOf(Event.class, tasks.get(0));
    }
    @Test
    public void scheduleLogicalDateTime() throws Exception{
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String sdt = "01-02-2024 1234";
        String edt = "03-02-2024 1234";

        String command = String.format("reschedule 1 /sdt %s /edt %s", sdt, edt);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        RescheduleAction act = new RescheduleAction();
        act.execute(fields, tasks);
        Event e = (Event)tasks.get(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        LocalDateTime correctSdt = LocalDateTime.parse(sdt, dtf);
        LocalDateTime correctEdt = LocalDateTime.parse(edt, dtf);
        assert(e.getStartDateTime().equals(correctSdt));
        assert(e.getEndDateTime().equals(correctEdt));
    }

    @Test
    public void scheduleLogicalDateTimeNewSdt() throws Exception{
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String sdt = "01-02-2024 1234";
        String command = String.format("reschedule 1 /sdt %s", sdt);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        RescheduleAction act = new RescheduleAction();
        act.execute(fields, tasks);
        Event e = (Event)tasks.get(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        LocalDateTime correctSdt = LocalDateTime.parse(sdt, dtf);
        LocalDateTime correctEdt = LocalDateTime.parse(oldEdt, dtf);
        assert(e.getStartDateTime().equals(correctSdt));
        assert(e.getEndDateTime().equals(correctEdt));
    }
    @Test
    public void scheduleLogicalDateTimeNewEdt() throws Exception{
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String edt = "01-07-2024 1234";
        String command = String.format("reschedule 1 /edt %s", edt);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        RescheduleAction act = new RescheduleAction();
        act.execute(fields, tasks);
        Event e = (Event)tasks.get(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        LocalDateTime correctSdt = LocalDateTime.parse(oldSdt, dtf);
        LocalDateTime correctEdt = LocalDateTime.parse(edt, dtf);
        assert(e.getStartDateTime().equals(correctSdt));
        assert(e.getEndDateTime().equals(correctEdt));
    }

    @Test
    public void scheduleIllogicalDateTimeNewSdtAfterNewEdt() throws Exception {
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String sdt = "01-02-2024 1234";
        String edt = "01-01-2024 1234";
        String command = String.format("reschedule 1 /sdt %s /edt %s", sdt, edt);
        System.out.println(command);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        try {
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
        catch (Exception e){
            assertInstanceOf(BigChungusException.IllogicalDateTimeException.class, e);
        }
    }

    @Test
    public void scheduleIllogicalDateTimeNewSdtEqualNewEdt() throws Exception {
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String sdt = "01-02-2024 1234";
        String command = String.format("reschedule 1 /sdt %s /edt %s", sdt, sdt);
        System.out.println(command);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        try {
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
        catch (Exception e){
            assertInstanceOf(BigChungusException.IllogicalDateTimeException.class, e);
        }
    }

    @Test
    public void scheduleIllogicalDateTimeNewSdtAfterOldEdt() throws Exception {
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String sdt = "07-08-2024 1234";
        String command = String.format("reschedule 1 /sdt %s", sdt);
        System.out.println(command);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        try {
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
        catch (Exception e){
            assertInstanceOf(BigChungusException.IllogicalDateTimeException.class, e);
        }
    }

    @Test
    public void scheduleIllogicalDateTimeOldSdtAfterNewEdt() throws Exception {
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String edt = "07-05-2024 1234";
        String command = String.format("reschedule 1 /edt %s", edt);
        System.out.println(command);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        try {
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
        catch (Exception e){
            assertInstanceOf(BigChungusException.IllogicalDateTimeException.class, e);
        }
    }

    @Test
    public void invalidIndex() throws Exception {
        List<Task> tasks = new ArrayList<>();
        createTasks(tasks);
        String edt = "07-05-2024 1234";
        String command = String.format("reschedule 2 /edt %s", edt);
        System.out.println(command);
        Hashtable<String, String> fields = SyntaxParser.parse(command);
        try {
            RescheduleAction act = new RescheduleAction();
            act.execute(fields, tasks);
        }
        catch (Exception e){
            assertInstanceOf(BigChungusException.InvalidTaskIndexException.class, e);
        }
    }
}
