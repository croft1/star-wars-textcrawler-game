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
import starwars.actions.FlyToTatooine;
import starwars.actions.Take;

/**
 * Class for the Millenium Falcon SWEntity
 * 
 * The Millenium Falcon is the main usage of travel between the planets and destinations
 * in the Star Wars universe - mainly for the Resistance and New Republic in the main film
 * series and other various media. In this game, Luke will be able to use the Millenium Falcon
 * to travel in-between Tatooine, the Death Star and Yavin 4.
 * 
 * @author jas
 * @author mewc
 *
 */
public class MilleniumFalcon extends SWEntity {

	/**
	 * Constructor for the <code>Miilenium Flacon</code>. 
	 * 
	 * Creates the Millenium Falcon that will be denoted by a "^". Luke can use this spacecraft to travel
	 * inbetween worlds of this game (as well as his followers)
	 * 
	 * @param	m	- MessageRenderer that these Droid Parts will use to display notification
	 * to (mainly their description)
	 */
	public MilleniumFalcon(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "the Millennium Falcon";
		this.longDescription = "the Millennium Falcon, the Correlian YT-1300f light freighter/spacecraft";
		this.hitpoints = 100; 
	}
	
	/**
	 * A symbol that is used to represent the Millenium Falcon on a text based user interface
	 * 
	 * @return 	Single Character string "s"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public String getSymbol() {
		return "^";
	}
}
