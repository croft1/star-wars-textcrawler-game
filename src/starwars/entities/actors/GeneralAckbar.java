package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.Mace;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

import java.util.ArrayList;

public class GeneralAckbar extends SWActor {

	private String name;

	public GeneralAckbar(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "General Ackbar";

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
		String says = " looks at you curiously";
		if (Math.random() >= 0.9){
			says += " then bellows: it's a Trap!";
		}

		return this.getShortDescription() + says;
	}

	

	
	//obeyMindControl will be called from the SWActor class, doesn't need to be customised within here
	
	

	
}
