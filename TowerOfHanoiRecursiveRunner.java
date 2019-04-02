/**
 * @author Joanne Hayashi
 * EN.605.202.84.SP19: Lab 2 - Tower of Hanoi
 */
package Module8Lab2;
//import FileStreamOutput...

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TODO:
//no output to screen
//add error handling
//add comments

public class TowerOfHanoiRecursiveRunner {
    
    // metrics
    RunMetric[] runMetrics;
    int metricsIndex;
    
    public TowerOfHanoiRecursiveRunner() {

        // metrics
//       runMetrics = new RunMetric[discs];
        runMetrics = new RunMetric[100];  // assign constant at the bottom
        metricsIndex = 0;
        
    }
    
    
    public static void main(String[] args) {

        BufferedWriter output;
        
        int numDisks = Integer.valueOf(args[0]);
        int diskCount;
        
        TowerOfHanoiRecursiveRunner recRunner = new TowerOfHanoiRecursiveRunner();
        RecursiveTowerOfHanoi recToH;

      
        // metrics
        long startTime;
        long endTime;
        long runTime;

        // Check for 2 command line arguments
        // Ref: Project0.java
        if (args.length != 2) {
            System.out.println("Usage:  java TowerOfHanoiRecursiveRunner " + 
            				   "[number of disks]" + " [output filespec]");
            System.exit(1);
        }
        
        // open the output file handler
        // Ref: Project0.java
        try {
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (Exception ioe) {
            System.err.println(ioe.toString());
            return;
        }
        
      // recursive run
      for(diskCount = 0; diskCount <= numDisks; diskCount++) {
          System.out.println("\n*|*|*|* Entering The Tower of Hanoi with " + 
                  diskCount + " Disk(s) *|*|*|*");
          recRunner.writeResult("\n*|*|*|* Entering The Tower of Hanoi with " + 
                  diskCount + " Disk(s) *|*|*|*", output);
          
          // if mode = (r)ecursive 
          recToH = new RecursiveTowerOfHanoi(diskCount);

          // metrics
          startTime = System.nanoTime();
              
          recToH.printTowers(output);
          System.out.println("Begin...");
          recRunner.writeResult("\nBegin...", output);
          
          recToH.move(diskCount, recToH.source, recToH.auxillary, recToH.destination);
          
          System.out.println("...End");
          recRunner.writeResult("...End", output);
          recToH.printTowers(output);
          
          // metrics
          endTime = System.nanoTime();
          runTime = endTime - startTime;
          recRunner.saveMetrics(recToH.numMoves, runTime);
      }
      
      System.out.println("\nSummary:");
      recRunner.writeResult("\nSummary:", output);
      
      System.out.println(recRunner.getMetrics());
      recRunner.writeResult(recRunner.getMetrics(), output);
      
      // close filespec and return to operating system
      // Ref: Project0.java
      try {
          output.close();
      } catch (Exception x) {
          System.err.println(x.toString());
      }
      
  }
    
    /**
     *  Write a string to the output stream.
     *  @param text   The text to write.
     *  @param output The output stream to write the text to.
     *  @ref Project0.java
     */
    private void writeResult(String text, BufferedWriter output) {
        try {
            output.write(text, 0, text.length());  
            output.newLine();
        } catch (IOException iox) {
            System.err.println(iox.toString());
            System.exit(3);
        }
        return;
    }
    
    // metrics methods
    
    // Ref: Project0.java
    private void saveMetrics(long n, long timeElapsed) {
            RunMetric item = new RunMetric(n, timeElapsed);
            runMetrics[metricsIndex] = item;
            System.out.println("Runtime: " + runMetrics[metricsIndex].getSize() + " moves in " +  runMetrics[metricsIndex].getRuntime() + " nSec");
            metricsIndex++;
            return;
    }

    // Ref: Project0.java
    private String getMetrics() {
        StringBuilder metrics = new StringBuilder();
        for (int i=0; i<metricsIndex; i++) {
            metrics.append("[" + i + "]: " + runMetrics[i].getSize() + " moves in " + runMetrics[i].getRuntime() + " nSec\n");
        }
        metrics.append("\n");
        return metrics.toString();
    }
}
