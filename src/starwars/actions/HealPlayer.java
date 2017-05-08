package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.Canteen;
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
						a.say(a.getShortDescription() + " used the canteen."
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
	@Override
	public String getDescription() {
		return "heal player: " + target.getShortDescription();
	}

}
