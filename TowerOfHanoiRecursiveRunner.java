//Joanne Hayashi
//EN.605.202.84.SP19: Lab 2 - Towers of Hanoi

package Module8Lab2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class: TowerOfHanoiRecursiveRunner - runs recursive solution to the Towers of Hanoi
 *                                      and reports metrics
 * Ref: Project0 (JHU)
 */
public class TowerOfHanoiRecursiveRunner {
   
   RunMetric[] runMetrics;
   int metricsIndex;
   
   public TowerOfHanoiRecursiveRunner() {
      runMetrics = new RunMetric[MAX_DISKS];
      metricsIndex = 0;
   }
   
   // ***** PROGRAM ENTRY POINT *****
   
   public static void main(String[] args) {

      BufferedWriter output;
      
      int numDisks = Integer.valueOf(args[0]);
      int diskCount;
      
      TowerOfHanoiRecursiveRunner recRunner = new TowerOfHanoiRecursiveRunner();
      RecursiveTowerOfHanoi recToH;

      long startTime;
      long endTime;
      long runTime;

      // checks for 2 command line arguments
      // Ref: Project0.java
      if (args.length != 2) {
         System.out.println("Usage:  java TowerOfHanoiRecursiveRunner " + 
                            "[number of disks]" + " [output filespec]");
         System.exit(1);
      }
      
      // opens output file handler
      // Ref: Project0.java
      try {
         output = new BufferedWriter(new FileWriter(args[1]));
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         return;
      }
      
     for(diskCount = 0; diskCount <= numDisks; diskCount++) {
        recRunner.writeOut("\n*|*|*|* Entering The Towers of Hanoi with " + 
              diskCount + " Disk(s) *|*|*|*\n", output);

        recToH = new RecursiveTowerOfHanoi(diskCount);

        recToH.printAllTowers(output);
        recRunner.writeOut("\nBegin...\n", output);

        startTime = System.nanoTime();
        recToH.move(diskCount, recToH.source, recToH.auxillary, recToH.destination, output);
        endTime = System.nanoTime();
        
        recRunner.writeOut("...End\n", output);
        recToH.printAllTowers(output);

        runTime = endTime - startTime;
        recRunner.saveMetrics(recToH.numMoves, runTime, output);
     }
     
     recRunner.writeOut("\nSummary:", output);
     recRunner.writeOut(recRunner.getMetrics(), output);
     
     // closes file handler
     // Ref: Project0.java
     try {
        output.close();
     } catch (IOException ioe) {
        System.err.println(ioe.toString());
        return;
     }
  }

   // ***** PRIVATE METHOD(S) *****
   
   /**
    *  method: writeOut() - writes a string to the output file handler
    *  @param text - text to write.
    *  @param output - output file handler
    *  @return - none
    *  @ref Project0.java
    */
   private void writeOut(String text, BufferedWriter output) {
      try {
         output.write(text + "\n", 0, text.length());  
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(3);
      }
      return;
   }
      
   /**
    * method: saveMetrics() - saves number of disks and run time
    *                         for metric object
    * @param numDisks - number of disks, n
    * @param timeElapsed - running time for n disks
    * @param output - output file handler
    * @return - none
    * @ref - Project0 (JHU)
    */
   private void saveMetrics(long numDisks, long timeElapsed, BufferedWriter output) {
      RunMetric item = new RunMetric(numDisks, timeElapsed);
      runMetrics[metricsIndex] = item;
      try {
         output.write("\nRuntime: " + runMetrics[metricsIndex].getSize() + 
           " moves in " +  runMetrics[metricsIndex].getRuntime() + " nSec\n");
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         return;
      }
      metricsIndex++;
      return;
   }

   /**
    * method: getMetrics() - shows metrics for all numbers of disks n=0 to n
    * @param - none
    * @return - all run metric objects stored in the run metrics array
    * @ref: Project0 (JHU)
    */
   private String getMetrics() {
      StringBuilder metrics = new StringBuilder();
      for (int i=0; i<metricsIndex; i++) {
         metrics.append("[" + i + "]: " + runMetrics[i].getSize() + 
            " moves in " + runMetrics[i].getRuntime() + " nSec\n");
      }
      metrics.append("\n");
      return metrics.toString();
   }

   // ***** PRIVATE VARIABLE(S) *****
   
   private int MAX_DISKS = 50;   // depends on hardware limitations

}
