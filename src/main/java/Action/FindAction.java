package main.java.Action;
import main.java.Syntax.SyntaxKeyword;
import main.java.Task.Task;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FindAction implements IExecutable{

    public FindAction(){}
    @Override
    public void execute(Hashtable<String, String> fields, List<Task> tasks) throws Exception {
        List<Integer> matchedIndex = new ArrayList<>();
        String terms = fields.get(SyntaxKeyword.find);
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(terms)) {
                matchedIndex.add(i);
            }
        }
        for(Integer i : matchedIndex){
            System.out.printf("%d. %s%n", i+1, tasks.get(i).print());
        }
    }
}
