package starwars.worlds;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
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
public class DeathStar extends SWWorld {



	/**
	 * Constructor of <code>SWWorld</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid.
	 */
	public DeathStar() {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		myGrid = new SWGrid(10,10, factory);
		space = myGrid;
		name = "The Death Star";

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
				loc.setSymbol('x');
			}
		}


		Direction[] patrolmoves = {CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.SOUTH,
				CompassBearing.WEST, CompassBearing.WEST,
				CompassBearing.SOUTH,
				CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.NORTHWEST, CompassBearing.NORTHWEST};


		// TODO Luke - add in the existing luke entity to here on transport.
        //DONT DO LIKE THIS - need to reuse to keep stats
        Player luke = new Player(Team.GOOD, 100, iface, this);
        luke.setShortDescription("Luke");
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(luke, loc);
        luke.resetMoveCommands(loc);


        // The Millenium Falcon
        MilleniumFalcon millFalc = new MilleniumFalcon(iface);
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(millFalc, loc);


        PrincessLeia leia = PrincessLeia.getPrincessLeia(iface, this, patrolmoves);
        leia.setSymbol("L");
        loc = myGrid.getLocationByCoordinates(9, 9);
        entityManager.setLocation(leia, loc);

        DarthVader dv = DarthVader.getDarthVader(iface, this, patrolmoves);
        loc = myGrid.getLocationByCoordinates(4, 4);
        entityManager.setLocation(dv, loc);
        dv.setSymbol("V");
    }

}

	
