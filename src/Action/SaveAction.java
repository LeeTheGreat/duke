package Action;

import Task.Task;
import Storage.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class SaveAction implements IExecutable{
    public SaveAction(){}


    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws IOException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.Save(tasks);
    }
}
