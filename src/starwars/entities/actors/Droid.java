/**
 * starwars.entities.actors package
 * 
 *  Used in the SWApplication (roguelike game) for all actors (both human
 *  and non human) who will wander the map in survival & questing!	
 *
 */
package starwars.entities.actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.actors.behaviors.Patrol;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Attack;
import starwars.actions.Disassemble;
import starwars.actions.Move;
import starwars.actions.Repair;
import starwars.actions.Take;
import starwars.actions.HealDroid;
import starwars.actions.Leave;


/**
 * Class for Droid SWActors  
 * 
 * Droids are non human SWActors able to follow the player if taken 
 * ownership of. Droid, like all SWActors also have a certain amount of hitpoints that
 * renders them �mmobile if falling below zero. It is to this need then that
 * the Droid can be dismantled into Droid Parts - and then re-assembled 
 * by using these parts.
 * 
 * Droids also are ownable - thus, if a player comes across a neutral
 * affiliated Droid, they can take up their ownership. Droids do NOT use
 * the Force, so they are essentially the owners' companions!
 * 
 * @author rober_000
 * @author jas
 * @author mewc
 *
 */
public class Droid extends SWActor {

	private String name;

	/**The owner of the actor. Utilised for Droids**/
	private SWActor owner;
	
	/**isImmobile boolean. Used for Droid SWActors in most actions*/
	private boolean isImmobile;
	
	/**isDisassembled boolean. Used for the disassembly and repair of Droids**/
	private boolean isDisassembled;
	
	/**Patrol path used for specific Droids. Null otherwise**/
	private Patrol droidPatrol;
	
	/**
	 * Constructor for a Droid. Droids are initially of NEUTRAL affiliation. Taking 
	 * ownership of a Droid changes their allegience.
	 * 
	 * @param hitpoints
	 *            the number of hit points of this Droid has. If this
	 *            decreases to below zero, the Droid will become immobile
	 * @param name
	 *            the Droids name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>Droid</code> belongs to
	 * 
	 */
	public Droid(int hitpoints, String name, MessageRenderer m, SWWorld world, Direction [] droidPath) {
		super(Team.NEUTRAL, hitpoints, m, world);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.owner = null;	//Initial owner - not null
		this.isImmobile = false; //Initially not immobile
		this.isDisassembled = false; //Initially not disassembled
		
		//Setting Droidpaths for a Droid that has a given path. Otherwise they are able to free roam.
		if (droidPath == null) {
			return;
		}
		else {
			this.droidPatrol = new Patrol(droidPath);
		}
		
	}

	/**
	 * act() method for Droids
	 * 
	 * Implements the Droids' actions - which if a regular Droid, is essentially moving around
	 * in a random fashion (20%) chance each turn.
	 * 
	 * Droids can heal themselves if they are below half their health - if they come accross a 
	 * oil can they have picked up.
	 * 
	 * Once taken ownership, Droids follow their new owner until they are once again immobile. On 
	 * repair, Droids take on their owners allegiance, so be wary!
	 * 
	 */
	@Override
	public void act() {

		//Location symbol of a Droid (Stack Overflow 2010)
		char locationSymbol = this.world.getEntityManager().whereIs(this).getSymbol();
		
		//Begin act
		say(describeLocation());		
		
		//Check for the lose condition that R2 is disassembled
		//If R2's HP is <=0
		if (this.isDead())
		{
			//If R2 is disassembled
			if(this.getIsDisassembled())
			{
				//Is the symbol of the disassembled Droid is R2's
				if(this.getSymbol().contains("{R2}"))
				{
					//Message stating R2 was disassembled
					this.messageRenderer.render("\n\nR2-D2 has been disassembled!");
					
					//Scheduler schedules the loss
					scheduler.lossSchedule(this.messageRenderer);
				}
			}
		}
		
		
		//If a Droid is immobile (Dead)
		if (this.getIsImmobile() == true) {
			say(this.getShortDescription() + " is immobile. ");
						
			if(this.getOwner() != null)
			{
				say("Owned. checking in owners follow list");
				
				String thisDroidNoBraces = this.getSymbol().substring(1, this.getSymbol().length()-1);

				int indexofDroid = this.getOwner().getFollowerList().indexOf(thisDroidNoBraces);
				
				if (this.getOwner().getFollowerList().get(indexofDroid).equals(thisDroidNoBraces))
				{
					say("Removing");
					this.getOwner().getFollowerList().remove(thisDroidNoBraces);
					this.getOwner().getFollowerListSWActors().remove(this);
					this.setOwner(null);
				}
				else
				{
					return;
				}
			}
			else
			{
				
			}
		}
		
		//If a Droid is mobile and human controlled
		else if (this.humanControlled == true) {
			
			//Following Owner - since humancontrolled.
			
			
			if (isDead()) 
			{
				this.setIsImmobile(true);
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
		
		//C-3PO Droid specific act ()code
		else if (this.getSymbol() == "C3") {
			
			//Double precision number to represenet C-3PO's chance of speaking
			double c3Speak = Math.random();
			
			//10% chance of occuring a 0.9 or above
			if (c3Speak >= 0.9) {
				//Call C-3PO speak method (he talks!)
				c3POSpeaks();
			}
			else {
				return;
			}
		}
		
		//R2-D2 Repair droid specific act() code
		else if (this.getSymbol() == "R2") {
			
			this.messageRenderer.render("R2 DISS: " + this.isDisassembled);
			
			//Describe who R2 can see
			//get the contents of the location
			List<SWEntityInterface> r2contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
			
			//and describe the contents
			if (r2contents.size() > 1) { // if it is equal to one, the only thing here is R2, so there is nothing to report
				for (SWEntityInterface r2entity : r2contents) {
					if (r2entity.getSymbol().contains("D")) { // If R2 comes across a Droid (Stack Overflow 2010)
						say(this.getShortDescription() + " has come accross a Droid!");
						
						//Cast target entity as a Droid for checking what it needs from R2
						Droid targetr2 = (Droid) r2entity;
						
						//R2 attempts to disassemble the Droid if its immobile
						if (targetr2.isImmobile && targetr2.isDisassembled == false) {
							Disassemble r2diss = new Disassemble(r2entity, messageRenderer);
							scheduler.schedule(r2diss, this, 1);
						}
						
						//R2 tries to repair a droid who is immobile and disassembled
						else if (targetr2.isImmobile && targetr2.isDisassembled) {
							if (this.getItemCarried() != null) {
								Repair r2rep = new Repair(r2entity, messageRenderer);
								scheduler.schedule(r2rep, this, 1);
							} 
						}
						
						//R2 attempts to Heal a Droid if active and not disassembled
						else if (targetr2.isDisassembled == false && targetr2.isImmobile == false)
						{
							
							HealDroid r2heal = new HealDroid(r2entity, messageRenderer);
							scheduler.schedule(r2heal, this, 1);
						}
					}
				}
			}
					
			//Heals himself if he can
			
			HealDroid droidHealR2 = new HealDroid(this, messageRenderer);
			scheduler.schedule(droidHealR2, this, 1);
			
			//R2 moves
			Direction R2Direction = this.getDroidPatrol().getNext();
			say(getShortDescription() + " moves " + R2Direction);
			Move myMove = new Move(R2Direction, messageRenderer, world);

			scheduler.schedule(myMove, this, 1);
			
			
			
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
				randomMovement();
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

	/**
	 * getShortDescription() method for Droids
	 * 
	 * Returns the short string description of a Droid. This is based on the basis of a Droid - if it is either
	 * C-3PO or R2-D2, the function returns specialized descriptions matching their functions. Otherwise for
	 * regular Droids, the return is the same - " the Droid ".
	 * 
	 * @return 	description	- The Droids' short description in String format
	 */
	@Override
	public String getShortDescription() {
		
		if (this.getSymbol() == "R2") {
			return name + " the Repair Droid";
		}
		
		else if (this.getSymbol() == "C3") {
			return name + " the etiquette minded Droid";
		}
		
		else {
			return name + " the Droid";
		}	
	}

	/**
	 * getLongDescription() method for Droids
	 * 
	 * Returns the short string description (as this.getShortDEscripton) of a Droid. This is based on 
	 * the basis of a Droid - if it is either C-3PO or R2-D2, the function returns specialized descriptions 
	 * matching their functions. Otherwise for regular Droids, the return is the same - " the Droid ".
	 * 
	 * @return 	short descripton 	- The Droids' short descripton in String format
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	//Private describeLocation() method for Droids - describes the location of a Droid
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();
	}
	
	/**
	 * getOwner() method for Droids
	 * 
	 * Returns the SWActor owner the Droids currently. If the Droid does not have a
	 * owner, this function returns null.
	 * 
	 * @return 	owner 	- The Droids' SWActor owner at present runtime.
	 */
	public SWActor getOwner() {
		//Return the SWActor owner of this Actor (initially nothing, can change!)
		return owner;
	}
	
	/**
	 * setOwner() method for Droids
	 * 
	 * Initiates the SWActor owner of the Droid that will take owner of said Droid. This
	 * method also sets the humanControlled variable to true.
	 * 
	 * @param 	newOwner	- The new owner (SWActor) of the Droid 
	 * 
	 */
	public void setOwner(SWActor newOwner) {
		//Set this SWActors' owner to newOwner
		this.owner = newOwner;
		
		//Set humancontrolled boolean to true
		this.humanControlled = true;
	}
	
	/**
	 * setIsImmobile() method for Droids
	 * 
	 * Initiates the mobile status of Droids once called upon.
	 * 
	 * @param 	newisImmobile	- The new mobile status of the Droid 
	 * 
	 */
	public void setIsImmobile(boolean newisImmobile) {
		this.isImmobile = newisImmobile;
	}
	
	/**
	 * getIsImmobile() method for Droids
	 * 
	 * Returns the current mobility status of a Droid
	 * 
	 * @return 	isImmobile	- The mobility status of a Droid (Boolean)
	 * 
	 */
	public boolean getIsImmobile() {
		return isImmobile;
	}

	/**
	 * setIsDisassembled() method for Droids
	 * 
	 * Initiates the disassembled status of Droids once called upon. Note that
	 * disassembled Droid will drop some Droid Parts in use for repairing the Droid
	 * back to usable health!
	 * 
	 * @param 	newIsDis	- The new disassembled status of the Droid 
	 * 
	 */
	public void setIsDisassembled(boolean newIsDis) {
		this.isDisassembled = newIsDis;
	}

	/**
	 * getIsDisassembled() method for Droids
	 * 
	 * Obtains the disassembled status of a Droid once called upon. Note that
	 * disassembled Droid will drop some Droid Parts in use for repairing the Droid
	 * back to usable health!
	 * 
	 * @return 	isDisassembled	- The disassembled status of the Droid 
	 * 
	 */
	public boolean getIsDisassembled() {
		return isDisassembled;
	}
	
	//Selfheal method for Droids	( if they are at lower than half health)
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
	
	//Droid Patrol method (for R2-D2's implementation)
	private Patrol getDroidPatrol() {
		return this.droidPatrol;
	}
	
	//c3OSpeaks method - for calling in random quotes from C-3PO fine choice of literacy! (, Java Switch Statement, viewed 10 May 2017,
	// (Javatpoint 2017) (The Internet Movie Database 2017)
	private void c3POSpeaks() {
		int quoteChoice = (int) ( Math.random() * 10+1);
		
		switch (quoteChoice) {
		case 1 : this.say("C-3PO says: 'Artoo says that the chances of survival \nare 725 to 1. Actually Artoo has \nbeen known to make mistakes... from time \nto time... Oh dear...' "); break;
		case 2 : this.say("C-3PO says: 'Excuse me sir, but might I inquire \nas to what's going on?' "); break;
		case 3 : this.say("C-3PO says: 'I have a bad feeling about this.' "); break; 
		case 4 : this.say("C-3PO says: 'I've had the most peculiar dream.'"); break;
		case 5 : this.say("C-3PO says: 'I'm programmed for etiquette, not destruction!'"); break;
		case 6 : this.say("C-3PO says: 'Pardon me, sir, could you possibly resist fiddling \nwith technology that is beyond your comprehension?' "); break;
		case 7 : this.say("C-3PO says: 'Well, you'll never get me to go back to that iceberg! \nI don't care how safe it is now, R2, it gives my motivators the \nchills just thinking about it. ' "); break;
		case 8 : this.say("C-3PO says: 'Hello, I am C-3PO, human cyborg relations. How might I serve you?' "); break;
		case 9 : this.say("C-3PO says: 'Hm, it seems no one wants my company tonight. '"); break;
		case 10 : this.say("C-3PO says: 'This way looks potentially dangerous.' "); break;
		}
	}
}

/*
REFERENCES

Javatpoint 2017, Java Switch Statement, viewed 10 May 2017,
https://www.javatpoint.com/java-switch 

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

http://stackoverflow.com/questions/4313457/java-arraylist-index 22

http://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string 22
*/
