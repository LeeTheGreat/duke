package Storage;

import Task.Task;

import java.util.List;

public interface IStorable {
    public List<Task> Load() throws Exception;
    public abstract void Save(List<Task> tasks) throws Exception;
}
