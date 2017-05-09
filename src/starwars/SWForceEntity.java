package starwars;

import java.util.HashSet;

import edu.monash.fit2099.simulator.matter.Entity;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;


/**
 * Class that represents inanimate objects in the Star Wars world. Objects that cannot move for example trees.
 * 
 * @author 	ram
 * @see 	edu.monash.fit2099.simulator.matter.Entity
 * @see 	SWEntityInterface
 */

public class SWForceEntity extends SWEntity implements SWForceEntityInterface {
	
	
	/**
	 * Constructor for this <code>SWEntity</code>. Will initialize this <code>SWEntity</code>'s
	 * <code>messageRenderer</code> and set of capabilities.
	 * 
	 * @param m the <code>messageRenderer</code> to display messages
	 */
	protected SWForceEntity(MessageRenderer m) {
		super(m);
		capabilities = new HashSet<Capability>();
		
	}


	
	

	@Override
	public int getHitpoints() {
		return hitpoints;
	}
	
	
	
	
	
	


}
