//Joanne Hayashi
//EN.605.202.84.SP19: Lab 2 - Towers of Hanoi

package Module8Lab2;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * class: RecursiveTowerOfHanoi - recursive solution to the Towers of Hanoi
 * Ref: https://en.wikipedia.org/wiki/Tower_of_Hanoi
 */
public class RecursiveTowerOfHanoi {

   Tower source;      // or tower 1
   Tower auxillary;   // or tower 2
   Tower destination; // or tower 3

   int numDisks;
   int numMoves;

   public RecursiveTowerOfHanoi(int numDisks) {
      this.numDisks = numDisks;                     // user specified tower size
      this.source = new Tower(numDisks, "A");       // creates tower 1
      this.auxillary = new Tower(numDisks, "B");    // creates tower 2
      this.destination = new Tower(numDisks, "C");  // creates tower 2
      this.loadSourceTower();                       // initialize disks in tower 1
      this.numMoves = 0;                            // initializes number of moves
   }

   /**
    * method: move() - recursive determines disk movement 
    * @param numDisks - number of disks
    * @param source - tower 1
    * @param auxillary - tower 2
    * @param destination - tower 3
    * @param output - output file handler
    * @return - none
    */
   public void move(int numDisks, Tower source, Tower auxillary, Tower destination, BufferedWriter output) {
      if (numDisks >= 1) {
         this.numMoves++;
         move(numDisks - 1, source, destination, auxillary, output);
         try {
            output.write(
                  "   Move Disk " + numDisks + " from tower " + source.getName() + 
                  " to tower " + destination.getName() + "\n");
         } catch (IOException ioe) {
            System.err.println(ioe.toString());
            return;
         }
         destination.push(source.pop());
         move(numDisks - 1, auxillary, source, destination, output);
      } else {
         // no moves required for an empty source tower
      }
   }

   /**
    * method: printAllTowers() - prints contents of all towers
    * @param output - output file handler
    * @return - none
    */
   public void printAllTowers(BufferedWriter output) {
      try {
         source.printTower(output);
         auxillary.printTower(output);
         destination.printTower(output);
         output.write("\n");
      } catch (IOException ioe){
         System.err.println(ioe.toString());
         return;
      }
   }

   // ***** PRIVATE METHOD(S) *****
   
   /**
    * method: loadSourceTower() - load integers onto tower 1 to start game
    *         largest disk (greatest int value n) is at the bottom of the tower
    *         smallest disk (1) is at the top of the tower
    * @param - none
    * @return - none
    */
   private void loadSourceTower() {
      for (int i = numDisks; i > 0; i--) {
         source.push((i));
      }
   }

}