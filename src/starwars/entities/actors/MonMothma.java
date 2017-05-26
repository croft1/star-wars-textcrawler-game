package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;


/**
 * Class for Mon Mothma SWActors
 * 
 * A Humanoid character who resides on Tatooine.
 * 
 * @author jas
 * @author mewc
 */
public class MonMothma extends SWActor {

	//Private String variable name
	private String name;

	/**
	 * General Ackbar Mon Mothma
	 * 
	 * Implements a new instance of Mon Mothma into the world - placed into the 
	 * current world defined in its parameters.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that Mon Mothma will be placed in
	 */
	public MonMothma(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "Mon Mothma";

	}

	/**
	 * act() method for Mon Mothma General Ackbar
	 * 
	 * Implements the actions defined that will be taken by this Mon Mothma instance.
	 * 
	 * Mon Mothma, along with Genral Ackbar, will be waiting at the moon Yavin IV for their
	 * General (Leia) - which wins the game. If Luke visits the moon before Leia and R2-D2 is
	 * followed, Mothma will comment to the 'farmboy' to bring back Leia as soon as possible!
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
	 * Returns the name of this SWActor (Mon Mothma), which is a String.
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
	 * Returns the name of this SWActor (Mon Mothma), which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	//Private describeLocation() method
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		Boolean winConditionMet = false;
		String says = " says: 'What are you doing here, farmboy!? Bring us \nGeneral Organa (Princess Leia) and the plans!'";
		if (winConditionMet){
			says += " YOU WIN!";
		}

		return this.getShortDescription() + says;
	}
}
