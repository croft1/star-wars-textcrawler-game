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
import starwars.entities.actors.Droid;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;

/**
 * Class for HealDroid
 * 
 * The HealDroid action enables other SWActors (like the Player and Droid R2-D2) to be
 * able to heal other Droids (and themselves) when they would like to. The affiliated
 * SWActor who wants to heal needs to have a Oil Can to do so (minus R2-D2), who has an
 * internal Oil distributor!
 * 
 * @author jas
 * @author mewc
 *
 */
public class HealDroid extends SWAffordance {

	/**
	 * Constructor for HealDroid
	 * 
	 * @param 	theTarget	- SWEntityInterface that the heal will effect
	 * @param 	m	- MessageRenderer used for displaying messages to output.
	 *
	 */
	public HealDroid(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use HealDroid.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 * 
	 * @return 	- Boolean (true) that exclaims this actor can undertake the healing
	 * of a particular Droid
	 *
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the HealDroid process once option is selected from the same menu.
	 * (also preselected if R2-D2 comes across a Droid who needs healing).
	 * Depending on certain conditions (such as if the actor (a) is holding a oil can
	 * or if the target Droid is at full health) are checked in a logical order
	 * in the enabling of healing the target Droid.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake the process of 
	 * healing a Droid.
	 *
	 */
	@Override
	public void act(SWActor a) {
		//Target is the Droid that is going to be healed.

		Droid target = (Droid) this.getTarget();

		//If Luke is attempting to heal a Droid (i.e needs a oil can!)
		if (a.getSymbol() == "@") {
			if (target.getIsImmobile() == true) {
	
				//Print out notification - cant heal an immobile Droid.
				a.say("Cant heal " + target.getShortDescription() + ", who is \nimmobile. Need to"
						+ " Disassemble or Repair first.");
			}
			
			else {
				SWEntityInterface itemCarried = a.getItemCarried();
				if (itemCarried != null) {//if an item is carried
					if (itemCarried.getShortDescription() == "an oil can") {
						//Healing process
						
						//If a player tries to heal a full HP NPC
						if(target.getHitpoints() == target.getInitialHP()) {
							a.say(target.getShortDescription() + " is at maximum HP!");
						}
						
						else {
							//Printout of healing attempt
							a.say("Healing " + target.getShortDescription() + " with "
									+ "an oil can of capacity " + itemCarried.getHitpoints() + "." );
							
							//Calculating depleted HP
							int depletedHealth = target.getInitialHP() - target.getHitpoints();
							a.say(target.getShortDescription() + " has so far"
									+ " lost a total of " + depletedHealth + " HP." );
							
							//Checking if the oil can isnt empty
							if (itemCarried.getHitpoints() > 0) {
								int newHitpoints;	//Defining newHitpoints for the target. Will change in two scenarios as below
								
								//If the depleted health is MORE than the current capacity of the oil can
								if (depletedHealth > itemCarried.getHitpoints()) {
									//Setting newHitpoints for the target
									newHitpoints = target.getHitpoints() + itemCarried.getHitpoints();
									
									//Deplete the oil can
									itemCarried.takeDamage(itemCarried.getHitpoints());
									
									//Set the new HP to the target
									target.setHitpoints(newHitpoints);
									
									//Print out to game
									a.say(itemCarried.getShortDescription() + " is now "
											+ " empty: capacity of " + itemCarried.getHitpoints());
									a.say(target.getShortDescription()+ " has healed"
											+ " to " + target.getHitpoints() + "HP");
									
								}
								//Otherwise the oil can CAN completely heal the target
								else { 
									target.setHitpoints(target.getInitialHP());
									
									//Deplete the oil can
									itemCarried.takeDamage(depletedHealth);
								
									//Print out to game
									a.say(itemCarried.getShortDescription() + " now has "
											+ "capacity of " + itemCarried.getHitpoints());
									a.say(target.getShortDescription()+ " has healed"
											+ " to " + target.getHitpoints() + "HP");
									
								}
								
							} 
							
							//Else if the oil can is completely used.
							else {
								a.say("Cannot heal " + target.getShortDescription() + " with "
										+ " an empty oil can!");
							}
								
							
							
						}
						
						
						
					}
					else {
						a.say(a.getShortDescription() + " is carrying " + a.getItemCarried().getShortDescription() 
								+ ". An oil can is required.");
					}
				} 
				
				else {
					a.say(a.getShortDescription() + " cannot heal " + target.getShortDescription() 
					 + ", no Oil Can held! ");
				}
				
				
			}
		}
		
		//If R2-D2 comes accross a Droid
		if (a.getSymbol() == "R2") {
			
			
			if (a.getSymbol() == target.getSymbol()) {
				
				//R2 only heals himself when he is at half health
				if(a.getHitpoints() < (a.getInitialHP()/2)) {
					a.setHitpoints(a.getInitialHP());
					a.say("R2-D2 has healed himself to " + a.getHitpoints() + "HP");
				}
			}
			
			else 
			{
				if (target.getIsImmobile() == true) 
				{
					//Print out notification - cant heal an immobile Droid.
					a.say("Cant heal " + target.getShortDescription() + ", who is \nimmobile. Need to"
					+ " Disassemble or Repair first.");
				}
				
				else 
				{
					//If a player tries to heal a full HP NPC
					if(target.getHitpoints() == target.getInitialHP()) 
					{
						a.say(target.getShortDescription() + " is at maximum HP!");
					}
					
					else 
					{
						//Otherwise the oil can CAN completely heal the target
						target.setHitpoints(target.getInitialHP());
												
						//Print out to game
						a.say("R2-D2 has healed " + 
						target.getShortDescription()
						+ " to " + target.getHitpoints() + "HP");
							
					}
				}				
			}
			
		}
	}
		
		
	/**
	 * public method getDescription()
	 * 
	 * Returns a string description of HealDroid. Used when showing the player they 
	 * are able to complete this action selected.
	 * 
	 * @return	-	String of action - implemented in game selection options.
	 *
	 */
	@Override
	public String getDescription() {
		return "heal " + target.getShortDescription();
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
