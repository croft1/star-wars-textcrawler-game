package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Fillable;

public class HealPlayer extends SWAffordance {

	public HealPlayer(SWEntityInterface theTarget, MessageRenderer m) {
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
		
		//If the player has the water canteen
		if (a.getItemCarried().getSymbol() == "o") {
			
			System.out.println("canteen HP " + a.getItemCarried().getHitpoints());
			
			
		}
		
		
		
	}
	@Override
	public String getDescription() {
		return "heal player: " + target.getShortDescription();
	}

}
