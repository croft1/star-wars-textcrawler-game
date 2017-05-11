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
import starwars.entities.Canteen;
import starwars.entities.Fillable;

/**
 * Class for HealDroid
 * 
 * The HealPlayer action enables SWActors (like the Player and Ben Kenobi) to be
 * able to heal themselves when they would like to. The affiliated
 * SWActor who wants to heal needs to have a Water Canteen with a level >0
 * to do so. Otherwise it will need to be filled at the Moisture Farms.
 * 
 * @author jas
 * @author mewc
 *
 */
public class HealPlayer extends SWAffordance {

	/**
	 * Constructor for HealPlayer
	 * 
	 * @param 	theTarget	- SWEntityInterface that the heal will effect
	 * @param 	m	- MessageRenderer used for displaying messages to ouput.
	 *
	 */
	public HealPlayer(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use HealPlayer.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 * 
	 * @return 	- Boolean (true) that exclaims this actor can undertake HealPlayer
	 *
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}
	
	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the HealPlayer process once option is selected from the same menu.
	 * Depending on certain conditions (such as if the actor (a) is holding a water
	 * canteen, if the actor is at full health etc) are checked in a logical order
	 * in the enabling of healing.
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 *
	 */
	@Override
	public void act(SWActor a) {
		
		//If the actor has the water canteen
		if (a.getItemCarried().getSymbol() == "o") {
			
			//Set the canteen to carriedCanteen
			Canteen carriedCanteen = (Canteen) a.getItemCarried();
			
			//If the canteens' level is ABOVE 0 (i.e has water in it
			if (carriedCanteen.getLevel() > 0) {
				
				//If the Actor is at full health
				if (a.getInitialHP() == a.getHitpoints()) {
					//Printout
					a.say("Cannot heal. " + a.getShortDescription() +
							"is already at full HP!.");
				}	
				
				//Else heal the actor
				else {
					//Printout
					a.say("Healing " + a.getShortDescription() + "...");
					
					//Get actors' distance to max HP
					int disttoFullHP = a.getInitialHP() - a.getHitpoints();
					
					//If the actors' distance to full HP is less than 10, restore all health
					if (disttoFullHP < 10) {
						
						//Set HP to full, since water canteen can fully heal
						a.setHitpoints(a.getInitialHP());
						
						//Decrement level of canteen by 1 (1 use)
						carriedCanteen.setLevel(carriedCanteen.getLevel() - 1);
						
						//Print message
						a.say(a.getShortDescription() + " used the canteen (+10HP)."
								+ "\n" + a.getShortDescription() + " HP: " + a.getHitpoints() + 
								"\nThe canteen has " + carriedCanteen.getLevel() + " use(s) remaining.");
					}
					
					//Otherwise, heal 10 HP
					else {
						
						//Heal 10HP of actor
						a.setHitpoints(a.getHitpoints() + 10);
						
						//Decrement level of canteen by 1 (1 use)
						carriedCanteen.setLevel(carriedCanteen.getLevel() - 1);
						
						//Print message
						a.say(a.getShortDescription() + " used the canteen."
								+ "\n" + a.getShortDescription() + " HP: " + a.getHitpoints() + 
								"\nThe canteen has " + carriedCanteen.getLevel() + " use(s) remaining.");
					}
				}	
			} 
			
			//Otherwise the canteen is empty
			else {
				a.say("Cannot heal. " + a.getShortDescription() + " is "
						+ "trying to heal \nwith a empty water canteen. Refill first.");
			}	
		}
		
		
		
	}
	
	/**
	 * public method getDescription()
	 * 
	 * Returns a string description of HealPLayer. Used when showing the player they 
	 * are able to complete this action
	 * 
	 * @return	-	String of action - implemented in game selection options.
	 *
	 */
	@Override
	public String getDescription() {
		return "heal player: " + target.getShortDescription();
	}

}
