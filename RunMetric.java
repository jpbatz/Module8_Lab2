//Joanne Hayashi
//EN.605.202.84.SP19: Lab 2 - Towers of Hanoi

package Module8Lab2;

/**
 *  method: RunMetric() - a runtime metric object constructor
 *  @param size - problem size
 *  @param runTime - running time of problem
 *  @ref - Project0 (JHU)
 */
public class RunMetric {

   public RunMetric (long size, long runTime) {
      this.size = size;
      this.runTime = runTime;
   }

   /**
    *  method: getRuntime() - returns running time
    *  @param none
    *  @return runTime - running time in nSec
    */
   public long getRuntime() {
      return this.runTime;
   }
   
   /**
    *  method: getSize() - returns problem size
    *  @param none
    *  @return size - problem size
    */
   public long getSize() {
      return this.size;
   }

   // ***** PRIVATE VARIABLE(S) *****
   
   private long size;
   private long runTime;
   
}
