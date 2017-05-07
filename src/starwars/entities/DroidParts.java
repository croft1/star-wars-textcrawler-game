package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.actions.Take;

public class DroidParts extends SWEntity {

	/**
	 * Constructor for the <code>Droid Parts</code>. 
	 */
	public DroidParts(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "some Droid Parts";
		this.longDescription = "some Droid parts, sourced from a immobile Droid. ";
		
		this.addAffordance(new Take(this, m));//add the Take affordance so that the Droid Parts can be picked up
	}
	
	
	/**
	 * A symbol that is used to represent the Droid Parts on a text based user interface
	 * 
	 * @return 	Single Character string "dp"
	 */
	public String getSymbol() {
		return "dp"; 
	}
}
