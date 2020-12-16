package au.net.hal9000.heisenberg.fifthed.combat;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class TimerRoundTest {

  @Test
  public void testGetAvailableActionDurationSetInitial() {
    TimerRound timer = new TimerRound();
    Set<ActionDuration> got = timer.getAvailableActionDurationSet();
    Set<ActionDuration> expected = new HashSet<ActionDuration>();
    expected.add(ActionDuration.FULLROUND);
    expected.add(ActionDuration.STANDARD);
    expected.add(ActionDuration.MOVE);
    expected.add(ActionDuration.FIVEFOOTSTEP);
    expected.add(ActionDuration.IMMEDIATE);
    expected.add(ActionDuration.SWIFT);
    expected.add(ActionDuration.FREE);
    assertEquals(expected, got);
  }

  @Test
  public void testGetAvailableActionDurationSetTakeFullRoundAction() {
    TimerRound timer = new TimerRound();
    timer.takeActionDuration(ActionDuration.FULLROUND);
    Set<ActionDuration> got = timer.getAvailableActionDurationSet();
    Set<ActionDuration> expected = new HashSet<ActionDuration>();
    expected.add(ActionDuration.FIVEFOOTSTEP);
    expected.add(ActionDuration.IMMEDIATE);
    expected.add(ActionDuration.SWIFT);
    expected.add(ActionDuration.FREE);
    assertEquals(expected, got);
  }

  @Test
  public void testGetAvailableActionDurationSetTakeStandard() {
    TimerRound timer = new TimerRound();
    timer.takeActionDuration(ActionDuration.STANDARD);
    Set<ActionDuration> got = timer.getAvailableActionDurationSet();
    Set<ActionDuration> expected = new HashSet<ActionDuration>();
    expected.add(ActionDuration.MOVE);
    expected.add(ActionDuration.FIVEFOOTSTEP);
    expected.add(ActionDuration.IMMEDIATE);
    expected.add(ActionDuration.SWIFT);
    expected.add(ActionDuration.FREE);
    assertEquals(expected, got);
  }

  @Test
  public void testGetAvailableActionDurationSetTakeFiveFootStep() {
    TimerRound timer = new TimerRound();
    timer.takeActionDuration(ActionDuration.FIVEFOOTSTEP);
    Set<ActionDuration> got = timer.getAvailableActionDurationSet();
    Set<ActionDuration> expected = new HashSet<ActionDuration>();
    expected.add(ActionDuration.FULLROUND);
    expected.add(ActionDuration.STANDARD);
    expected.add(ActionDuration.IMMEDIATE);
    expected.add(ActionDuration.SWIFT);
    expected.add(ActionDuration.FREE);
    assertEquals(expected, got);
  }
}
