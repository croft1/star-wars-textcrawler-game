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
	 * act() method for Humanoid Characters
	 * 
	 * Implements the actions defined that will be taken by this Humanoid instance.
	 * 
	 * Humanoids dont need to do anything apart from display messages - which essentially
	 * means no movement!  
	 */
	@Override
	public void act() {
		
	}
	
	/**
	 * getShortDescription() method
	 * 
	 * Returns the name of this Humanoid, which is a String.
	 * 
	 * @return	name	The name of this Humanoid, as a String 
	 */
	@Override
	public String getShortDescription() {
		return shortDescription ;
	}

	/**
	 * getLongDescription() method
	 * 
	 * Returns the name of this Humanoid, which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this Humanoid, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription() + " the regular Humanoid";
	}
}
