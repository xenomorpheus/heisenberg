package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.util.PcClass;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Human extends Humanoid {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Human() {
		this("Human");
	}

	public Human(String string) {
		super(string);
	}

    public Human(String name, PcClass pcClass) {
        super(name, pcClass);
    }
}
