package main.java;

import main.java.Action.ActionManager;
import main.java.Storage.IStorable;
import main.java.Syntax.SyntaxParser;
import main.java.Task.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * A class that is responsible for handling the user input and executing the required code
 */
public class UI {

    public UI(){}

    /**
     *
     * @param storage A storage class that implements IStorable
     * @param tasks list of tasks
     * @param actionManager ActionManager to create the action object to handle the input
     */

    public void Start(IStorable storage, List<Task> tasks, ActionManager actionManager) {
        try {
            System.out.println("Hello. I'm BigChungus, your task bot");
            Scanner in = new Scanner(System.in);
            while (true) {
                try {
                    String input = in.nextLine();
                    input = input.trim().toLowerCase();
                    if (input.isEmpty()) {
                        continue;
                    }
                    else if (input.equals("bye")) {
                        System.out.println("gooda bye");
                        break;
                    }
                    else {
                        Hashtable<String, String> fields = SyntaxParser.Parse(input);
                        actionManager.executeAction(fields, tasks);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
