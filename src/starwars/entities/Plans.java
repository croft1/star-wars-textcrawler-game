package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;

/**
 * Class for the Death Star Plans
 * 
 * The Death Star plans entity, which is to be held by Princess Leia, is key in the rebels'
 * assault on the dark side. Bring these with Leia, along with R2-D2 to Yavin Four - and
 * the game is won!
 * 
 * @author jas
 * @author mewc
 *
 */
public class Plans extends SWEntity {

	/**
	 * Constructor for the <code>Plans</code>. 
	 * 
	 * Creates the Plans entity that will be beld by General Organa (Leia).
	 * 
	 * @param	m	- MessageRenderer that these Plans will use to display notifications (if any)
	 */
    public Plans(MessageRenderer m) {
        super(m);
        this.shortDescription = " The Death Star Plans";
        this.longDescription = "a great big architectural diagram of the Death Star";

        capabilities.add(Capability.HOME);
    }
    
    
}

