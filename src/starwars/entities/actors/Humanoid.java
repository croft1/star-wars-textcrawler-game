package starwars.entities.actors;

import java.util.ArrayList;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.actions.Obey;
import starwars.entities.Mace;
import starwars.entities.Rake;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

public class Humanoid extends SWActor {

	private String name;

	/**
	 * Create a Tusken Raider.  Tusken Raiders will randomly wander
	 * around the playfield (on any given turn, there is a 50% probability
	 * that they will move) and attack anything they can (if they can attack
	 * something, they will).  They 
	 * are all members of team TUSKEN, so their attempts to attack
	 * other Tusken Raiders won't be effectual.
	 * 
	 * @param hitpoints
	 *            the number of hit points of this Tusken Raider. If this
	 *            decreases to below zero, the Raider will die.
	 * @param name
	 *            this raider's name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>TuskenRaider</code> belongs to
	 * 
	 */
	public Humanoid(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		
		setItemCarried(new Rake(m));
		this.addAffordance(new Obey(this, m));	//allow any humanoid to obey those with force
		
	}

	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		
		
	}

	@Override
	public String getShortDescription() {
		return shortDescription ;
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription() + " the regular Humanoid";
	}

	

	

	
	//obeyMindControl will be called from the SWActor class, doesn't need to be customised within here
	
	

	
}
