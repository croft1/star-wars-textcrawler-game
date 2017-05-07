package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;

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
		SWActor target = (SWActor) this.getTarget();
	
		//If the Droid is still mobile

		if (target.getisImmobile() == false) {
			System.out.println("Cant repair a mobile Droid!");
		}
		
		//Otherwise, attempt to repair a Droid
		else {
			
			//If the player has no items
			if (a.getItemCarried() == null) {
				System.out.println(a.getShortDescription() + " has no items held." + 
					("\nObtain Droid Parts to repair this Droid."));
			}
			
			else {
				//If the item is not Droid Parts
				if(a.getItemCarried().getSymbol() != "dp") {
					System.out.println(a.getShortDescription() + " is holding " + 
							a.getItemCarried().getShortDescription() + "\nObtain Droid Parts to repair this Droid.");
				}
				
				//Otherwise, Droid Parts are held. Repair the immobile & disassembled Droid
				else {
					System.out.println("Due to heal Droid!");
				}
				
			}
			
			
		}
	
	}

	@Override
	public String getDescription() {
		return "repair " + target.getShortDescription();
	}

}
