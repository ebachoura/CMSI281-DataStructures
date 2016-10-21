package classwork5;

// TODO: Choose imports based on DS choice
import java.util.Scanner;
import java.util.LinkedList;

public class WebNavigator {

    // Fields
    private String current; // Tracks currently visited site
    private int currentIndex; // Tracks where we are in history
    private LinkedList<String> history; //Tracks our history specifically
    
    // Constructor
    WebNavigator () {
        current = null;
        currentIndex = -1;
        history = new LinkedList<String>();
    }
    
    // Methods
    // [!] YOU DO NOT HAVE TO MODIFY THIS METHOD FOR YOUR SOLUTION
    public boolean getNextUserCommand (Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");
        
        // Switch on the command (issued first in input line)
        switch(parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forw":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }
        
        System.out.println("Currently Visiting: " + current);
        return true;
    }
    
    public void visit (String site) {
    	if (currentIndex + 1 < history.size()) {
        	int i = currentIndex + 1;
        	while (i != history.size()) {
        		history.removeLast();
        	}
        }
    	current = site;
        currentIndex++;
        history.add(site);
    }
    
    public void back () {
        if (currentIndex <= 0) {return;}
    	currentIndex--;
        current = history.get(currentIndex);
    }
    
    public void forw () {
    	if (currentIndex < 0 || currentIndex + 1 == history.size()) {return;}
    	currentIndex++;
        current = history.get(currentIndex);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();
        
        System.out.println("Welcome to ForneyFox, enter a command from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {}
        System.out.println("Goodbye!");
    }
}