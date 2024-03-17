package Storage;

import Task.Task;

import java.util.List;

public interface ISavable {
    public abstract void Save(List<Task> tasks) throws Exception;
}
