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
		
		SWEntityInterface itemCarried = a.getItemCarried();
	
	}

	@Override
	public String getDescription() {
		return "repair " + target.getShortDescription();
	}

}
