package Storage;

import Task.Task;

import java.util.List;

public interface ILoadable {
    public List<Task> Load() throws Exception;
}
