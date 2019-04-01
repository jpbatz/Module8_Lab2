/**
 * @author Joanne Hayashi
 * EN.605.202.84.SP19: Lab 2 - Tower of Hanoi
 */
package Module8Lab2;

// TODOs:
//        output to file, only
//        no output to screen
//        report times at the end of each round
//        add error handling
//        add comments

public class RecursiveTowerOfHanoi {

    Tower source;
    Tower auxillary;
    Tower destination;

    int numDisks;
    int numMoves;

    public RecursiveTowerOfHanoi(int numDisks) {
        this.numDisks = numDisks;
        source = new Tower(numDisks, "A");
        auxillary = new Tower(numDisks, "B");
        destination = new Tower(numDisks, "C");
        loadSourceTower();
        numMoves = 0;
    }

    public void loadSourceTower() {
        for (int i = numDisks; i > 0; i--) {
            source.push((i));
        }
    }

    public void move(int n, Tower source, Tower auxillary, Tower destination) {
        if (n >= 1) {
                numMoves++;
            // start timer
            move(n - 1, source, destination, auxillary);
            // stop timer
            System.out.println(
                    "Move Disk " + n + " from Tower " + source.getName() + 
                    " " + " to Tower " + destination.getName());
            // resume timer
            destination.push(source.pop());
            move(n - 1, auxillary, source, destination);
            // stop timer and report timer total
        } else {
            // System.out.println("No moves on an empty tower");
        }
    }

    public void printTowers() {
        System.out.println();
        source.print();
        auxillary.print();
        destination.print();
        System.out.println();
    }
}