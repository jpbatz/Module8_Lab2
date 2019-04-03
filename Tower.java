//Joanne Hayashi
//EN.605.202.84.SP19: Lab 2 - Towers of Hanoi

package Module8Lab2;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * class: Tower - a stack implementation of a tower of integers
 */
public class Tower {
   int tower[];
   int numDisks;
   int top;
   String name;

   public Tower(int numDisks, String name) {
      this.numDisks = numDisks;
      this.tower = new int[numDisks];
      this.top = -1;
      this.name = name;
   }

   /**
    * method: getName() - retrieves tower name
    * @param - none
    * @return - tower name
    */
   public String getName() {
      return this.name;
   }

   /**
    * method: isEmpty() - test for empty tower
    * @param - none
    * @return - tests for if top is at bottom of tower
    */
   public boolean isEmpty() {
      return (this.top == - 1);
   }

   /**
    * method: isFull() - test for full tower
    * @param - none
    * @return - tests for if top is at top of tower
    */
   public boolean isFull() {
      return (this.top == this.tower.length - 1);
   }

   /**
    * method: push() - adds item to top of the tower
    * @param - item
    * @return - none
    */
   public void push(int disk) {
      if (!this.isFull()) {
         tower[++top] = disk;
      } else {
         System.out.println("push: Tower stack is full.");
         System.exit(5);
      }
   }

   /**
    * method: pop() - removes item from top of tower
    * @param - none
    * @return - popped item
    */
   public int pop() {
      int disk = 0;
      if (!this.isEmpty()) {
         disk = tower[top--];
      } else {
         System.out.println("pop(): Tower stack is empty.");
         System.exit(6);
      }
      return disk;
   }

   /**
    * method: reset() - removes all items from tower
    * @param - none
    * @return - none
    */
   public void reset() {
      while (!this.isEmpty()) {
         this.pop();
      }
   }

   /**
    * method: printTower() - iteratively prints contents of tower
    * @param - output file handler
    * @return - none
    */
   public void printTower(BufferedWriter output) {
      if (top == -1) {
         try {
            output.write("\nTower " + name + ": [ EMPTY ]");
         } catch(IOException ioe) {
            System.err.println(ioe.toString());
            return;
         }
      } else {
         try {
            output.write("\nTower " + name + ": [ ");
         } catch(IOException ioe) {
            System.err.println(ioe.toString());
            return;
         }
         
         for (int i = 0; i <= top; i++) {
            try {
               output.write(tower[i] + " ");
            } catch(IOException ioe) {
               System.err.println(ioe.toString());
               return;
            }
         }
         try {
            output.write("]");
         } catch(IOException ioe) {
            System.err.println(ioe.toString());
            return;
         }
      }
   }
}