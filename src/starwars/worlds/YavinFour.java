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


		
        
        // The Millenium Falcon
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
	
