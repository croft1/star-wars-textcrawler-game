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
	 * Create a Humanoid.  THey won't move and are weak minded
	 *
	 * 
	 * @param hitpoints
	 *            the number of hit points of this HJumanoid. If this
	 *            decreases to below zero, the Raider will die.
	 * @param name
	 *            this raider's name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>Humanoid</code> belongs to
	 * 
	 */
	public Humanoid(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		
		setItemCarried(new Rake(m));
		setWielding(true);
		this.addAffordance(new Obey(this, m));	//allow any humanoid to obey those with force
		
	}

	
	/**
	 **A humanoid doesn't move, so it doesn't need to do anything
	
	 * @author 	croft1
	 *
	 */
	@Override
	public void act() {
		
		
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
