package starwars.entities.actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Attack;
import starwars.actions.Move;
import starwars.actions.Take;
import starwars.actions.HealDroid;
import starwars.actions.Leave;

public class Droid extends SWActor {

	private String name;

	/**The owner of the actor. Utilised for Droids**/
	private SWActor owner;
	
	/**isImmobile boolean. Used for Droid SWActors in most actions*/
	private boolean isImmobile;
	
	/**isDisassembled boolean. Used for the disassembly and repair of Droids**/
	private boolean isDisassembled;
	
	/**
	 * Creates a Droid. Droids are initially of NEUTRAL affiliation. Taking 
	 * ownership of a Droid changes their allegience.
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
		this.owner = null;	//Initial owner - not null
		this.isImmobile = false; //Initially not immobile
		this.isDisassembled = false; //Initially not disassembled
		
		//SWActors are given the Attack affordance hence they can be attacked
		//SWAffordance healdroid = new HealDroid(this, m);
		//this.addAffordance(healdroid);
		
	}

	@Override
	public void act() {

		//Location symbol of a Droid
		char locationSymbol = this.world.getEntityManager().whereIs(this).getSymbol();
		
		//Begin act
		
		say(describeLocation());
		
		//If a Droid is immobile (Dead)

		if (this.getIsImmobile() == true) {
			say(this.getShortDescription() + " is immobile. ");

			return;
		}
		
		//If a Droid is mobile and human controlled
		else if (this.humanControlled == true) {
			
			//Following Owner - since humancontrolled.
			
			if (isDead()) {
				return;
			} 
			
			else
			{
				//Get owners' location
				SWLocation ownerloc = this.world.getEntityManager().whereIs(this.getOwner());
				
				//Set Droids' position to owners' location (follow)
				this.world.getEntityManager().setLocation(this, ownerloc);
				
				//Take damage if moving to the Badlands
				if (locationSymbol == 'b') { //IF the Droid is at the Badlands
					int NewHP = this.getHitpoints() - 2;	//Take 2 from the Droids' health
					this.setHitpoints(NewHP);
				
					say(this.getShortDescription() + " has lost health by moving into the Badlands!");
				}
				
				selfHeal();
			}	
		} 
		
		//If a Droid is not immobile, and not human controlled (roaming)
		else {	
			
			//Picking up an oil can
			//Get contents of the current location
			List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
			
			//and describe the contents
			if (contents.size() > 1) { // if it is equal to one, the only thing here is this Player, so there is nothing to report
				for (SWEntityInterface entity : contents) {
					if (entity != this) { // don't include self in scene description
						if (entity.getSymbol() == "x") {
							
							//The Droid is standing over a oil can. Does it pick it up?
							if (Math.random() > 0.5){ //Half a chance...
								say(this.getShortDescription() + " decided to pick up " + entity.getShortDescription());
	
								//Droid takes the oil can. Scheduler implements the Take.
								Take droidTakes = new Take(entity, messageRenderer);
								scheduler.schedule(droidTakes, this, 1);
							}
							else { //The Droid passes over the oil can.
								say(this.getShortDescription() + " decided not to pick up" + entity.getShortDescription());
							}						
						}
					}
				}
			}
			
			
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
			
				say(this.getShortDescription() + " has lost health by moving into the Badlands!");
			}
			
			/*Self healing
			Implementation that if a Droids' health gets below half, they will attempt to heal themselves IF they 
			are carrying an oil can
			*/
			selfHeal();
			
			
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
	
	public SWActor getOwner() {
		//Return the SWActor owner of this Actor (initially nothing, can change!)
		return owner;
	}
	
	public void setOwner(SWActor newOwner) {
		//Set this SWActors' owner to newOwner
		this.owner = newOwner;
		
		//Set humancontrolled boolean to true
		this.humanControlled = true;
	}
	
	public void setIsImmobile(boolean newisImmobile) {
		this.isImmobile = newisImmobile;
	}
	
	public boolean getIsImmobile() {
		return isImmobile;
	}

	//isDisassembled setter & getter
	
	public void setIsDisassembled(boolean newIsDis) {
		this.isDisassembled = newIsDis;
	}
	
	public boolean getIsDisassembled() {
		return isDisassembled;
	}
	
	
	private void selfHeal() {

		//IF the Droid is carrying an item...
		if (this.getItemCarried() != null){
			
			//If the item is an oil can...
			if (this.getItemCarried().getShortDescription() == "an oil can" ) {
			
				//If the Droids health is LOWER than half...
				if((this.getInitialHP()/2) > this.getHitpoints()) {
					say(this.getShortDescription() + " is at or below half HP. "
							+ " Healing itself...");
					
					//Implementing a new HealDroid method (Droid heals on itself)
					//public HealDroid(SWEntityInterface theTarget, MessageRenderer m) {
					HealDroid droidHeal = new HealDroid(this, messageRenderer);
					
					scheduler.schedule(droidHeal, this, 1);
					
				}
				else {
					return;
				}
			}
		}		
	}
}
