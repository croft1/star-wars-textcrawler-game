package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;
import starwars.entities.actors.Droid;

public class Repair extends SWAffordance {

	public Repair(SWEntityInterface theTarget, MessageRenderer m) {
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
	
		//If the Droid is still mobile

		if (target.getisImmobile() == false) {
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
					target.setisImmobile(false);
					
					//Set isDisassembled to false
					target.setisDisassembled(false);
					
					//Set allegience to repairing character
					target.setOwer(a);
					target.setTeam(a.getTeam());
					
					//Printing out ownership
					a.say(target.getShortDescription() + " has new owner: " + target.getOwner().getShortDescription());
					
					//Printing team affiliation
					a.say(target.getShortDescription() + " affiliation has changed to: " +  target.getTeam() );

					//Add an attack affordance to the repaired Droid, so it can be attacked again
					target.addAffordance(new Attack(target, this.messageRenderer));
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return "repair " + target.getShortDescription();
	}
}
