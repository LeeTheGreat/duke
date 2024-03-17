package Task;

import java.util.Hashtable;

public abstract class Task {
    private boolean isDone;
    private String description;

    protected Task(Hashtable<String,String> fields) {
        if(fields.contains("done")){
            this.setDone(Boolean.parseBoolean(fields.get("done")));
        }
        this.setDescription(fields.get("desc"));
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString(){
        String[] info = {"task", this.getDescription(), String.valueOf(this.getDone())};
        return String.join(";;", info);
    }

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

}