/**
 * starwars.entities package
 * 
 * Initiates entities that will be able to be usable by actors in the Star Wats
 * roguelike game. This includes items like a LightSaber, Blaster, Water Canteen and
 * so forth!
 *
 */

package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.actions.Take;

/**
 * Class for Droid SWActors  
 * 
 * Droid Parts are used by SWActors in the need of repairing immobile Droids.
 * Droid Parts are consumed when used - so be wary which Droid you would like to
 * bring back to mobility!
 * 
 * @author jas
 * @author mewc
 *
 */
public class DroidParts extends SWEntity {

	/**
	 * Constructor for the <code>Droid Parts</code>. 
	 * 
	 * Creates some new DroidParts with predefined short and long descriptions. Also
	 * initiates a Take affordance (these can be taken by SWActors!
	 * 
	 * @param	m	- MessageRenderer that these Droid Parts will use to display notification
	 * to (mainly their description)
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
