package main.java.Task;

import java.util.Hashtable;

public class Todo extends Task {
    public Todo(Hashtable<String,String> fields){
        super(fields);
    }

    @Override
    public String toString(){
        String[] info = {this.getClass().getName(), super.getDescription(), String.valueOf(super.getDone())};
        return String.join(";;", info);
    }
    @Override
    public String print() {
        return String.format("[T]%s", super.print());
    }

    @Override
    protected String toStringForCompare() {
        String[] info = {this.getClass().getName(), super.getDescription()};
        return String.join(";;", info);
    }

    public int hashCode() {
        return this.toStringForCompare().hashCode();
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Todo) {
            return this.toStringForCompare().equals(((Todo) obj).toStringForCompare());
        }
        return false;
    }
}
