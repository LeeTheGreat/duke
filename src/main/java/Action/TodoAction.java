package main.java.Action;

import java.util.Hashtable;
import java.util.List;
import main.java.Task.*;

public class TodoAction implements IExecutable {

    public TodoAction(){
    }
    public void execute(Hashtable<String, String> fields, List<Task> tasks){
        Todo todo = new Todo(fields);
        tasks.add(todo);
        System.out.printf("added Todo: %s%n", todo.getDescription());
    }
}
