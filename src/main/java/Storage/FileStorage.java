/**
 *
 */
package main.java.Storage;

import main.java.Syntax.SyntaxKeyword;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import main.java.Task.*;
import main.java.CustomException.BigChungusException;

/**
 * FileStorage is a class to provide saving and loading of the tasks from and to a text file in JSON format
 */
public class FileStorage implements IStorable {
    
    public String fileName = "bigchungus.txt";
    public String folder = System.getProperty("user.dir") + File.separator + "data";
    public String fullPath = this.folder + File.separator + this.fileName;
    public FileStorage(){  }

    /**
     * Save the tasks to the file in the data folder of the program directory
     * @param tasks list of tasks
     * @throws IOException
     */
    public void Save(List<Task> tasks) throws IOException {
        File f = new File(fullPath);
        try{
            boolean dirCreated = f.getParentFile().mkdirs();
        }
        catch (SecurityException e) {
            System.out.printf("failed to create directory %s%n", folder);
        }

        FileWriter writer = new FileWriter(this.fullPath);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
        for(Task task : tasks) {
            JSONObject taskJson = new JSONObject();
            taskJson.put(SyntaxKeyword.isDone, String.valueOf(task.getDone()));
            taskJson.put(SyntaxKeyword.description, task.getDescription());
            if(task instanceof Todo){
                taskJson.put("type", "todo");
            }
            else if (task instanceof Deadline){
                taskJson.put("type", "deadline");
                taskJson.put(SyntaxKeyword.endDateTimeKeyword, ((Deadline) task).getEndDateTime().format(dtf));
            }
            else if(task instanceof Event){
                taskJson.put("type", "event");
                taskJson.put(SyntaxKeyword.startDateTimeKeyword, ((Event) task).getStartDateTime().format(dtf));
                taskJson.put(SyntaxKeyword.endDateTimeKeyword, ((Event) task).getEndDateTime().format(dtf));
            }
            writer.append(String.format("%s%n", taskJson.toString()));
        }
        writer.close();
        System.out.printf("saved tasks to: %s%n", this.fullPath);
        writer.close();
    }

    /**
     * Load the saved tasks from the file in the data folder of the program directory
     * @return List<main.java.Task></main.java.Task>
     * @throws IOException for reading from the save file
     * @throws BigChungusException.JsonTypeKeyNotATaskClass for loading a JSON "type" key that is not a task class or its subclass
     * @throws BigChungusException.InvalidDateTimeFormatException for date time that doesn't follow the stated format
     * @throws BigChungusException.StartDateTimeAfterEndDateTimeException self-explanatory in the exception name
     * @throws BigChungusException.EndDateTimeBeforeStartDateTimeException self-explanatory in the exception name
     */
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
            fields.put(SyntaxKeyword.description, jo.getString(SyntaxKeyword.description));
            fields.put(SyntaxKeyword.isDone, jo.getString(SyntaxKeyword.isDone));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SyntaxKeyword.inputDateTimeFormat);
            if(taskType.equals("todo")){
                tasks.add(new Todo(fields));
                //gson.fromJson(attrib, Todo.class);
            }
            else if(taskType.equals("deadline")){
                fields.put(SyntaxKeyword.endDateTimeKeyword, jo.getString(SyntaxKeyword.endDateTimeKeyword));
                tasks.add(new Deadline(fields, dtf));
                //t = gson.fromJson(attrib, Deadline.class);
            }
            else if(taskType.equals("event")){
                fields.put(SyntaxKeyword.startDateTimeKeyword, jo.getString(SyntaxKeyword.startDateTimeKeyword));
                fields.put(SyntaxKeyword.endDateTimeKeyword, jo.getString(SyntaxKeyword.endDateTimeKeyword));
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
