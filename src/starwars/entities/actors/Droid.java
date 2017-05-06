package starwars.entities.actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;

public class Droid extends SWActor {

	private String name;
	private int InitHP;	//Initial HP integer (for self healing of Droids)

	/**
	 * Create a Droid At present coding, Droids are of team DROID,
	 * so their attempts to attack other Droids won't be effectual.
	 * 
	 * @param hitpoints
	 *            the number of hit points of this Droid has. If this
	 *            decreases to below zero, the Raider will become immobile
	 * @param name
	 *            the Droids name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>Droid</code> belongs to
	 * 
	 */
	public Droid(int hitpoints, String name, MessageRenderer m, SWWorld world) {
		super(Team.NEUTRAL, 50, m, world);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.InitHP = hitpoints;
	}

	@Override
	public void act() {

		//Location symbol of a Droid
		char locationSymbol = this.world.getEntityManager().whereIs(this).getSymbol();
		
		if (isDead()) {
			return;
		}
		
		say(describeLocation());
		
		if (this.humanControlled == true) {
			
			//Following Owner - since humancontrolled.
			
			if (isDead()) {
				return;
			} else
			{
				//Get owners' location
				SWLocation ownerloc = this.world.getEntityManager().whereIs(this.getOwner());
				
				//Set Droids' position to owners' location (follow)
				this.world.getEntityManager().setLocation(this, ownerloc);
				
				//Take damage if moving to the Badlands
				if (locationSymbol == 'b') { //IF the Droid is at the Badlands
					int NewHP = this.getHitpoints() - 2;	//Take 2 from the Droids' health
					this.setHitpoints(NewHP);
				
					System.out.println(this.getShortDescription() + " has lost health by moving into the Badlands!");
				}
			}
			
		} else 
		{
			if (Math.random() > 0.8){
				ArrayList<Direction> possibledirections = new ArrayList<Direction>();
					
				// build a list of available directions
				for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
					if (SWWorld.getEntitymanager().seesExit(this, d)) {
						possibledirections.add(d);
					}
				}

				Direction heading = possibledirections.get((int) (Math.floor(Math.random() * possibledirections.size())));
				say(getShortDescription() + " is heading " + heading + " next.");
				Move myMove = new Move(heading, messageRenderer, world);
					
				scheduler.schedule(myMove, this, 1);
				
				
			}
			
			//If a Roaming Droid is at the Badlands, they lose health
			
			if (locationSymbol == 'b') { //IF the Droid is at the Badlands
				int NewHP = this.getHitpoints() - 2;	//Take 2 from the Droids' health
				this.setHitpoints(NewHP);
			
				System.out.println(this.getShortDescription() + " has lost health by moving into the Badlands!");
			}
		}	
	}

	@Override
	public String getShortDescription() {
		return name + " the Droid";
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}
	
	/*
	private String nextToPlayer() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		//get the contents of the location
		List<SWEntityInterface> contents = this.world.getEntityManager().contents(location);
				
		//and describe the contents
		if (contents.size() > 1) { // if it is equal to one, the only thing here is this Player, so there is nothing to report
			for (SWEntityInterface entity : contents) {
				String entity_symbol = "@";
				if (entity.getSymbol() == entity_symbol) { // If the Droid is next to Luke (denoted by @)
					return (this.getLongDescription() + " is next to " + entity.getShortDescription() + "!");
					}
				}
			}
		return ("Luke is not next to a Droid");
	}
	*/
}
