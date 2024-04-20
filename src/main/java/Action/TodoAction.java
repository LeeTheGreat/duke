package main.java.Action;

import java.util.Hashtable;
import java.util.List;
import main.java.Task.*;

public class TodoAction implements IExecutable {

    public TodoAction(){
    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks){
        Todo task = new Todo(fields);
        if(tasks.contains(task)){
            throw new IllegalArgumentException("unable to add todo. Todo already exists");
        }
        tasks.add(task);
        System.out.printf("added Todo: %s%n", task.print());
    }
}
