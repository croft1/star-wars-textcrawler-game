package starwars;

import java.util.HashSet;

import edu.monash.fit2099.simulator.matter.Entity;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;


/**
 * Class that represents inanimate objects in the Star Wars world that have a mysterious property to them: the force.. Objects that cannot move for example lightsabres.
 * 
 * @author 	croft1
 * @see 	edu.monash.fit2099.simulator.matter.Entity
 * @see 	SWEntityInterface
 */

public class SWForceEntity extends SWEntity implements SWForceEntityInterface {
	
	
	/**
	 * Constructor for this <code>SWForceEntity</code>. Will initialize this <code>SWForceEntity</code>'s
	 * <code>messageRenderer</code> and set of capabilities.
	 * 
	 * @param m the <code>messageRenderer</code> to display messages
	 */
	protected SWForceEntity(MessageRenderer m) {
		super(m);
		capabilities = new HashSet<Capability>();
		
	}


	
	
	
	
	
	
	


}
