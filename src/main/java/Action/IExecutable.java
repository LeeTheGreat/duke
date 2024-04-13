package main.java.Action;

import java.util.Hashtable;
import java.util.List;
import main.java.Task.*;

public interface IExecutable {
    public abstract void execute(Hashtable<String, String> fields, List<Task> tasks) throws Exception;
}
