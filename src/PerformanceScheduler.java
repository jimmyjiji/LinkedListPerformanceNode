/**
 * Created by Jimmy on 2/23/2015 in PACKAGE_NAME
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class PerformanceScheduler {
    private static PerformanceList performanceList;
    private static Scanner input;

    public static void main(String[] args) throws EmptyListException {

        performanceList = new PerformanceList();

       boolean error = true;
        do {
            try {
                displayMenu(performanceList);
                error = false;
            } catch (EmptyListException ex) {
                System.out.println("The list is empty");
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input");
            }
        } while (error);


        /*
        PerformanceNode node = new PerformanceNode(); // Bob
        PerformanceNode node1 = new PerformanceNode("Joe's Performance", "Joe", 10, 25);
        PerformanceNode node2 = new PerformanceNode("Tom's Performance", "Tom", 20, 60);
        PerformanceNode node3 = new PerformanceNode("Sam's Performance", "Sam", 30, 15);
        performanceList.addToEnd(node); //creates Bob in the beginning 1
        performanceList.addAfterCurrent(node1);//Adds Joe after Bob. Cursor is still at one 3
        performanceList.addAfterCurrent(node2);//Adds Tom before Joe 2
        performanceList.addToEnd(node3); // Adds Sam 4
        display(performanceList);
        */

    }

    public static void displayMenu(PerformanceList performanceList) throws EmptyListException {
        input = new Scanner(System.in);
        menu();
        String choice = input.next().toLowerCase();
        if (choice.length() > 1)
            throw new InputMismatchException();
        char converted = choice.charAt(0);

        do {
            switch (converted) {
                case 'a':
                    add(performanceList);
                    break;
                case 'i':
                    insert(performanceList);
                    break;
                case 'r':
                    remove(performanceList);
                    break;
                case 'c':
                    display(performanceList);
                    break;
                case 'd':
                    displayAll(performanceList);
                    break;
                case 'f':
                    moveForward(performanceList);
                    break;
                case 'b':
                    moveBackward(performanceList);
                    break;
                case 'j':
                    jumpTo(performanceList);
                    break;
                case 'q':
                    System.out.println("Program terminating normally");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Not a valid operation. Try again");
            }
            System.out.println("\n");
            menu();
            String s = input.next().toLowerCase();
            if (s.length() > 1)
                throw new InputMismatchException();
            converted = s.charAt(0);
        } while (converted != 'q');

    }
    public static void menu() {
        System.out.println();
        System.out.print("A) Add to end\n" +
                "I) Insert a new performance after this node\n" +
                "R) Remove current node\n" +
                "C) Display current nodes\n" +
                "D) Display all nodes\n" +
                "F) Move cursor forward\n" +
                "B) Move cursor backward\n" +
                "J) Jump to a given position\n" +
                "Q) Quit\n\nChoose an operation: ");
    }
    public static void add(PerformanceList list) throws InputMismatchException {
        input = new Scanner(System.in);
        System.out.print("Enter the name of the performance:");
        String performanceName = input.nextLine();
        System.out.print("Enter the name of the performer:");
        String performerName = input.nextLine();
        System.out.print("Enter the total amount of participants:");
        int participants = input.nextInt();
        if(participants < 0)
            throw new InputMismatchException();
        System.out.print("Enter the duration of the performance:");
        int duration = input.nextInt();
        if(duration < 0)
            throw new InputMismatchException();
        PerformanceNode newNode = new PerformanceNode(performanceName, performerName, participants,duration);
        list.addToEnd(newNode);

        System.out.println(newNode.getNameOfPerformance() +" has been added to the end of the list");
    }

    public static void insert(PerformanceList list) throws InputMismatchException {
        input = new Scanner(System.in);
        System.out.print("Enter the name of the performance:");
        String performanceName = input.nextLine();
        System.out.print("Enter the name of the performer:");
        String performerName = input.nextLine();
        System.out.print("Enter the total amount of participants:");
        int participants = input.nextInt();
        if(participants < 0)
            throw new InputMismatchException();
        System.out.print("Enter the duration of the performance:");
        int duration = input.nextInt();
        if(duration < 0)
            throw new InputMismatchException();

        PerformanceNode newNode = new PerformanceNode(performanceName, performerName, participants,duration);
        list.addAfterCurrent(newNode);

        System.out.println(newNode.getNameOfPerformance() +" has been added after the cursor");


    }

    public static void remove(PerformanceList list) throws EmptyListException {
        if(list.getCursor()!= null) {
            list.removeCurrentNode();
        } else
            throw new EmptyListException("List is empty");


        System.out.println("Current node has been removed");
    }

    public static void display(PerformanceList list) throws EmptyListException{
        if(list.getCursor()!= null) {
            System.out.printf("           %10s%25s%20s%10s%15s" , "Performance Name", "Lead Performer Name", "Participants", "Duration", "Start Time");
            System.out.println();
            System.out.print("\t\t  ");
            for(int i = 0; i < 90; i++)
                System.out.print("-");
            System.out.println();
            list.displayCurrentPerformance();
        }
        else
            throw new EmptyListException("List is empty");
    }

    public static void displayAll(PerformanceList list) throws EmptyListException {
        if(list.getCursor()!= null)
            System.out.println(list.toString());
        else
            throw new EmptyListException("List is empty");
    }

    public static void moveForward(PerformanceList list) {
        if(list.getCursor().getNext()!=null) {
            list.moveCursorForward();
            System.out.println("Cursor has been moved forward");
        } else {
            System.out.println("The cursor is already at the end of the list");
        }
    }

    public static void moveBackward(PerformanceList list) {
        if(list.getCursor().getPrev()!=null) {
            list.moveCursorBackward();
            System.out.println("Cursor has been moved backward");
        } else {
            System.out.println("The cursor is already at the head of the list");
        }
    }

    public static void jumpTo(PerformanceList list) {
        input = new Scanner(System.in);
        System.out.print("Enter a position to jump to:");
        int position = input.nextInt();
        if(position > 0 && position <= list.listLength()) {
            list.jumpToPosition(position);
            System.out.println("Cursor has moved to " + position);
        } else {
            System.out.println("That position is invalid");
        }
    }
}
