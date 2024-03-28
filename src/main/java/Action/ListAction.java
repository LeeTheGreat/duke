package main.java.Action;

import java.util.Hashtable;
import java.util.List;
import main.java.Task.*;

public class ListAction implements IExecutable {

    public ListAction(){

    }
    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks){
        System.out.println("listing tasks");
        int taskNum = 0;
        for(Task t : tasks){
            taskNum++;
            System.out.printf("%d. %s%n", taskNum, t.print());
        }
    }
}
