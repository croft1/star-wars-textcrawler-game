package starwars;

import edu.monash.fit2099.simulator.time.Scheduler;
import starwars.swinterfaces.SWGridController;
import starwars.worlds.DeathStar;
import starwars.worlds.Tatooine;
import starwars.worlds.YavinFour;
import starwars.SWUniverse;
import java.util.ArrayList;

/**
 * Driver class for the Star Wars package with <code>GridController</code>.  Contains nothing but a main().
 * 
 * @author ram
 */
/*
 * Change log
 * 2017-02-02	The TextInterface handles the responsibly of displaying the grid not the SWGrid or SWWorld classes (asel)
 * 2017-02-10	GridController controls the interactions with the user and will determine which UI it should use to do this. 
 * 			    Therefore there is tight coupling with the user interfaces and the driver. The application no longer has to worry about the
 * 				UI(asel)
 * 2017-02-19	Removed the show banner method. The text interface will deal with showing the banner. (asel)
 */

public class Application {
	public static void main(String args[]) {
		//Implement a new SWUniverse
		SWUniverse SWuniv = new SWUniverse("The Star Wars Universe");
		
		//Implement three worlds in the Star Wars universe
		SWWorld tatooine = new Tatooine(SWuniv);
		SWWorld yavinFour = new YavinFour(SWuniv);
		SWWorld deathStar = new DeathStar(SWuniv);
		
		//Add the three worlds (two planets, 1 ship) to the universe
		SWuniv.getWorlds().add(tatooine);	//Tatooine at index 0
		SWuniv.getWorlds().add(yavinFour);	//Yavin IV at index 1
		SWuniv.getWorlds().add(deathStar);	//The Death Star at index 2
		
		//Set the current active world to Tatooine
		SWuniv.setActiveWorld(tatooine);
		
		//Run the three worlds
		runUniverse(SWuniv);
	}


	//primarily for testing,
	private static void runUniverse(SWUniverse univ){

		//Grid controller controls the data and commands between the UI and the model
		SWGridController uiController = new SWGridController(univ.getActiveWorld());

		Scheduler theScheduler = new Scheduler(1, univ.getActiveWorld());
		
		SWActor.setScheduler(theScheduler);

		// set up the world (Tatooine)
		univ.getActiveWorld().initializeWorld(uiController);
		
		//Set the active worlds initialisation to true (for not re-initialising worlds in transport)
		univ.getActiveWorld().setIsInitialised(true);

		// kick off the scheduler
		while(true) {
			uiController.render();
			theScheduler.tick();
		}
	}

	
	

}
