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
 * Class representing a world in the Star Wars universe. 
 * 
 * @author ram
 */
/*
 * Change log
 * 2017-02-02:  Render method was removed from Middle Earth
 * 				Displaying the Grid is now handled by the TextInterface rather 
 * 				than by the Grid or MiddleWorld classes (asel)
 */
public class YavinFour extends SWWorld {

	/**
	 * Constructor of <code>SWWorld</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid.
	 */
	public YavinFour() {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		myGrid = new SWGrid(2,2,factory);
		space = myGrid;
		name = "Yavin IV";

	}


	/**
	 * Set up the world, setting descriptions for locations and placing items and actors
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


		//TODO Need to move the existing player to this position, not create a new one
        // Luke - add in the existing luke entity to here on transport.
        //DONT DO LIKE THIS - need to reuse to keep stats
        Player luke = new Player(Team.GOOD, 100, iface, this);
        luke.setShortDescription("Luke");
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(luke, loc);
        luke.resetMoveCommands(loc);

        GeneralAckbar ga = new GeneralAckbar(iface, this);
        loc = myGrid.getLocationByCoordinates(0, 1);
        entityManager.setLocation(ga, loc);
        ga.setSymbol("GA");

        MonMothma mm = new MonMothma(iface, this);
        loc = myGrid.getLocationByCoordinates(1, 1);
        entityManager.setLocation(mm, loc);
        mm.setSymbol("MM");

	}
}
	
