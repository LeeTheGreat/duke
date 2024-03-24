package Storage;

import Syntax.SyntaxUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Task.*;
import CustomException.BigChungusException;

public class FileStorage implements IStorable {
    
    public String fileName = "bigchungus.txt";
    public String folder = System.getProperty("user.dir") + File.separator + "data";
    public String fullPath = this.folder + File.separator + this.fileName;
    public FileStorage(){  }

    public void Save(List<Task> tasks) throws IOException {
        File f = new File(fullPath);
        try{
            boolean dirCreated = f.getParentFile().mkdirs();
        }
        catch (SecurityException e) {
            System.out.printf("failed to create directory %s%n", folder);
        }

        FileWriter writer = new FileWriter(this.fullPath);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxUtil.dateTimeFormat);
        for(Task task : tasks) {
            JSONObject taskJson = new JSONObject();
            taskJson.put(SyntaxUtil.isDone, String.valueOf(task.getDone()));
            taskJson.put(SyntaxUtil.description, task.getDescription());
            if(task instanceof Todo){
                taskJson.put("type", "todo");
            }
            else if (task instanceof Deadline){
                taskJson.put("type", "deadline");
                taskJson.put(SyntaxUtil.endDateTimeKeyword, ((Deadline) task).getEndDateTime().format(dtf));
            }
            else if(task instanceof Event){
                taskJson.put("type", "event");
                taskJson.put(SyntaxUtil.startDateTimeKeyword, ((Event) task).getStartDateTime().format(dtf));
                taskJson.put(SyntaxUtil.endDateTimeKeyword, ((Event) task).getEndDateTime().format(dtf));
            }
            writer.append(String.format("%s%n", taskJson.toString()));
        }
        writer.close();
        System.out.printf("saved tasks to: %s%n", this.fullPath);
        writer.close();
    }

    public List<Task> Load() throws
            IOException
            , BigChungusException.JsonTypeKeyNotATaskClass
            , BigChungusException.InvalidDateTimeFormatException
            , BigChungusException.StartDateTimeAfterEndDateTimeException
            , BigChungusException.EndDateTimeBeforeStartDateTimeException
    {
        List<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(new File(this.fullPath));
        while (scanner.hasNextLine()) {
            String jsonString = scanner.nextLine();
            JSONObject jo = new JSONObject(jsonString);
            String taskType = jo.getString("type");
            Hashtable<String,String> fields = new Hashtable<>();
            fields.put(SyntaxUtil.description, jo.getString(SyntaxUtil.description));
            fields.put(SyntaxUtil.isDone, jo.getString(SyntaxUtil.isDone));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxUtil.dateTimeFormat);
            if(taskType.equals("todo")){
                tasks.add(new Todo(fields));
                //gson.fromJson(attrib, Todo.class);
            }
            else if(taskType.equals("deadline")){
                fields.put(SyntaxUtil.endDateTimeKeyword, jo.getString(SyntaxUtil.endDateTimeKeyword));
                tasks.add(new Deadline(fields, dtf));
                //t = gson.fromJson(attrib, Deadline.class);
            }
            else if(taskType.equals("event")){
                fields.put(SyntaxUtil.startDateTimeKeyword, jo.getString(SyntaxUtil.startDateTimeKeyword));
                fields.put(SyntaxUtil.endDateTimeKeyword, jo.getString(SyntaxUtil.endDateTimeKeyword));
                tasks.add(new Event(fields, dtf));
            }
            else{
                throw new BigChungusException.JsonTypeKeyNotATaskClass(taskType);
            }
        }
        scanner.close();
        System.out.printf("loaded tasks from: %s%n", this.fullPath);
        return tasks;
    }


}
