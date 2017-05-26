/**
 * starwars.actions package
 * 
 * Initiates actions that will be able to be initiated by SWActors in the Star Wars
 * roguelike game. This includes actions like Obey (the Force), TakeOwnership (of Droids),
 * Leave (an item) and so forth!
 *
 */
package starwars.actions;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.entities.Blaster;
import starwars.entities.DroidParts;
import starwars.entities.Fillable;
import starwars.entities.actors.Droid;

/**
 * Class for Disassemble
 * 
 * The Disassemble action enables SWActors (like the Player and Droid R2-D2) to be
 * able to disassemble immobile Droids into Droid Parts. The Droid will be left on the map -
 * and some Droid Parts will be left next to the SWActor in the need of Repairing the 
 * Droid back to usable condition!
 * 
 * @author jas
 * @author mewc
 *
 */
public class Disassemble extends SWAffordance {

	/**
	 * Constructor for the <code>Disassemble</code> class. 
	 * 
	 * @param theTarget 	- the Droid being disassembled (SWEntityInterface)
	 * @param m 	- the message renderer to display messages
	 */
	public Disassemble(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use Disassemble.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 * 
	 * @return 	- Boolean (true) that exclaims this actor can undertake the disassembly
	 * of Droids into Droid Parts
	 *
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the Disassemble process once option is selected from the same menu.
	 * (also preselected if R2-D2 comes across a Droid who isn't disassembled).
	 * Depending on certain conditions being met (such as if the Droid is not already disassembled) 
	 * - the Droid will be disassembled into parts which the SWActor can use in 
	 * repairing said Droid (or other immoble Droids for that matter).
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake the process of 
	 * healing a Droid.
	 *
	 */
	@Override
	public void act(SWActor a) {
		//Target is the Droid that is going to be healed.
		Droid target = (Droid) this.getTarget();

		//SWEntityInterface itemCarried = a.getItemCarried();
		
		//If the entity trying to disassemble is Luke 
		if (a.getSymbol() == "@") {
			a.say(a.getShortDescription() + " is trying to disassemble " + 
		target.getShortDescription() + " ,\nwho is at " + target.getHitpoints() + " HP.");
	
			//If5 a Droid is still mobile

			if (target.getIsImmobile() == false) {
				a.say("Cant disassemble a mobile Droid!");

			}
			
			//Otherwise, disassemble into Droid Parts
			else {
				
				//If the Droid has already been disassembled

				if (target.getIsDisassembled() == true) {
					a.say(target.getShortDescription() + " has already been \ndisassembled into Droid Parts.");

				} 
				
				//Otherwise, create Droid Parts
				else {
					
					//Call in Entity Manager (for handling SWEntities)
					EntityManager<SWEntityInterface, SWLocation> entityManager = SWAction.getEntitymanager();
					
					//Create new Droid Parts
					DroidParts droidPImm = new DroidParts(this.messageRenderer);
					
					//Place created Droid Parts in the world
					entityManager.setLocation(droidPImm, entityManager.whereIs(target));
					
					//Set the immoble Droid to disassembled
					target.setIsDisassembled(true);
	
				}
			}			
		}
		
		//If the entity trying to disassemble is R2D2 
		if (a.getSymbol() == "R2") {
			a.say(a.getShortDescription() + " is trying to disassemble " + 
					target.getShortDescription() + " ,\nwho is at " + target.getHitpoints() + " HP.");
			
			if (target.getIsImmobile() == false) {
				a.say("Cant disassemble a mobile Droid!");

			}
			
			//Otherwise, disassemble into Droid Parts
			else {
				
				//If the Droid has already been disassembled

				if (target.getIsDisassembled() == true) {
					a.say(target.getShortDescription() + " has already been \ndisassembled into Droid Parts.");

				} 
				
				//Otherwise, create Droid Parts
				else {
					
					//Call in Entity Manager (for handling SWEntities)
					EntityManager<SWEntityInterface, SWLocation> entityManager = SWAction.getEntitymanager();
					
					//Create new Droid Parts
					DroidParts droidPImm = new DroidParts(this.messageRenderer);
					
					//Set the immoble Droid to disassembled
					target.setIsDisassembled(true);
					
					//If R2 is carrying droid Parts, leave them. else take them!
					if (a.getItemCarried() != null) {
						a.say(a.getShortDescription() + "is already holding an item. Cannot\ntake the Droid Parts!");
						return;
					}
					else
					{
						a.say(a.getShortDescription() + " took the Droid Parts from the \nDroid he disassembled.");
						a.setItemCarried(droidPImm);
					}
	
				}
			}			
		}
	}

	/**
	 * public method getDescription()
	 * 
	 * Returns a string description of Disassemble. Used when showing the player they 
	 * are able to complete this action selected.
	 * 
	 * @return	-	String of action - implemented in game selection options.
	 *
	 */
	@Override
	public String getDescription() {
		return "disassemble " + target.getShortDescription();
	}

}

/*
REFERENCES

Javatpoint 2017, Java Switch Statement, viewed 10 May 2017,
https://www.javatpoint.com/java-switch 

Javin, P 2017, How to find length/size of ArrayList in Java? Example, Java67, viewed 22 May 2017,
http://www.java67.com/2016/07/how-to-find-length-size-of-arraylist-in-java.html

Stack Overflow 2011, Creating a new ArrayList in Java, viewed 22 May 2017,
https://stackoverflow.com/questions/5915892/creating-a-new-arraylist-in-java

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2012, How to remove first and last character of a string?, viewed 22 May 2017,
https://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

Stack Overflow 2010, Java ArrayList Index, viewed 22 May 2017,
https://stackoverflow.com/questions/4313457/java-arraylist-index

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

*/