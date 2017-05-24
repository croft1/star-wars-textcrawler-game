/**
 * starwars.actions package
 * 
 * Initiates actions that will be able to be initiated by SWActors in the Star Wars
 * roguelike game. This includes actions like Obey (the Force), TakeOwnership (of Droids),
 * Leave (an item) and so forth!
 *
 */
package starwars.actions;

import java.util.ArrayList;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.time.Scheduler;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.entities.Fillable;
import starwars.entities.MilleniumFalcon;
import starwars.entities.actors.Droid;
import starwars.entities.actors.Player;
import starwars.swinterfaces.SWGridController;

/**
 * Class for Fly to the Death Star
 * 
 * The fly action will enable the SWActor (Player) the opportunity to travel to the 
 * enemy spaceraft named the Death Star from either the Yavin IV or Tatooine
 * 
 * @author jas
 * @author mewc
 *
 */
public class FlyToDeathStar extends SWAffordance {

	SWLocation locTravelTo;
	
	EntityManager<SWEntityInterface, SWLocation> theEM;
	
	/**
	 * Constructor for the <code>Fly</code> class. 
	 * 
	 * @param theTarget 	- the Millenium Falcon being flown in (which is a SWEntity)
	 * @param m 	- the message renderer to display messages
	 */
	public FlyToDeathStar(SWEntityInterface theTarget, EntityManager<SWEntityInterface, SWLocation> em, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		this.theEM = em;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use Fly (within the Millenium Falcon
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake this action
	 * 
	 * @return 	- Boolean (true) that exclaims this actor can undertake the action of
	 * flight in the Millenium Falcon
	 *
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}
	
	/**
	 * Public Method act(SWActor a)
	 *
	 * Initiates the Fly process once option is selected from the same menu.
	 *
	 * 
	 * @param 	a	- The SWActor in question of being able to undertake the process of 
	 * flight.
	 *
	 */
	@Override
	public void act(SWActor a) {
		//If the entity trying to fly is Luke
		if (a instanceof Player) {

			//Get the Array List depicting the followers of Luke
			ArrayList<String> followersList = a.getFollowerList();
			
			//If no one is following Luke
			if (followersList.size() == 0)
			{
				a.say("No one is following me");
				
				a.say(a.getShortDescription() + " is on world " + a.getWorld().getWorldName() 
						+ " in the " + a.getWorld().getUniverse().getUniverseName());
				
				//Yavin IV is at index 1 of the universe world list.. obtain it
				SWWorld deathStar = a.getWorld().getUniverse().getWorlds().get(2);
				a.say(deathStar.getWorldName());

				//Initially transport to Ben 
				//this.theEM.setLocation(a, this.locTravelTo);
			
				//this.theEM.setLocation(a, a.getWorld().getUniverse().getMFList().get(0));
				
				a.setWorld(deathStar);
				
				SWLocation loc = a.getWorld().getGrid().getLocationByCoordinates(0, 0);
				deathStar.getEntityManager().setLocation(a, loc);
		        
		        //Grid controller controls the data and commands between the UI and the model
				SWGridController uiController = new SWGridController(a.getWorld());

				Scheduler theScheduler = new Scheduler(1, a.getWorld());
				
				SWActor.setScheduler(theScheduler);

				a.getWorld().getUniverse().setActiveWorld(deathStar);
				
				// Set up the world if not done so already, otherwise pass 
				if (a.getWorld().getUniverse().getActiveWorld().getIsInitialised() == false)
				{
					//Set up Tatooine (if not done already)
					a.say("The Death Star has not been initialised. Setting up...");
					
					// set up the world (Yavin Four)
					a.getWorld().getUniverse().getActiveWorld().initializeWorld(uiController);
					
					//Set the active worlds initialisation to true (for not re-initialising worlds in transport)
					a.getWorld().getUniverse().getActiveWorld().setIsInitialised(true);
				}
				a.say("The Death Star has been initialised.");

				// kick off the scheduler
				while(true) {
					uiController.render();
					theScheduler.tick();
				}
			}
			//Luke has followers. Transport them to Yavin IV with the SAME location
			else
			{
				//Yavin IV is at index 1 of the universe world list.. obtain it
				SWWorld deathStar = a.getWorld().getUniverse().getWorlds().get(2);
				a.say(deathStar.getWorldName());
				
				//Each actor is represented by their symbol
				for (SWActor followingActor : a.getFollowerListSWActors())
				{
					//Set location of Actors to Yavin 4's Millenium Falcon 
					SWLocation loc = followingActor.getWorld().getGrid().getLocationByCoordinates(0, 0);
					deathStar.getEntityManager().setLocation(followingActor, loc);
				}
				
				//Transport Luke
				a.setWorld(deathStar);
				
				a.getWorld().getUniverse().setActiveWorld(deathStar);
				
				SWLocation loc = a.getWorld().getGrid().getLocationByCoordinates(0, 0);
				deathStar.getEntityManager().setLocation(a, loc);
		        
		        //Grid controller controls the data and commands between the UI and the model
				SWGridController uiController = new SWGridController(a.getWorld());

				Scheduler theScheduler = new Scheduler(1, a.getWorld());
				
				SWActor.setScheduler(theScheduler);
			
				// Set up the world if not done so already, otherwise pass 
				if (a.getWorld().getUniverse().getActiveWorld().getIsInitialised() == false)
				{
					//Set up the Death Star (if not done already)
					a.say("The Death Star  has not been initialised. Setting up...");
					
					// set up the world (Death Star)
					a.getWorld().getUniverse().getActiveWorld().initializeWorld(uiController);
					
					//Set the active worlds initialisation to true (for not re-initialising worlds in transport)
					a.getWorld().getUniverse().getActiveWorld().setIsInitialised(true);
				}
				a.say("The Death Star has been initialised.");

				// kick off the scheduler
				while(true) {
					uiController.render();
					theScheduler.tick();
				}
			}
		}	
	}

	/**
	 * public method getDescription()
	 * 
	 * Returns a string description of Fly. Used when showing the player they 
	 * are able to complete this action selected.
	 * 
	 * @return	-	String of action - implemented in game selection options.
	 *
	 */
	@Override
	public String getDescription() {
		return "Fly to the Death Star";
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
