package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.util.PcClass;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Elf extends Humanoid {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Elf.
     */
    public Elf() {
        this("Elf");
    }

    /**
     * Constructor for Elf.
     * @param string String
     */
    public Elf(String string) {
        super(string);
    }

    /**
     * Constructor for Elf.
     * @param string String
     * @param description String
     */
    public Elf(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for Elf.
     * @param name String
     * @param description String
     * @param pcClass PcClass
     */
    public Elf(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }
}
