package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class DamageTest {

	@Test
	public void testDamageAccept() {
		Cookie cookie = new Cookie();
		Damage damage = new Damage();
		damage.setImpact(1);
		cookie.accept(damage);
		assertEquals("hit points", -1, cookie.getHitPoints(), 0.001F);
	}

	@Test
	public void testDamageAcceptModified() {
		Cookie cookie = new Cookie();
		cookie.setDamageModifierElectrical(0.5F);
		cookie.setHitPoints(5);
		Damage damage = new Damage();
		damage.setElectrical(3);
		damage.setSonic(0.4F);
		cookie.accept(damage);
		assertEquals("hit points", 3.1F, cookie.getHitPoints(), 0.001F);
	}
	
}
