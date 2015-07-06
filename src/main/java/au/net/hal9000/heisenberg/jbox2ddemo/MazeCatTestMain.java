package au.net.hal9000.heisenberg.jbox2ddemo;


/**
 * The entry point for the testbed application
 * 
 * @author Daniel Murphy
 */
public class MazeCatTestMain {

    public static void main(String[] args) {

        MazeCatTest mazeCatTest = new MazeCatTest();
        mazeCatTest.initTest();
        mazeCatTest.step();
    }
}
