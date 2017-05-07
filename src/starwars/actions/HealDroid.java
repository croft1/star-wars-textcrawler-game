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
		return true;
	}

	@Override
	public void act(SWActor a) {
		//Target is the Droid that is going to be healed.
		SWActor target = (SWActor) this.getTarget();
		
		SWEntityInterface itemCarried = a.getItemCarried();
			if (itemCarried != null) {//if an item is carried
				if (itemCarried.getShortDescription() == "an oil can") {
					System.out.println(a.getShortDescription() + "has " + 
						itemCarried.getShortDescription() + ". Going to heal a Droid.");
					
					//Healing the target Droid
					System.out.println(target.getShortDescription());
					System.out.println(target.getShortDescription() + " current HP is: " + target.getHitpoints());
					System.out.println(target.getShortDescription() + " initial HP is: " + target.getInitialHP());
					
					
					
				}
				else {
					System.out.println(a.getShortDescription() + " is carrying " + a.getItemCarried().getShortDescription() 
							+ ". An oil can is required.");
				}
			} 
			
			else {
				System.out.println(a.getShortDescription() + " cannot heal " + target.getShortDescription() 
				 + ", no Oil Can held! ");
				
	
			}
	}

	@Override
	public String getDescription() {
		return "heal " + target.getShortDescription();
	}

}
