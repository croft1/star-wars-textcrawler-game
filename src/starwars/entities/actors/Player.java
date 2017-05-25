/**
 * starwars.entities.actors package
 * 
 *  Used in the SWApplication (roguelike game) for all actors (both human
 *  and non human) who will wander the map in survival & questing!	
 *
 */
package starwars.entities.actors;


import java.util.List;

import edu.monash.fit2099.simulator.time.Scheduler;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import edu.monash.fit2099.values.TColor;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Attack;
import starwars.actions.HealDroid;
import starwars.actions.Influence;
import starwars.actions.Move;
import starwars.entities.Force;
import starwars.swinterfaces.SWGridController;

/**
 * A very minimal <code>SWActor</code> that the user can control.  Its <code>act()</code> method
 * prompts the user to select a command.
 * 
 * @author ram
 */
/*
 * Change log
 * 2017/02/22	Schedule actions in the act method instead of tick. 
 * 				A controller used to get user input rather than the UI directly (Asel)
 */
public class Player extends SWForceActor {



	/**
	 * Constructor for the <code>Player</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Player</code></li>
	 * 	<li>Initialize the world for this <code>Player</code></li>
	 *  <li>Initialize the <code>Team</code> for this <code>Player</code></li>
	 * 	<li>Initialize the hit points for this <code>Player</code></li>
	 * 	<li>Initialize the <code>Force</code>points for this <code>Player</code></li>
	 * 	<li>Set this <code>Player</code> as a human controlled <code>SWActor</code></li>
	 * </ul>
	 * 
	 * @param team the <code>Team</code> to which the this <code>Player</code> belongs to
	 * @param hitpoints the hit points of this <code>Player</code> to get started with
	 * @param m <code>MessageRenderer</code> to display messages.
	 * @param world the <code>SWWorld</code> world to which this <code>Player</code> belongs to
	 * 
	 */
	public Player(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		humanControlled = true; // this feels like a hack. Surely this should('nt?) be dynamic. Thats starwars
		this.setSymbol("@");
		//use default force
		//use default force influence
		estSideOfForce();
		RESIST_INFLUENCE = 0.75;
		addAffordance(new Influence(this, m));		//player may be influenced to a side of the force

	}
	
	/**
	 * This method will describe this <code>Player</code>'s scene and prompt for user input through the controller 
	 * to schedule the command.
	 * <p>
	 * This method will only be called if this <code>Player</code> is alive and is not waiting.
	 * 
	 * @see {@link #describeScene()}
	 * @see {@link starwars.swinterfaces.SWGridController}
	 */
	@Override
	public void act() {	
		describeScene();
		//say(nextToDroid()); (used to see if a player is next to a Droid 
		scheduler.schedule(SWGridController.getUserDecision(this), this, 1);
		
	}
	/**
	 * This method will describe, 
	 * <ul>
	 * 	<li>the this <code>Player</code>'s location</li>
	 * 	<li>items carried (if this <code>Player</code> is carrying any)</li>
	 * 	<li>the contents of this <code>Player</code> location (what this <code>Player</code> can see) other than itself</li>
	 * <ul>
	 * <p>
	 * The output from this method would be through the <code>MessageRenderer</code>.
	 * 
	 *  @see {@link edu.monash.fit2099.simulator.userInterface.MessageRenderer}
	 */
	public void describeScene() {
		//If dead, the game is lost
		if (this.isDead()) 
		{
			//Message stating Luke was killed in action
			this.messageRenderer.render("\n\nLuke has been killed in action!");
			
			//Scheduler schedules the loss
			scheduler.lossSchedule(this.messageRenderer);
		}
		
		//If on Yavin 4
		if (this.getWorld() == this.getWorld().getUniverse().getWorlds().get(1))
		{
			boolean containsR2D2;
			boolean containsLeia;
			containsR2D2 = (this.getFollowerList().contains("R2"));
			containsLeia = (this.getFollowerList().contains("L"));
			
			if (containsR2D2 == true && containsLeia == true)
			{
				//Message stating Luke was killed in action
				this.messageRenderer.render("\n\nYou successfully took Leia and R2-D2 to the rebel base!");
				
				//Scheduler schedules the win
				scheduler.winSchedule(this.messageRenderer);
			}
		}
		
		//get the location of the player and describe it
		SWLocation location = this.world.getEntityManager().whereIs(this);
		say(this.getShortDescription() + " [HP: " + this.getHitpoints() +
				" F: " + this.getForcePower() + "(" + this.getForceCharge() + "/100) " +
				" I: " + this.getInfluenceString() + "] is at " + location.getShortDescription());
		
		say("Current followers (symbols) of " + this.getShortDescription() + ": " + this.getFollowerList().toString());
		say("Current followers of " + this.getShortDescription() + ": " + this.getFollowerListSWActors().toString());
		
		//get the items carried for the player
		SWEntityInterface itemCarried = this.getItemCarried();
		if (itemCarried != null) {
			//and describe the item carried if the player is actually carrying an item
			say(getCarryDescription());

		}
		
		
		
		//weild
		
		//get the contents of the location
		List<SWEntityInterface> contents = this.world.getEntityManager().contents(location);
		
		//and describe the contents
		if (contents.size() > 1) { // if it is equal to one, the only thing here is this Player, so there is nothing to report
			say(this.getShortDescription() + " can see:");
			for (SWEntityInterface entity : contents) {
				if (entity != this) { // don't include self in scene description
					say("\t " + entity.getSymbol() + " - " + entity.getLongDescription() + " [" + entity.getHitpoints() + "]");
				}
			}
		}
		
		//Gets SWWorld
		SWWorld ownedWorld = this.getWorld();
		
		say(ownedWorld.getWorldName());
		
		say(ownedWorld.getUniverse().getUniverseName());
		
		say(ownedWorld.getUniverse().getMFList().get(0).toString());
		
		
		
		
		/*
		say(ownedWorld.getUniverse().getWorlds().get(0).getWorldName()
				+ ownedWorld.getUniverse().getWorlds().get(1).getWorldName()
				+ ownedWorld.getUniverse().getWorlds().get(2).getWorldName());
				*/
		
		System.out.println("Tatooine init: " + ownedWorld.getUniverse().getWorlds().get(0).getIsInitialised());
		System.out.println("Yavin init init: " + ownedWorld.getUniverse().getWorlds().get(1).getIsInitialised());
		System.out.println("DS init init: " + ownedWorld.getUniverse().getWorlds().get(2).getIsInitialised());
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

*/
