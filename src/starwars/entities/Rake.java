package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Fill;

/**
 * A canteen that can be used to contain water.
 * 
 * It can be filled at a Reservoir, or any other Entity
 * that has a Dip affordance.
 * 
 * Please note that drinking from the canteen is currently 
 * unimplemented
 * 
 * 
 * @author Robert Merkel
 * 
 */
public class Rake extends SWEntity {

	public Rake(MessageRenderer m)  {
		super(m);
		this.shortDescription = "a rake";
		this.longDescription = "a well used and sturdy rake for home maintainence";

		capabilities.add(Capability.HOME);
		
	}


	
	
}
