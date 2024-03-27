package main.java.Task;

import java.util.Hashtable;

public class Todo extends Task {
    public Todo(Hashtable<String,String> fields){
        super(fields);
    }

    @Override
    public String toString(){
        String[] info = {"todo", super.getDescription(), String.valueOf(super.getDone())};
        return String.join(";;", info);
    }
    @Override
    public String print() {
        return String.format("[T]%s", super.print());
    }
}
