import java.util.List;

import Storage.FileStorage;
import Task.*;
import Action.*;

public class BigChungus {
    public static void main(String[] args) {
        try {
            UI ui = new UI();
            ActionManager actionManager = new ActionManager();
            List<Task> tasks;
            FileStorage storage = new FileStorage();
            tasks = storage.Load();
            ui.Start(storage, tasks, actionManager);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}