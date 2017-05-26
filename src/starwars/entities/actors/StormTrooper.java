package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.Blaster;
import starwars.entities.Mace;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

import java.util.ArrayList;

/**
 * Class for Stormtroopers
 * 
 * A soldier of the Empire. Will protect Vader at all costs - will try to call for backup if needed
 * also, which can be a problem for the player!
 * 
 * @author jas
 * @author mewc
 */
public class StormTrooper extends SWActor {

	//Private name variable - String of the name of this 
	private String name;
    private static int cloneNumber = 1471;
   
    /**
	 * Constructor for a <code>StormTrooper</code> soldier. They are of team EVIL, and wander about on the Death
	 * Star in search for Luke and compatriots.
	 * 
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>StormTrooper</code> belongs to
	 * 
	 */
	public StormTrooper(MessageRenderer m, SWWorld world) {
		super(Team.EVIL, 100, m, world);
		// TODO Auto-generated constructor stub
		this.name = "#" + getNextCloneNumber();
		setItemCarried(new Blaster(m));
	}
	
	/**
	 * act() method for <code>StormTrooper</code>
	 * 
	 * Implements the <code>StormTrooper</code> actions - which is extremely similar to Tusken Raiders. If they
	 * come across an enemy, they will have a 75% chance of hitting their enemy - as these 
	 * soldiers are known to be springy and sometimes take actions that would not be suitable for
	 * a situation!
	 * 
	 * <code>StormTrooper</code> can also call for backup - which will create a new <code>StormTrooper</code>
	 * in the same map - who can also call for backup!
	 * 
	 */
	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		say(describeLocation());

		AttackInformation attack = AttackNeighbours.attackLocals(this, this.world, true, true);

		//75% chance to miss
		if (attack != null ) {
			if(Math.random() > 0.74 ){
				say(getShortDescription() + " has attacked " + attack.entity.getShortDescription());
				scheduler.schedule(attack.affordance, this, 1);
			} else{
				say(getShortDescription() + " shoots wildly");
			}
		}else if (Math.random() > 0.94) {
			//TODO radio for backup

		}else{
			
			randomMovement();
		}
	}

	/**
	 * getShortDescription() method for <code>StormTrooper</code>
	 * 
	 * Returns the short string description of a <code>StormTrooper</code>
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
	@Override
	public String getShortDescription() {
		return name + " Storm Trooper";
	}

	/**
	 * getLongDescription() method for <code>StormTrooper</code>
	 * 
	 * Returns the long string description of a <code>StormTrooper</code>, calling the getShortDescription() method
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	/**
	 * getSymbol() method for <code>StormTrooper</code>
	 * 
	 * Returns the character symbol a <code>StormTrooper</code>.
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
    @Override
    public String getSymbol() {
        return "S";
    }
    
    //Private method describeLocation()
  	private String describeLocation() {
  		SWLocation location = this.world.getEntityManager().whereIs(this);
  		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

  	}

  	//Private method nextNextCloneNumber()
    private int getNextCloneNumber(){
	    cloneNumber ++;
	    return cloneNumber;
    }
}
