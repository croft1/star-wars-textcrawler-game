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

/**
 * Class for General Ackbar SWActors
 * 
 * A Humanoid character who resides on Tatooine. A general of the Rebel forces!
 * 
 * @author jas
 * @author mewc
 */
public class GeneralAckbar extends SWActor {

	//Name private variable
	private String name;

	/**
	 * General Ackbar constructor
	 * 
	 * Implements a new instance of General Ackbar into the world - placed into the 
	 * current world defined in its parameters.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that General Ackbar will be placed in
	 */
	public GeneralAckbar(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "General Ackbar";

	}

	/**
	 * act() method for General Ackbar
	 * 
	 * Implements the actions defined that will be taken by this Genral Ackbar instance.
	 * 
	 * General Ackbar , along with Mon Mothma, will be waiting at the moon Yavin IV for their
	 * General (Leia) - which wins the game. If Luke visits the moon before Leia and R2-D2 is
	 * followed, Ackbar has a 10% of yelling uncontrollably!
	 *  
	 */
	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		say(describeLocation());
	}

	/**
	 * getShortDescription() method
	 * 
	 * Returns the name of this SWActor (Ackbar), which is a String.
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getShortDescription() {
		return name;
	}

	/**
	 * getLongDescription() method
	 * 
	 * Returns the name of this SWActor (Ackbar), which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	//Private function describeLocation()
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		String says = " looks at you curiously";
		if (Math.random() >= 0.9){
			says += " then bellows: it's a Trap!";
		}

		return this.getShortDescription() + says;
	}
}
