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
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.Droid;

/**
 * Class for TakeOwnership
 * 
 * The TakeOwnership  action enables SWActors (like the Player) to be
 * able to take ownership of neutral Droids  when they would like to. The 
 * Droid, once taken ownership of, will then follow the players every move,
 * essentially being their sidekick throughout the game!
 * 
 * @author jas
 * @author mewc
 *
 */
public class TakeOwnership extends SWAffordance implements SWActionInterface {

	
	/**
	 * Constructor for the <code>TakeOwnership</code> class. 
	 * 
	 * @param theTarget 	- the Droid being taken ownership of
	 * @param m 	- the message renderer to display messages
	 */
	public TakeOwnership(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>TakeOwnership</code> action.
	 * 
	 * @return The duration of the TakeOwnership action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	
	/**
	 * A String describing what this <code>TakeOwnership</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "taken ownership of " and the short description of the target Droid of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return "take ownership of " + this.target.getShortDescription();
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can take ownership of the target.
	 * 
	 * @author 	dsquire
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> can always try to take ownership of a Droid, 
	 * however if the Droid already has a owner or is immobile/disassembled, it wont do much!
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	
	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the TakeOwnership process once option is selected from the same menu.
	 * Depending on certain conditions (like if the Droid being queried is mobile, if they
	 * do not have a owner currently for example), this act() action will change the 
	 * action of the situation at hand.
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 *
	 */
	@Override
	public void act(SWActor a) {
		Droid target = (Droid) this.getTarget();

		if (target.getIsImmobile() == true) {
			//Removing the take ownership affordance of an immobile Droid. Whoever repairs the
			//Droid will gain its allegience!
			
		
			//Print out notification - cant take ownership of an immobile Droid
			a.say("Cant take ownership of " + target.getShortDescription() + ", who is \nimmobile. Need to"
					+ " Disassemble or Repair first.");
		}		//If a Droid has no owner
		else if ( target.getOwner() == null) {
			
			//Printing out notification of imminent ownership
			a.say(a.getShortDescription()  + " is to take ownership of " + target.getShortDescription());

			//Setting ownership & team affiliation
			target.setOwner(a);
			target.setTeam(a.getTeam());
			
			//Printing out ownership
			a.say(target.getShortDescription() + " has new owner: " + target.getOwner().getShortDescription());
			
			//Printing team affiliation
			a.say(target.getShortDescription() + " affiliation has changed to: " +  target.getTeam() );
			
			//Removing the take ownership affordance of the Droid (since you cant try to own
			// a Droid that you already own!
			target.removeAffordance(this);

		}
		else if (target.getOwner() != null) {
			
			//Printing out notification of prior ownership
			a.say(a.getShortDescription()  + " already owns " + target.getShortDescription() + "!");
		}	
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
