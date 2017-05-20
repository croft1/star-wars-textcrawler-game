/**
 * starwars.entities package
 * 
 * Initiates entities that will be able to be usable by SWActors in the Star Wars
 * roguelike game. This includes items like a LightSaber, Blaster, Water Canteen and
 * so forth!
 *
 */

package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Fly;
import starwars.actions.Take;

/**
 * Class for the Millenium Falcon SWEntity
 * 
 * The Millenium Falcon is the main usage of travel between the planets and destinations
 * in the Star Wars universe - mainly for the Resistance and New Repulic in the main film
 * series and other various media. In this game, Luke will be able to use the Millenium Falcon
 * to travel to the Death Star and Yavin 4 moon.
 * 
 * @author jas
 * @author mewc
 *
 */
public class MilleniumFalcon extends SWEntity {

	/**
	 * Constructor for the <code>Miilenium Flacon</code>. 
	 * 
	 * Creates the Millenium Falcon that will be denoted by a "MF". Luke can use this spacefract to travel
	 * inbetween worlds of this game (
	 * 
	 * @param	m	- MessageRenderer that these Droid Parts will use to display notification
	 * to (mainly their description)
	 */
	public MilleniumFalcon(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "the Millenium Falcon";
		this.longDescription = "the Millenimu Falcon, the Correlian YT-1300f light freighter/spacecraft";
		this.hitpoints = 100; // start with a fully charged pistol

		this.addAffordance(new Fly(this, m));//add the Fly affordance so that Luke can use the Millenium Falcon to intertravel
		//destinations
	}
	
	
	/**
	 * A symbol that is used to represent the Millenium Falcon on a text based user interface
	 * 
	 * @return 	Single Character string "s"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public String getSymbol() {
		return "MF"; 
	}
}
