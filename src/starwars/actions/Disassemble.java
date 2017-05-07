package starwars.actions;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.entities.Fillable;

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
		SWActor target = (SWActor) this.getTarget();
		
		SWEntityInterface itemCarried = a.getItemCarried();
		
		//If the entity trying to disassemble is Luke 
		if (a.getSymbol() == "@") {
			System.out.println(a.getShortDescription() + " is trying to disassemble " + 
		target.getShortDescription() + " ,\nwho is at " + target.getHitpoints() + " HP.");
			
			//if an item is carried
			if (itemCarried != null) {
				System.out.println(a.getShortDescription() + " already is carrying  " + 
						a.getItemCarried().getShortDescription() + ".\nLeave this item before disassembling. ");
			
			//Otherwise, disassemble into Droid Parts
			} else {
				entityManager.setLocation((SWEntityInterface)target, entityManager.whereIs(a));
				
				
				/*
				 	public void act(SWActor a) {
						if (a.getItemCarried() == null) { // the actor is not holding something
															// This should really throw an exception, but let's just use a message for now.
							a.say("Leave affordance called by actor that is not holding anything. This should never happen");
		}
						else {
							// put the item in the actor's location
							if (target instanceof SWEntityInterface) {
								EntityManager<SWEntityInterface, SWLocation> entityManager = SWAction.getEntitymanager();
								entityManager.setLocation((SWEntityInterface)target, entityManager.whereIs(a));
								a.setItemCarried(null);
								target.removeAffordance(this);
								target.addAffordance(new Take((SWEntityInterface)target, this.messageRenderer)); // add a Take affordance
							}
						}
					} 
				  
				  
				  */				
				
			}
			
			
		}
		
		
		
	}

	@Override
	public String getDescription() {
		return "disassemble " + target.getShortDescription();
	}

}
