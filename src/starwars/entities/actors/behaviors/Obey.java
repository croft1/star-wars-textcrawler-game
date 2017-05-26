package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import edu.monash.fit2099.simulator.space.Direction;
import starwars.swinterfaces.SWGridController;

/**
 * Class for Stormtroopers
 * 
 * A soldier of the Empire. Will protect Vader at all costs - will try to call for backup if needed
 * also, which can be a problem for the player!
 * 
 * @author jas
 * @author mewc
 */
public class Obey {

	private ArrayList<Direction> moves;
	private int position = 0;
	
	/**
	 * Obey(moves) method for actor behaviour
	 * 
	 * Sets the list of available moves that this actor can do.
	 * 
	 * @param 	moves	- A Array of Directions that this actor can traverse.
	 */
	public Obey(Direction [] moves) {
		this.moves = new ArrayList<Direction>(Arrays.asList(moves));
	
	}
	/**
	 * Obey(moves) method for actor behaviour
	 * 
	 * Sets the list of available moves that this actor can do.
	 * 
	 * @param 	moves	- A Collection of Directions that this actor can traverse.
	 */
	public Obey(Collection<Direction> moves) {
		this.moves = new ArrayList<Direction>(moves);
	}
	
	
}
