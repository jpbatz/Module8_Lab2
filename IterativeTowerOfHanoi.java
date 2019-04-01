/**
 * @author Joanne Hayashi
 * EN.605.202.84.SP19: Lab 2 - Tower of Hanoi
 */
package Module8Lab2;

// Ref: algorithm and heavily modified code by Sumit Ghosh on
// https://www.geeksforgeeks.org/iterative-tower-of-hanoi/
// Corrected and modified to use user defined stacks

// TODOs:
//        no output to screen
//        add error handling
//        add comments

public class IterativeTowerOfHanoi {

    Tower source;
    Tower auxillary;
    Tower destination;

    int numDisks;
    int numMoves;

    public IterativeTowerOfHanoi(int numDisks) {
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

    private void moveDisk(Tower src, Tower dest)
    {
        int tower1TopDisk = Integer.MIN_VALUE;
        int tower2TopDisk = Integer.MIN_VALUE;

        if (!src.isEmpty()) {
                tower1TopDisk = src.pop();
        }
        
        if (!dest.isEmpty()) {
                tower2TopDisk = dest.pop();
        }
        
        // only one disk
        if (this.numDisks == 1) {
                dest.push(tower1TopDisk);
                printMove(src, dest, tower1TopDisk);
        }
        
        // When tower 1 is empty
        else if (tower1TopDisk == Integer.MIN_VALUE)
        {
            src.push(tower2TopDisk);
            printMove(dest, src, tower2TopDisk);
        }

        // When tower2 tower is empty
        else if (tower2TopDisk == Integer.MIN_VALUE)
        {
            dest.push(tower1TopDisk);
            printMove(src, dest, tower1TopDisk);
        }

        // When top disk of tower1 > top disk of tower2
        else if (tower1TopDisk > tower2TopDisk)
        {
            src.push(tower1TopDisk);  // restore
            src.push(tower2TopDisk);
            printMove(dest, src, tower2TopDisk);
        }

        // When top disk of tower1 < top disk of tower2
        else if (tower1TopDisk < tower2TopDisk)
        { 
                dest.push(tower2TopDisk);  // restore
                dest.push(tower1TopDisk);
            printMove(src, dest, tower1TopDisk);
        }
    }
    
    // Function to show the movement of disks
    private void printMove(Tower srcTower, Tower destTower, int diskNum) {
        System.out.println("Move disk " + diskNum + 
                           " from " + srcTower.getName() + 
                           " to " + destTower.getName());
    }
    
    public void move(int n, Tower source, Tower auxillary, Tower destination) {

        int numMoves = (int) (Math.pow(2, n) - 1);
       
        if (n > 0) {
            // if number of disks n is even, then interchange
            // destination tower and auxiliary tower
            if (n % 2 == 0) {
                Tower tempTower = destination;
                destination = auxillary;
                auxillary = tempTower;
            }
            
            for (int i = 1; i <= numMoves; i++) {
                if (i % 3 == 1)
                        this.moveDisk(source, destination);
           
                else if (i % 3 == 2)
                        this.moveDisk(source, auxillary);
           
                else if (i % 3 == 0)
                        this.moveDisk(auxillary, destination);
            }
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