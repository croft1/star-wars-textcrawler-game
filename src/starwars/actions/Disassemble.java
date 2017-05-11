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

public class Disassemble extends SWAffordance {

	public Disassemble(SWEntityInterface theTarget, MessageRenderer m) {
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

	@Override
	public String getDescription() {
		return "disassemble " + target.getShortDescription();
	}

}
