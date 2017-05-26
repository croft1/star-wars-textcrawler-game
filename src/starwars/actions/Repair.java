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
import starwars.entities.actors.Droid;

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
public class Repair extends SWAffordance {

	/**
	 * Constructor for the <code>Repair</code> class. 
	 * 
	 * @param theTarget 	- the Droid being repaired (SWEntityInterface)
	 * @param m 	- the message renderer to display messages
	 */
	public Repair(SWEntityInterface theTarget, MessageRenderer m) {
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
		//Target is the Droid that is going to be healed.
		Droid target = (Droid) this.getTarget();
	
		//If the Droid is still mobile

		//If the entity trying to disassemble is Luke 
		if (a.getSymbol() == "@") {
			
			if (target.getIsImmobile() == false) {
				a.say("Cant repair a mobile Droid!");
	
			}
			
			//Otherwise, attempt to repair a Droid
			else {
				
				//If the player has no items
				if (a.getItemCarried() == null) {
					a.say(a.getShortDescription() + " has no items held." + 
						("\nObtain Droid Parts to repair this Droid."));
				}
				
				else {
					//If the item is not Droid Parts
					if(a.getItemCarried().getSymbol() != "dp") {
						a.say(a.getShortDescription() + " is holding " + 
								a.getItemCarried().getShortDescription() + "\nObtain Droid Parts to repair this Droid.");
					}
					
					//Otherwise, Droid Parts are held. Repair the immobile & disassembled Droid
					else {
								
						//Remove the Droid Parts from the Actors' inventory
						a.setItemCarried(null);
						
						//Make Droids' health back to max HP
						target.setHitpoints(target.getInitialHP());
						
						//Printout of repair 
						a.say(target.getShortDescription() + " was repaired by " + a.getShortDescription()
						+ ".\n" + target.getShortDescription() + " now has " + target.getHitpoints() + " HP.");
						
						//Set isImmobile to false
						target.setIsImmobile(false);
						
						//Set isDisassembled to false
						target.setIsDisassembled(false);
						
						//Set allegience to repairing character
						target.setOwner(a);
						target.setTeam(a.getTeam());
						
						//Printing out ownership
						a.say(target.getShortDescription() + " has new owner: " + target.getOwner().getShortDescription());
						
						//Printing team affiliation
						a.say(target.getShortDescription() + " affiliation has changed to: " +  target.getTeam() );
	
						//Adding the Droid to the SWActor a's following ArrayList
						a.getFollowerList().add(target.getSymbol());
						a.getFollowerListSWActors().add(target);
						
						//Printing notification of addition to follow list
						a.say(target.getShortDescription() + " was added to " +  a.getShortDescription() + "'s follow list." );
						
						//Add an attack affordance to the repaired Droid, so it can be attacked again
						target.addAffordance(new Attack(target, this.messageRenderer));
					}
				}
			}
		}
		
		//If the entity trying to disassemble is R2D2 
				if (a.getSymbol() == "R2") {
					
					if (target.getIsImmobile() == false) {
						a.say("Cant repair a mobile Droid!");
			
					}
					
					//Otherwise, attempt to repair a Droid
					else {
						
						//If the player has no items
						if (a.getItemCarried() == null) {
							a.say(a.getShortDescription() + " has no items held." + 
								("\nObtain Droid Parts to repair this Droid."));
						}
						
						else {
							//If the item is not Droid Parts
							if(a.getItemCarried().getSymbol() != "dp") {
								a.say(a.getShortDescription() + " is holding " + 
										a.getItemCarried().getShortDescription() + "\nObtain Droid Parts to repair this Droid.");
							}
							
							//Otherwise, Droid Parts are held. Repair the immobile & disassembled Droid
							else {
										
								//Remove the Droid Parts from the Actors' inventory
								a.setItemCarried(null);
								
								//Make Droids' health back to max HP
								target.setHitpoints(target.getInitialHP());
								
								//Printout of repair 
								a.say(target.getShortDescription() + " was repaired by " + a.getShortDescription()
								+ ".\n" + target.getShortDescription() + " now has " + target.getHitpoints() + " HP.");
								
								//Set isImmobile to false
								target.setIsImmobile(false);
								
								//Set isDisassembled to false
								target.setIsDisassembled(false);
								
								//Add an attack affordance to the repaired Droid, so it can be attacked again
								target.addAffordance(new Attack(target, this.messageRenderer));
							}
						}
					}
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
		return "repair " + target.getShortDescription();
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