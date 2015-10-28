/**
 * Created by jimmy on 2/17/2015.
 */
/**
 * Created by Jimmy on 2/14/2015 in PACKAGE_NAME
 */
public class PerformanceNode {
    private PerformanceNode nextLink;
    private PerformanceNode prevLink;
    private String nameOfPerformance;
    private String nameOfPerformer;
    private int numberOfParticipants;
    private int duration;
    private int startTime;

    public PerformanceNode(){
        this("Bob's Performance", "Bob", 5, 30);

    }
    public PerformanceNode(String nameOfPerformance, String nameOfPerformer, int numberOfParticipants, int duration) {
        this.nameOfPerformance = nameOfPerformance;
        this.nameOfPerformer = nameOfPerformer;
        this.numberOfParticipants = numberOfParticipants;
        this.duration = duration;
    }

    public PerformanceNode(PerformanceNode node, PerformanceNode nextLink, PerformanceNode prevLink){
        nameOfPerformance = node.getNameOfPerformance();
        nameOfPerformer = node.getNameOfPerformer();
        numberOfParticipants = node.getNumberOfParticipants();
        duration = node.getDuration();
        this.nextLink = nextLink;
        this.prevLink = prevLink;
    }
    public String getNameOfPerformance() {
        return this.nameOfPerformance;
    }
    public void setNameOfPerformance(String nameOfPerformance) {
        this.nameOfPerformance = nameOfPerformance;
    }
    public String getNameOfPerformer() {
        return this.nameOfPerformer;
    }
    public void setNameOfPerformer(String nameOfPerformer) {
        this.nameOfPerformer = nameOfPerformer;
    }
    public int getNumberOfParticipants() {
        return this.numberOfParticipants;
    }
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getStartTime() {
        return (this.startTime);
    }
    public void setStartTime(int time) {
        startTime = time;
    }
    public void setNext(PerformanceNode node) {
        nextLink = node;
    }
    public void setPrev(PerformanceNode node) {
        prevLink = node;
    }
    public PerformanceNode getNext() {
        return nextLink;
    }
    public PerformanceNode getPrev() {
        return prevLink;
    }

    @Override
    public String toString() {
        return String.format("           %-23s%-22s %10d %10d %10d",getNameOfPerformance(),getNameOfPerformer(), getNumberOfParticipants(), getDuration(), getStartTime());
    }

    public static void main(String[] args) {
        PerformanceNode node = new PerformanceNode("Orchestra", "Bob Joe", 5, 30);
        PerformanceNode node1 = new PerformanceNode();
        PerformanceNode node2 = new PerformanceNode("What", "hey", 5, 50);
        node.setNext(node1);
        node1.setPrev(node);
        node1.setNext(node2);
        node2.setPrev(node1);
        System.out.println(node);
        System.out.println(node1);
        System.out.println(node2);
    }
}


