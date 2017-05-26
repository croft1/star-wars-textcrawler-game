package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Fill;

/**
 * A Mace that can be used to contain water.
 * 


 * 
 * 
 * @author croft1
 */
public class Rake extends SWEntity {

	public Rake(MessageRenderer m)  {
		super(m);
		this.shortDescription = "a rake";
		this.longDescription = "a well used and sturdy rake for home maintainence";

		capabilities.add(Capability.HOME);
		
	}
}
