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
 * The FlyToDeathStar action will enable the SWActor (Player) the opportunity to travel to the 
 * enemy spacecraft named the Death Star from either the Yavin IV or Tatooine
 * 
 * @author jas
 * @author mewc
 *
 */
public class FlyToDeathStar extends SWAffordance {

	SWLocation locTravelTo;
	
	EntityManager<SWEntityInterface, SWLocation> theEM;
	
	/**
	 * Constructor for the <code>FlyToDeathStar</code> class. 
	 * 
	 * @param theTarget 	- the Millenium Falcon being flown in (which is a SWEntity)
	 * @param m 	- the message renderer to display messages
	 */
	public FlyToDeathStar(SWEntityInterface theTarget, EntityManager<SWEntityInterface, SWLocation> em, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
		this.theEM = em;
	}

	/**
	 * Public Method canDo(SWActor a)
	 *
	 * Returns a boolean exclaiming that the particular SWActor (a) is able to
	 * use FlyToDeathStar(within the Millenium Falcon)
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
	 * Initiates the Fly to the Death Star process once option is selected from the same menu.
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
				//The Death Star is at index 2 of the universe world list.. obtain it
				SWWorld deathStar = a.getWorld().getUniverse().getWorlds().get(2);

				//Set Lukes' current world to the Death Star
				a.setWorld(deathStar);
				
				//Get the location to teleport to (Death Star Millenium Falcon)
				SWLocation loc = a.getWorld().getGrid().getLocationByCoordinates(0, 0);
		
				//Set the location of Luke to the Millenium Falcon
				deathStar.getEntityManager().setLocation(a, loc);
		        
		        //Grid controller controls the data and commands between the UI and the model
				SWGridController uiController = new SWGridController(a.getWorld());

				Scheduler theScheduler = new Scheduler(1, a.getWorld());
				
				SWActor.setScheduler(theScheduler);

				a.getWorld().getUniverse().setActiveWorld(deathStar);
				
				// Set up the world if not done so already, otherwise pass 
				if (a.getWorld().getUniverse().getActiveWorld().getIsInitialised() == false)
				{
					//Set up the Death Star (if not done already)
					a.getWorld().getUniverse().getActiveWorld().initializeWorld(uiController);
					
					//Set the active worlds initialisation to true (for not re-initialising worlds in transport)
					a.getWorld().getUniverse().getActiveWorld().setIsInitialised(true);
				}

				// Kick off the scheduler
				while(true) {
					uiController.render();
					theScheduler.tick();
				}
			}
			//Luke has followers. Transport them to the Death Star with the SAME location
			else
			{
				//The Death Star is at index 2 of the universe world list.. obtain it
				SWWorld deathStar = a.getWorld().getUniverse().getWorlds().get(2);
				a.say(deathStar.getWorldName());
				
				//Each actor is represented by their symbol
				for (SWActor followingActor : a.getFollowerListSWActors())
				{
					//Get the location to teleport to (Death Star Millenium Falcon)
					SWLocation loc = followingActor.getWorld().getGrid().getLocationByCoordinates(0, 0);
					
					//Teleport the SWActor to the Death Star Millenium Falcon
					deathStar.getEntityManager().setLocation(followingActor, loc);
				}
				
				//Transport Luke
				a.setWorld(deathStar);
				
				//Set Lukes' active world to the Death Star
				a.getWorld().getUniverse().setActiveWorld(deathStar);
				
				//Get the location to teleport to (Death Star Millenium Falcon)
				SWLocation loc = a.getWorld().getGrid().getLocationByCoordinates(0, 0);
			
				//Teleport Luke
				deathStar.getEntityManager().setLocation(a, loc);
		        
		        //Grid controller controls the data and commands between the UI and the model
				SWGridController uiController = new SWGridController(a.getWorld());

				Scheduler theScheduler = new Scheduler(1, a.getWorld());
				
				SWActor.setScheduler(theScheduler);
			
				// Set up the world if not done so already, otherwise pass 
				if (a.getWorld().getUniverse().getActiveWorld().getIsInitialised() == false)
				{
					// set up the world (Death Star)
					a.getWorld().getUniverse().getActiveWorld().initializeWorld(uiController);
					
					//Set the active worlds initialisation to true (for not re-initialising worlds in transport)
					a.getWorld().getUniverse().getActiveWorld().setIsInitialised(true);
				}

				// Kick off the scheduler
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
	 * Returns a string description of FlyToDeathStar. Used when showing the player they 
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

Javin, P 2017, How to find length/size of ArrayList in Java? Example, Java67, viewed 22 May 2017,
http://www.java67.com/2016/07/how-to-find-length-size-of-arraylist-in-java.html

Stack Overflow 2011, Creating a new ArrayList in Java, viewed 22 May 2017,
https://stackoverflow.com/questions/5915892/creating-a-new-arraylist-in-java

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2012, How to remove first and last character of a string?, viewed 22 May 2017,
https://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

Stack Overflow 2010, Java ArrayList Index, viewed 22 May 2017,
https://stackoverflow.com/questions/4313457/java-arraylist-index

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

*/