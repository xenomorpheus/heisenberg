package au.net.hal9000.heisenberg.item;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import junit.framework.TestCase;

/**
 * The class <code>AnimalTest</code> contains tests for the class {@link <code>Animal</code>}
 * 
 * @author bruins
 * 
 * @version $Revision$
 */
public class AnimalTest extends TestCase {

    /**
     * Construct new test instance
     * 
     * @param name
     *            the test name
     */
    public AnimalTest(String name) {
        super(name);
    }

    /**
     * Test drink
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     * @throws InvalidTypeException
     */
    @Test
    public void testDrink() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Cat cat = new Cat();// close enough
        Water water = new Water();
        cat.drink(water);
    }

}

/*
 * $CPS$ This comment was generated by CodePro. Do not edit it. patternId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern strategyId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = assertTrue = false callTestMethod = true createMain =
 * false createSetUp = false createTearDown = false createTestFixture = false
 * createTestStubs = false methods = package = au.net.hal9000.heisenberg.item
 * package.sourceFolder = Heisenberg/src/test/java superclassType =
 * junit.framework.TestCase testCase = AnimalTest testClassType =
 * au.net.hal9000.heisenberg.item.Animal
 */
