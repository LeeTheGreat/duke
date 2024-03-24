import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Hashtable;

import Storage.FileStorage;
import Syntax.*;
import Task.*;
import Action.*;

public class BigChungus {
    public static void main(String[] args) {
        String logo = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣧⠀⠀⠀⢰⡿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡟⡆⠀⠀⣿⡇⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⣿⠀⢰⣿⡇⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡄⢸⠀⢸⣿⡇⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡇⢸⡄⠸⣿⡇⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⢸⡅⠀⣿⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣥⣾⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⡿⡿⣿⣿⡿⡅⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠉⠀⠉⡙⢔⠛⣟⢋⠦⢵⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣄⠀⠀⠁⣿⣯⡥⠃⠀⢳⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡇⠀⠀⠀⠐⠠⠊⢀⠀⢸⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⡿⠀⠀⠀⠀⠀⠈⠁⠀⠀⠘⣿⣄⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣷⡀⠀⠀⠀
                ⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣧⠀⠀
                ⠀⠀⠀⡜⣭⠤⢍⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢛⢭⣗⠀
                ⠀⠀⠀⠁⠈⠀⠀⣀⠝⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠠⠀⠀⠰⡅
                ⠀⠀⠀⢀⠀⠀⡀⠡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠔⠠⡕⠀
                ⠀⠀⠀⠀⣿⣷⣶⠒⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀
                ⠀⠀⠀⠀⠘⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠈⢿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠊⠉⢆⠀⠀⠀⠀
                ⠀⢀⠤⠀⠀⢤⣤⣽⣿⣿⣦⣀⢀⡠⢤⡤⠄⠀⠒⠀⠁⠀⠀⠀⢘⠔⠀⠀⠀⠀
                ⠀⠀⠀⡐⠈⠁⠈⠛⣛⠿⠟⠑⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠉⠑⠒⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
        //System.out.println(logo);
        System.out.println("Hello. I'm BigChungus");
        try {
            Scanner in = new Scanner(System.in);
            FileStorage storage = new FileStorage();
            List<Task> tasks = new ArrayList<>();
            tasks = storage.Load();
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
                        ActionManager actionManager = new ActionManager();
                        actionManager.executeAction(fields, tasks);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}