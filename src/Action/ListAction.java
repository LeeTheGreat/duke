package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;

public class ListAction implements IExecutable {

    public ListAction(){

    }
    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks){
        System.out.println("listing tasks");
        int taskNum = 0;
        for(Task t : tasks){ // if i wish to test this, i'll need BigChungus.tasks which will require me to initialize the entire big chungus. not good.
            taskNum++;
            System.out.printf("%d. %s%n", taskNum, t.print());
        }
    }
}
