package Action;

import java.util.Hashtable;
import java.util.List;
import Task.*;

public interface IExecutable {
    //private Hashtable<String,String> fields;
    public abstract void execute(Hashtable<String, String> fields, List<Task> tasks) throws Exception;

    /*
    public Hashtable<String, String> getFields() {
        return fields;
    }
    */

    /*
    public void setFields(Hashtable<String, String> fields) {
        this.fields = fields;
    }
    */
}
