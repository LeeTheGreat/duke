package main.java.Storage;

import main.java.Task.Task;

import java.util.List;

public interface IStorable {
    public List<Task> Load() throws Exception;
    public abstract void Save(List<Task> tasks) throws Exception;
}
