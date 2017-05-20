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

public class StormTrooper extends SWActor {

	private String name;
    private static int cloneNumber = 1471;
	/**
	 * Create a Tusken Raider.  Tusken Raiders will randomly wander
	 * around the playfield (on any given turn, there is a 50% probability
	 * that they will move) and attack anything they can (if they can attack
	 * something, they will).  They
	 * are all members of team TUSKEN, so their attempts to attack
	 * other Tusken Raiders won't be effectual.
	 *
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>TuskenRaider</code> belongs to
	 *
	 */
	public StormTrooper(MessageRenderer m, SWWorld world) {
		super(Team.EVIL, 100, m, world);
		// TODO Auto-generated constructor stub
		this.name = "#" + getNextCloneNumber();
		setItemCarried(new Blaster(m));
	}

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

	@Override
	public String getShortDescription() {
		return name + " Storm Trooper";
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}

    @Override
    public String getSymbol() {
        return "S";
    }

    private int getNextCloneNumber(){
	    cloneNumber ++;
	    return cloneNumber;
    }

    //obeyMindControl will be called from the SWActor class, doesn't need to be customised within here
	
	

	
}
