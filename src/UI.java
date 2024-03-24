import Action.ActionManager;
import Storage.IStorable;
import Syntax.SyntaxParser;
import Task.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class UI {
    public UI(){}

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
