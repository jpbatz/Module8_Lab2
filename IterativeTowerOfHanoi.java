//Joanne Hayashi
//EN.605.202.84.SP19: Lab 2 - Towers of Hanoi

package Module8Lab2;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * class: IterativeTowerOfHanoi - iterative solution to the Towers of Hanoi
 * Ref: https://www.geeksforgeeks.org/iterative-tower-of-hanoi/
 */
public class IterativeTowerOfHanoi {

   Tower source;      // or tower 1
   Tower auxillary;   // or tower 2
   Tower destination; // or tower 3

   int numDisks;
   int numMoves;

   public IterativeTowerOfHanoi(int numDisks) {
      this.numDisks = numDisks;                     // user specified tower size
      this.source = new Tower(numDisks, "A");       // creates tower 1
      this.auxillary = new Tower(numDisks, "B");    // creates tower 2
      this.destination = new Tower(numDisks, "C");  // creates tower 2
      this.loadSourceTower();                       // initialize disks in tower 1
      this.numMoves = 0;                            // initializes number of moves
   }

   /**
    * method: move() - determines general disk movement between towers
    * @param numDisks - number of disks
    * @param source - tower 1
    * @param auxillary - tower 2
    * @param destination - tower 3
    * @param output - output file handler
    * @return - none
    */
   public void move(int numDisks, Tower source, Tower auxillary, Tower destination, BufferedWriter output) {

	  // calculates number of moves required for n disks 2^n - 1
      this.numMoves = (int) (Math.pow(2, numDisks) - 1);

      if (numDisks > 0) {   // n=0 disks does not require movement
         // if number of disks n is even, swap dest and aux towers
         // so final tower is always tower 3
         if (numDisks % 2 == 0) {
            Tower tempTower = destination;
            destination = auxillary;
            auxillary = tempTower;
         }

         // three basic moves clears way for next largest disk
         // determines which two towers but not direction
         for (int i = 1; i <= this.numMoves; i++) {
            if (i % 3 == 1) {
               this.moveDisk(source, destination, output);
            } else if (i % 3 == 2) {
               this.moveDisk(source, auxillary, output);
            } else if (i % 3 == 0) {
               this.moveDisk(auxillary, destination, output);
            }
         }
      }
   }

   /**
    * method: printAllTowers() - prints contents of all 3 towers
    * @param output - output file handler
    * @return none
    */
   public void printAllTowers(BufferedWriter output) {
      try {
         this.source.printTower(output);
         this.auxillary.printTower(output);
         this.destination.printTower(output);
         output.write("\n");
      } catch (IOException ioe){
         System.err.println(ioe.toString());
         return;
      }
   }

   // ***** PRIVATE METHOD(S) *****
   
   /**
    * method: loadSourceTower() load integers onto tower 1 to start game
    *         largest disk (greatest int value n) is at the bottom of the tower
    *         smallest disk (1) is at the top of the tower
    * @param - none
    * @return - none
    */
   private void loadSourceTower() {
      for (int i = this.numDisks; i > 0; i--) {
         this.source.push((i));
      }
   }

   /**
    * method: moveDisk() - determines direction of move and moves top disk
    * @param src - on of two towers to be tested, calculates from or to
    * @param dest - on of two towers to be tested, calculates from or to
    * @param output - output file handler
    * @return - none
    */
   private void moveDisk(Tower src, Tower dest, BufferedWriter output) {
      
      int tower1Disk;
      int tower2Disk;

      if (!src.isEmpty()) {
         tower1Disk = src.pop();
      } else {
         tower1Disk = Integer.MIN_VALUE;
      }

      if (!dest.isEmpty()) {
         tower2Disk = dest.pop();
      } else {
         tower2Disk = Integer.MIN_VALUE;
      }

      // only one disk, n=1
      if (this.numDisks == 1) {
         dest.push(tower1Disk);
         printMove(src, dest, tower1Disk, output);
      }

      // tower 1 is empty
      else if (tower1Disk == Integer.MIN_VALUE) {
         src.push(tower2Disk);
         printMove(dest, src, tower2Disk, output);
      }

      // tower2 is empty
      else if (tower2Disk == Integer.MIN_VALUE) {
         dest.push(tower1Disk);
         printMove(src, dest, tower1Disk, output);
      }

      // disk of tower1 is larger than disk of tower2
      else if (tower1Disk > tower2Disk) {
         src.push(tower1Disk);  // restore
         src.push(tower2Disk);
         printMove(dest, src, tower2Disk, output);
      }

      // disk of tower1 is smaller than disk of tower2
      else if (tower1Disk < tower2Disk) { 
         dest.push(tower2Disk);  // restore
         dest.push(tower1Disk);
         printMove(src, dest, tower1Disk, output);
      }
   }

   /**
    * method: printMove() - shows the movement of disks between 
    *                       src and dest towers
    * @param srcTower - tower 1
    * @param destTower - tower 2
    * @param diskNum - tower 3
    * @param output - output file handler
    * @return - none
    */
   private void printMove(Tower srcTower, Tower destTower, int diskNum, BufferedWriter output) {
      try {
         output.write("   Move disk " + diskNum + 
                      " from tower " + srcTower.getName() + 
                      " to tower " + destTower.getName() + "\n");
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         return;
      }
   }
}