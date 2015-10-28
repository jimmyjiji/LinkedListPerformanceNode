
/**
 * Created by jimmy on 2/17/2015.
 */
public class PerformanceList {

    private PerformanceNode head;
    private PerformanceNode tail;
    private PerformanceNode cursor;

    public PerformanceList() {
    }

    public PerformanceNode getCursor() {
        return this.cursor;
    }



    public void addToEnd(PerformanceNode newPerformance) {
        if(head == null) {
            head = newPerformance;
            tail = newPerformance;
            cursor = newPerformance;
        } else {
            jumpToPosition(listLength());
            cursor.setNext(newPerformance);
            newPerformance.setPrev(cursor);
            tail = newPerformance;
            cursor = tail;

        }
    }

    public int listLength() {
        PerformanceNode loop;
        int count = 0;
        for (loop = head; loop != null; loop = loop.getNext()) {
            count++;
        }
        return count;
    }
    public void addAfterCurrent(PerformanceNode newPerformance) {
        if (head == null) {
            addToEnd(newPerformance);
        } else {
            if (cursor.getNext()!= null) {
                PerformanceNode temp = cursor.getNext();
                cursor.setNext(newPerformance);
                newPerformance.setPrev(cursor);
                newPerformance.setNext(temp);
                temp.setPrev(newPerformance);
                moveCursorForward();
            } else {
                cursor.setNext(newPerformance);
                newPerformance.setPrev(cursor);
                moveCursorForward();
            }
        }
    }

    public boolean removeCurrentNode() {

        PerformanceNode next = cursor.getNext();
        PerformanceNode prev = cursor.getPrev();

        if (next!=null && prev != null) {
            prev.setNext(next);
            next.setPrev(prev);
            moveCursorForward();
            return true;
        }else if (next == null && prev == null) {
            head = null;
            return true;
        } else if (prev == null && next != null) {
            moveCursorForward();
            next.setPrev(null);
            head = cursor;
            return true;
        } else if (next == null && prev != null) {
            moveCursorBackward();
            prev.setNext(null);
            tail = cursor;
            return true;
        } else if (cursor == null) {
            System.out.println("There is nothing to remove");
            return false;
        }
            return false;
    }

    public void displayCurrentPerformance() {
        System.out.println(cursor.toString());
    }

    public boolean moveCursorForward() {
        if(cursor.getNext() != null) {
            cursor = cursor.getNext();
            return true;
        } else
            return false;

    }

    public boolean moveCursorBackward() {
        if (cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            return true;
        } else
            return false;
    }

    public boolean jumpToPosition(int position) {
        PerformanceNode test = head;
        for (int i = 1; i <= position && test != null; i++, test = test.getNext()) {
            if (i == position) {
                cursor = test;
                return true;
            }
        }
        return false;
    }


    public String toString() {
        System.out.printf("%7s%5s%25s%25s%20s%10s%15s" ,"Current", "No.", "Performance Name", "Lead Performer Name", "Participants", "Duration", "Start Time");
        System.out.println();
        for(int i = 0; i < 110; i++)
            System.out.print("-");
        System.out.println();
        PerformanceNode loop = head;
        for(int i = 1; loop != null; loop = loop.getNext(), i++) {
            if (cursor.equals(loop)) {
                if(loop.getPrev()!= null)
                    loop.setStartTime(loop.getPrev().getStartTime() + loop.getPrev().getDuration());
                else
                    loop.setStartTime(0);
                System.out.print("  *  \t  " + i);
                System.out.println(loop);
            } else {
                if(loop.getPrev()!= null)
                    loop.setStartTime(loop.getPrev().getStartTime() + loop.getPrev().getDuration());
                else
                    loop.setStartTime(0);
                System.out.print("\t\t  " +i);
                System.out.println(loop);

            }
        }

        return "";
    }

    public static void main(String[] args) {
        PerformanceList list = new PerformanceList();
        PerformanceNode node = new PerformanceNode(); // Bob
        PerformanceNode node1 = new PerformanceNode("Joe's Performance", "Joe", 10, 25);
        PerformanceNode node2 = new PerformanceNode("Tom's Performance", "Tom", 20, 60);
        PerformanceNode node3 = new PerformanceNode("Sam's Performance", "Sam", 30, 15);
        list.addToEnd(node); //creates Bob in the beginning 1
        list.addAfterCurrent(node1);//Adds Joe after Bob. Cursor is still at one 3
        list.addAfterCurrent(node2);//Adds Tom before Joe 2
        list.addToEnd(node3); // Adds Sam 4
        list.moveCursorForward();
        System.out.println(list);

        System.out.println(node2.getPrev().getDuration() + node2.getPrev().getStartTime());
    }
}
