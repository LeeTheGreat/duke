import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import Task.*;
import Exception.BigChungusException;

public class Storage {
    public Storage(){  }

    private String getSaveFilePath(){
        String folder = System.getProperty("user.dir") + File.separator + "data";
        return (folder + File.separator + "bigchungus.txt");
    }

    public void Save(List<Task> tasks) throws IOException {
        File f = new File(getSaveFilePath());
        f.getParentFile().mkdirs();

        //Gson gson = new Gson();

        FileWriter writer = new FileWriter(getSaveFilePath());
        for(Task task : tasks) {
            //String taskAttrib = gson.toJson(task);
            JSONObject taskJson = new JSONObject();
            String taskType = "";
            taskJson.put("isDone", task.getDone());
            taskJson.put("description", task.getDescription());
            if(task instanceof Todo){
                taskJson.put("type", "todo");
            }
            else if (task instanceof Deadline){
                taskJson.put("type", "deadline");
                taskJson.put("endDateTime", ((Deadline) task).getEndDateTime());
                taskJson.put("endTime", ((Deadline) task).getEndTime());
            }
            else if(task instanceof Event){
                taskType = "event";
                taskJson.put("startDate", ((Event) task).getEndDate());
                taskJson.put("startTime", ((Event) task).getEndTime());
                taskJson.put("endDate", ((Event) task).getEndDate());
                taskJson.put("endTime", ((Event) task).getEndTime());
            }
            writer.append(String.format("%s%n", taskJson.toString()));
        }
        writer.close();
        System.out.printf("%s: %s", "saved to", getSaveFilePath());
        writer.close();
    }

    protected void addEventKeyValue(Event evt, JSONObject obj){

    }
    public List<Task> Load() throws IOException, BigChungusException.JsonTypeKeyNotATaskClass, BigChungusException.InvalidTimeFormat, BigChungusException.InvalidDateFormat, BigChungusException.InvalidDateTimeFormat {
        List<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(new File(getSaveFilePath()));
        //Gson gson = new Gson();
        while (scanner.hasNextLine()) {
            String jsonString = scanner.nextLine();
            JSONObject jo = new JSONObject(jsonString);
            String taskType = jo.getString("type");
            Hashtable<String,String> fields = new Hashtable<>();
            fields.put("description", jo.getString("description"));
            fields.put("isDone", jo.getString("isDone"));
            if(taskType.equals("todo")){
                tasks.add(new Todo(fields));
                //gson.fromJson(attrib, Todo.class);
            }
            else if(taskType.equals("deadline")){
                fields.put("endDate", jo.getString("endDate"));
                fields.put("endTime", jo.getString("endTime"));
                tasks.add(new Deadline(fields));
                //t = gson.fromJson(attrib, Deadline.class);
            }
            else if(taskType.equals("event")){
                fields.put("startDate", jo.getString("startDate"));
                fields.put("startTime", jo.getString("startTime"));
                fields.put("endDate", jo.getString("endDate"));
                fields.put("endTime", jo.getString("endTime"));
                tasks.add(new Event(fields));
                //t = gson.fromJson(attrib, Event.class);
            }
            else{
                throw new BigChungusException.JsonTypeKeyNotATaskClass(taskType);
            }
        }
        scanner.close();
        return tasks;
    }


}
