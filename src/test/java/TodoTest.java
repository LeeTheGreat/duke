package main.java.Action;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import main.java.Syntax.SyntaxKeyword;
import main.java.Task.*;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void createTodoIsDoneFalse(){
        Hashtable<String,String> fields = new Hashtable<>();
        ArrayList<Task> tasks = new ArrayList<>();
        String desc = "createTodoIsDoneFalse";
        String isDone = "false";
        fields.put(SyntaxKeyword.description, desc);
        fields.put(SyntaxKeyword.isDone, isDone);
        Todo todo = new Todo(fields);
        assertEquals(desc, todo.getDescription());
        assertFalse(todo.getDone());
    }

    @Test
    public void createTodoIsDoneTrue(){
        Hashtable<String,String> fields = new Hashtable<>();
        ArrayList<Task> tasks = new ArrayList<>();
        String desc = "createTodoIsDoneTrue";
        String isDone = "true";
        fields.put(SyntaxKeyword.description, desc);
        fields.put(SyntaxKeyword.isDone, isDone);
        Todo todo = new Todo(fields);
        assertEquals(desc, todo.getDescription());
        assertTrue(todo.getDone());
    }
}
