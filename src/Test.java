import solutions.PowerOfFour;

/**
 * Created by PPlovboiledfish on 10/31/15.
 */
public class Test {
    static public void main(String[] args) {
        long startTime = System.nanoTime();
        PowerOfFour.Test.randomTest();
        System.out.println( "Total Time: " + (System.nanoTime() - startTime) / 1000000 + "ms");
    }
}
