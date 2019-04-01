/**
 * @author Joanne Hayashi
 * EN.605.202.84.SP19: Lab 2 - Tower of Hanoi
 */
package Module8Lab2;
//import FileStreamOutput...

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

        TowerOfHanoiRecursiveRunner recRunner = new TowerOfHanoiRecursiveRunner();
        RecursiveTowerOfHanoi recToH;
        
        int numDisks = Integer.valueOf(args[0]);
        int diskCount;
      
        // metrics
        long startTime;
        long endTime;
        long runTime;

      // recursive run
      for(diskCount = 0; diskCount <= numDisks; diskCount++) {
          System.out.println("\n*|*|*|* Entering The Tower of Hanoi with " + 
                  diskCount + " Disk(s) *|*|*|*");
          
          // if mode = (r)ecursive 
          recToH = new RecursiveTowerOfHanoi(diskCount);

          // metrics
          startTime = System.nanoTime();
              
          recToH.printTowers();
          System.out.println("Begin...");
          
          recToH.move(diskCount, recToH.source, recToH.auxillary, recToH.destination);
          
          System.out.println("...End");
          recToH.printTowers();
          
          // metrics
          endTime = System.nanoTime();
          runTime = endTime - startTime;
          recRunner.saveMetrics(recToH.numMoves, runTime);
      }
      
      System.out.println("\nSummary:");
      System.out.println(recRunner.getMetrics());
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
