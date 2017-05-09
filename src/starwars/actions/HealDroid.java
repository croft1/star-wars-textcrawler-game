package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.entities.actors.Droid;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;

public class HealDroid extends SWAffordance {

	public HealDroid(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	@Override
	public void act(SWActor a) {
		//Target is the Droid that is going to be healed.

		Droid target = (Droid) this.getTarget();

		if (target.getisImmobile() == true) {

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
		
		

	@Override
	public String getDescription() {
		return "heal " + target.getShortDescription();
	}

}
