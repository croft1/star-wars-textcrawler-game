package starwars.worlds;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.*;
import starwars.entities.*;
import starwars.entities.actors.*;

/**
 * Class representing the Yavin IV (Four) moon in the Star Wars universe. The rebel 
 * base - this moon is home to Mon Mothma and General Ackbar - who are in search of their
 * General - Princess Leia. Luke is tasked to bring her to the base to win the game (along
 * with R2-D2 and the Death Star plans)
 * 
 * @author ram
 * @author jas
 * @author mewc
 */
public class YavinFour extends SWWorld {

	/**
	 * Constructor of <code>YavinFour</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid for setup of the Yavin IV instance.
	 * 
	 * @param 	inUniverse		The universe into which this Yavin IV instance belongs to.
	 */

	public YavinFour(SWUniverse inUniverse) {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		myGrid = new SWGrid(2,2,factory);
		space = myGrid;
		name = "Yavin IV";
		universe = inUniverse;
	}

	/**
     * Sets up Yavin IV, setting descriptions for locations and placing items and actors
     * on the grid.
     *
     * @param iface a MessageRenderer to be passed onto newly-created entities
     * @author ram
     */
	public void initializeWorld(MessageRenderer iface) {
		SWLocation loc;
		
		// Set default location string
		for (int row = 0; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				loc = myGrid.getLocationByCoordinates(col, row);
				loc.setLongDescription(name + " (" + col + ", " + row + ")");
				loc.setShortDescription(name + " (" + col + ", " + row + ")");
				loc.setSymbol('_');
			}
		}


		Direction[] patrolmoves = {CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.SOUTH,
				CompassBearing.WEST, CompassBearing.WEST,
				CompassBearing.SOUTH,
				CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.NORTHWEST, CompassBearing.NORTHWEST};


		
        
        // The Millennium Falcon
        MilleniumFalcon millFalcYFour= new MilleniumFalcon(iface);
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(millFalcYFour, loc);
        
        //Append the MF location to the MF Location list in SWUNiverse
        this.universe.getMFList().add(loc);
        
        millFalcYFour.addAffordance(new FlyToTatooine(millFalcYFour, this.getEntityManager(), iface));
        millFalcYFour.addAffordance(new FlyToDeathStar(millFalcYFour, this.getEntityManager(), iface));

        GeneralAckbar ga = new GeneralAckbar(iface, this);
        loc = myGrid.getLocationByCoordinates(0, 1);
        entityManager.setLocation(ga, loc);
        ga.setSymbol("GA");

        MonMothma mm = new MonMothma(iface, this);
        loc = myGrid.getLocationByCoordinates(1, 1);
        entityManager.setLocation(mm, loc);
        mm.setSymbol("MM");
        
        // A blaster
        Blaster blaster = new Blaster(iface);
        loc = myGrid.getLocationByCoordinates(0, 1);
        entityManager.setLocation(blaster, loc);

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
