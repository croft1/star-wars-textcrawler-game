package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;

public class MonMothma extends SWActor {

	private String name;

	public MonMothma(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "Mon Mothma";

	}

	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		say(describeLocation());

	}

	@Override
	public String getShortDescription() {
		return name;
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		Boolean winConditionMet = false;
		String says = " says: 'What are you doing here, farmboy!? Bring us \nGeneral Organa (Princess Leia) and the plans!'";
		if (winConditionMet){
			says += " YOU WIN!";
		}

		return this.getShortDescription() + says;
	}

	

	
	//obeyMindControl will be called from the SWActor class, doesn't need to be customised within here
	
	

	
}
