package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;

/**
 * Test for Currentcy class.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class CurrencyTest {
  /** float comparison tolerance. */
  private static final float TOLERANCE = 0.00001F;

  /** pp test value. */
  private static final int PP = 1;

  /** gp test value. */
  private static final int GP = 2;

  /** sp test value. */
  private static final int SP = 4;

  /** cp test value. */
  private static final int CP = 8;

  /** Test for constructor without params. */
  @Test
  public void testCoinCollectionConstructorNoParams() {
    Currency cc = new Currency();
    assertEquals("pp", 0, cc.getPp());
    assertEquals("gp", 0, cc.getGp());
    assertEquals("sp", 0, cc.getSp());
    assertEquals("cp", 0, cc.getCp());
  }

  /** Tests for constructor with params. */
  @Test
  public void testCoinCollectionConstructorWithCoinParams() {
    Currency cc = new Currency(PP, GP, SP, CP);
    assertEquals("pp", PP, cc.getPp());
    assertEquals("gp", GP, cc.getGp());
    assertEquals("sp", SP, cc.getSp());
    assertEquals("cp", CP, cc.getCp());
  }

  /** Tests for constructor with params. */
  @Test
  public void testCoinCollectionConstructorWithCC() {
    Currency cc = new Currency(PP, GP, SP, CP);
    Currency cc2 = new Currency(cc);
    assertEquals("pp", PP, cc2.getPp());
    assertEquals("gp", GP, cc2.getGp());
    assertEquals("sp", SP, cc2.getSp());
    assertEquals("cp", CP, cc2.getCp());
  }

  /** Tests for platinum coin. */
  @Test
  public void testPp() {
    Currency cc = new Currency();
    cc.setPp(10);
    assertEquals("10pp", 10.0F, cc.getPp(), TOLERANCE);
  }

  /** Tests for gold coin. */
  @Test
  public void testGp() {
    Currency cc = new Currency();
    cc.setGp(10);
    assertEquals("10gp", 10.0F, cc.getGp(), TOLERANCE);
  }

  /** Tests for silver coin. */
  @Test
  public void testSp() {
    Currency cc = new Currency();
    cc.setSp(10);
    assertEquals("10sp", 10.0F, cc.getSp(), TOLERANCE);
  }

  /** Tests for copper coin. */
  @Test
  public void testCp() {
    Currency cc = new Currency();
    cc.setCp(10);
    assertEquals("10cp", 10.0F, cc.getCp(), TOLERANCE);
  }

  /** Method testGetGpEquiv. */
  @Test
  public void testGetGpEquiv() {
    Currency cc = new Currency(PP, GP, SP, CP);
    assertEquals("1pp,2gp,4sp,8cp", 12.48F, cc.getGpEquiv(), TOLERANCE);
  }

  /** Tests for transfer method. */
  @Test
  public void testTransferCoinCollection() {
    Currency cc = new Currency(PP, GP, SP, CP);
    Currency cc2 = new Currency(2, 4, 6, 3);
    cc.transfer(cc2);
    assertEquals("transfer cc", 37.11F, cc.getGpEquiv(), TOLERANCE);
    assertEquals("transfer cc2", 0F, cc2.getGpEquiv(), TOLERANCE);
  }

  /** Tests for add method. */
  @Test
  public void testAddCoinCollection() {
    /* test each coin type. */
    Currency cc = new Currency(PP, GP, SP, CP);
    Currency cc2 = new Currency(2, 4, 6, 3);
    cc.add(cc2);
    assertEquals("add cc", 37.11F, cc.getGpEquiv(), TOLERANCE);
    assertEquals("add cc2", 24.63F, cc2.getGpEquiv(), TOLERANCE);
  }

  /** Tests for equals method. */
  @Test
  public void testEquals() {
    /* test each coin type. */
    Currency cc = new Currency(PP, GP, SP, CP);
    assertTrue("equals", cc.equals(new Currency(PP, GP, SP, CP)));
    assertFalse("not equals", cc.equals(new Currency(PP + 1, GP, SP, CP)));
    assertFalse("not equals", cc.equals(new Currency(PP, GP + 1, SP, CP)));
    assertFalse("not equals", cc.equals(new Currency(PP, GP, SP + 1, CP)));
    assertFalse("not equals", cc.equals(new Currency(PP, GP, SP, CP + 1)));
  }

  /** Tests of persistence methods. */
  @Test
  public void testPersistence() {
    File fileObj = new File(System.getProperty("java.io.tmpdir"), "currency_persit_test.ser");
    String filename = fileObj.getAbsolutePath();
    Currency old = new Currency(PP, GP, SP, CP);
    // Store the object
    try {
      FileOutputStream fos = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(fos);
      out.writeObject(old);
      out.close();
    } catch (IOException ex) {
      fail(ex.toString());
    }
    // Get the object back

    try {
      FileInputStream fis = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(fis);
      Currency newObj = (Currency) in.readObject();
      in.close();
      assertTrue("deserialized new equals old", old.equals(newObj));
    } catch (IOException ex) {
      fail(ex.toString());
    } catch (ClassNotFoundException ex) {
      fail(ex.toString());
    }
  }

  /** Tests for clone method. */
  @Test
  public void testClone() {
    Currency original = new Currency();
    Currency clone = null;
    try {
      clone = original.clone();
    } catch (CloneNotSupportedException e) {
      fail(e.toString());
    }

    // x.clone() != x
    // will be true, and that the expression:
    assertTrue("x.clone() != x", clone != original);

    // x.clone().getClass() == x.getClass()
    // will be true, but these are not absolute requirements.
    assertTrue("x.clone().getClass() == x.getClass()", clone.getClass() == original.getClass());

    // By convention, the returned object should be obtained by calling
    // super.clone. If a class and all of its super-classes (except
    // Object)
    // obey this convention, it will be the case that
    // x.clone().getClass() == x.getClass().
    // Already tested above.

    // While it is typically the case that:
    // x.clone().equals(x)
    // will be true, this is not an absolute requirement.
    assertTrue("x.clone().equals(x)", clone.equals(original));

    // Class specific tests
    // Make sure the cloning is deep, not shallow.
    // e.g. test the non-mutable, non-primitives
  }

  /** Tests for hashCode method. */
  @Test
  public void testHashCode() {
    Currency c1 = new Currency(PP, GP, SP, CP);
    Currency c2 = new Currency(PP, GP, SP, CP);
    assertEquals("equals", c1.hashCode(), c2.hashCode());
  }

  /** Tests for normalise method. */
  @Test
  public void testNormalise() {
    // copper, silver and gold all push one coin up to the next coin type.
    Currency c1 =
        new Currency(
            8,
            Currency.COIN_CONVERSION + 4,
            Currency.COIN_CONVERSION + 2,
            Currency.COIN_CONVERSION + 1);
    c1.normalise();
    Currency c2 = new Currency(9, 5, 3, 1);
    assertTrue("equals", c1.equals(c2));
  }

  /** Tests for toString method. */
  @Test
  public void testToString() {
    Currency c = new Currency(PP, GP, SP, CP);
    assertEquals("equals", "1PP, 2GP, 4SP, 8CP", c.toString());
  }
}
