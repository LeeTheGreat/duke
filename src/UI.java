import Action.ActionManager;
import Syntax.SyntaxParser;
import Task.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class UI {
    public UI(){}

    public void Start() {
        try {
            Scanner in = new Scanner(System.in);
            List<Task> tasks = new ArrayList<>();
            while (true) {
                try {
                    String input = in.nextLine();
                    input = input.trim().toLowerCase();
                    Hashtable<String, String> fields = SyntaxParser.Parse(input);
                    ActionManager actionManager = new ActionManager();
                    actionManager.executeAction(fields, tasks);
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
