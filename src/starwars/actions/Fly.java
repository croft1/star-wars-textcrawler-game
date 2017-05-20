/**
 * starwars.actions package
 * 
 * Initiates actions that will be able to be initiated by SWActors in the Star Wars
 * roguelike game. This includes actions like Obey (the Force), TakeOwnership (of Droids),
 * Leave (an item) and so forth!
 *
 */
package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;
import starwars.entities.MilleniumFalcon;
import starwars.entities.actors.Droid;
import starwars.entities.actors.Player;

/**
 * Class for Repair
 * 
 * The Disassemble action enables SWActors (like the Player and Droid R2-D2) to be
 * able to repair immobile and Disassembled Droids into Droid Parts into working and mobile
 * condition. The Droid Parts used for the repair will be consumed - with the Droid 
 * repaired now taking on their repairer as their owner - changing their allegience to their
 * side and following their movements (minus R2-D2).
 * 
 * @author jas
 * @author mewc
 *
 */
public class Fly extends SWAffordance {

	/**
	 * Constructor for the <code>Repair</code> class. 
	 * 
	 * @param theTarget 	- the Droid being repaired (SWEntityInterface)
	 * @param m 	- the message renderer to display messages
	 */
	public Fly(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use Repair
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 * 
	 * @return 	- Boolean (true) that exclaims this actor can undertake the repair
	 * of Droids 
	 *
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}
	
	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the Repair process once option is selected from the same menu.
	 * (also preselected if R2-D2 comes across a Droid who isn't repaired).
	 * Depending on certain conditions being met (such as if the Droid is not mobile etc) 
	 * - the Droid will be repaired back to gull health and able to roam around the
	 * SWWorld once again. The Droid will change allegience and follow their 
	 * new owner (repairer), until the destruction of the Droid happens again.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake the process of 
	 * repairing a Droid.
	 *
	 */
	@Override
	public void act(SWActor a) {
		//If the entity trying to fly is Luke
		if (a instanceof Player) {

		}
		
	}

	/**
	 * public method getDescription()
	 * 
	 * Returns a string description of Repair. Used when showing the player they 
	 * are able to complete this action selected.
	 * 
	 * @return	-	String of action - implemented in game selection options.
	 *
	 */
	@Override
	public String getDescription() {
		return "fly in the " + target.getShortDescription();
	}
}

/*
REFERENCES

Javatpoint 2017, Java Switch Statement, viewed 10 May 2017,
https://www.javatpoint.com/java-switch 

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

*/
