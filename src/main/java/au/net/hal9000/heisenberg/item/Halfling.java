package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Halfling extends Humanoid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Halfling() {
		super("Halfling");
	}

	public Halfling(String string) {
		super(string);
	}
}