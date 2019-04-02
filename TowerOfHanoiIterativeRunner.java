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

public class TowerOfHanoiIterativeRunner {
    
    // metrics
    RunMetric[] runMetrics;
    int metricsIndex;
    
    public TowerOfHanoiIterativeRunner() {
        // metrics
        runMetrics = new RunMetric[100];  // assign constant at the bottom
        metricsIndex = 0;
    }

    public static void main(String[] args) {

        BufferedWriter  output;
        
        int numDisks = Integer.valueOf(args[0]);
        int diskCount;
        
        TowerOfHanoiIterativeRunner iterRunner = new TowerOfHanoiIterativeRunner();
        IterativeTowerOfHanoi iterToH;

        int totalNumMoves;
      
        // metrics
        long startTime;
        long endTime;
        long runTime;

        // Check for 2 command line arguments
        // Ref: Project0.java
        if (args.length != 2) {
            System.out.println("Usage:  java TowerOfHanoiIterativeRunner " + 
            				   "[number of disks]" + " [output filespec]");
            System.exit(1);
        }
        
        //  Open the output file handler
        // Ref: Project0.java
        try {
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (Exception ioe) {
            System.err.println(ioe.toString());
            return;
        }
        
      // iterative run
      for(diskCount = 0; diskCount <= numDisks; diskCount++) {
          System.out.println("\n*|*|*|* Entering The Tower of Hanoi with " + 
                  diskCount + " Disk(s) *|*|*|*");
          totalNumMoves = (int) (Math.pow(2, diskCount) - 1);
          
          // if mode = (i)terative
          iterToH = new IterativeTowerOfHanoi(diskCount);

          // metrics
          startTime = System.nanoTime();
              
          iterToH.printTowers();
          System.out.println("Begin...");
          
          iterToH.move(diskCount, iterToH.source, iterToH.auxillary, iterToH.destination);
          
          System.out.println("...End");
          iterToH.printTowers();
          
          // metrics
          endTime = System.nanoTime();
          runTime = endTime - startTime;
          iterRunner.saveMetrics(totalNumMoves, runTime);
      }
      
      System.out.println("\nSummary:");
      System.out.println(iterRunner.getMetrics());
      
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
                metrics.append("[" + i+ "]: " + runMetrics[i].getSize() + " moves in " + runMetrics[i].getRuntime() + " nSec\n");
            }
            metrics.append("\n");
            return metrics.toString();
    }
}
