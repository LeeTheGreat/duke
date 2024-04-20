package main.java.Task;

import main.java.Syntax.SyntaxKeyword;

import java.util.Hashtable;

public abstract class Task {
    private boolean isDone;
    private String description;

    protected Task(Hashtable<String,String> fields) {
        if(fields.containsKey(SyntaxKeyword.isDone)){
            this.setDone(Boolean.parseBoolean(fields.get(SyntaxKeyword.isDone)));
        }
        this.setDescription(fields.get(SyntaxKeyword.description));
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public abstract String toString();

    public String print() {
        String done = " ";
        if (this.getDone()) {
            done = "X";
        }
        return String.format("[%s] %s", done, this.getDescription());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * this function is for comparison functions such as hashCode() or equals(). It ignores the isDone attribute.
     * @return String of the class name and attributes except isDone.
     */
    protected abstract String toStringForCompare();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}