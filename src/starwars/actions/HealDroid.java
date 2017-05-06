package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
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
		return false;
	}

	@Override
	public void act(SWActor a) {
		// TODO Auto-generated method stub
		System.out.println("Healing a Droid here");
		
	}

	@Override
	public String getDescription() {
		return "heal party member " + target.getShortDescription();
	}

}
