package starwars;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
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
public abstract class SWWorld extends World {

	protected String name;

	/**
	 * <code>SWGrid</code> of this <code>SWWorld</code>
	 */
	protected SWGrid myGrid;

	
	/**The entity manager of the world which keeps track of <code>SWEntities</code> and their <code>SWLocation</code>s*/
	protected static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();
	
	/** the universe that the world belongs to */
	protected SWUniverse universe;
	
	/* Boolean depicting if the world has been initialised yet  */
	protected boolean isInitialised = false;

	/** 
	 * Returns the height of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int height() {
		return space.getHeight();
	}
	
	/** 
	 * Returns the width of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int width() {
		return space.getWidth();
	}
	
	/**
	 * Set up the world, setting descriptions for locations and placing items and actors
	 * on the grid.
	 * 
	 * @author 	ram
	 * @param 	iface a MessageRenderer to be passed onto newly-created entities
	 */
	public void initializeWorld(MessageRenderer iface) {

	}
	

	/*
	 * Render method was removed from here
	 */
	
	/**
	 * Determine whether a given <code>SWActor a</code> can move in a given direction
	 * <code>whichDirection</code>.
	 * 
	 * @author 	ram
	 * @param 	a the <code>SWActor</code> being queried.
	 * @param 	whichDirection the <code>Direction</code> if which they want to move
	 * @return 	true if the actor can see an exit in <code>whichDirection</code>, false otherwise.
	 */
	public boolean canMove(SWActor a, Direction whichDirection) {
		SWLocation where = (SWLocation)entityManager.whereIs(a); // requires a cast for no reason I can discern
		return where.hasExit(whichDirection);
	}
	
	/**
	 * Accessor for the grid.
	 * 
	 * @author ram
	 * @return the grid
	 */
	public SWGrid getGrid() {
		return myGrid;
	}

	/**
	 * Move an actor in a direction.
	 * 
	 * @author ram
	 * @param a the actor to move
	 * @param whichDirection the direction in which to move the actor
	 */
	public void moveEntity(SWActor a, Direction whichDirection) {
		
		//get the neighboring location in whichDirection
		Location loc = entityManager.whereIs(a).getNeighbour(whichDirection);
		
		// Base class unavoidably stores superclass references, so do a checked downcast here
		if (loc instanceof SWLocation)
			//perform the move action by setting the new location to the the neighboring location
			entityManager.setLocation(a, (SWLocation) entityManager.whereIs(a).getNeighbour(whichDirection));
	}

	/**
	 * Returns the <code>Location</code> of a <code>SWEntity</code> in this grid, null if not found.
	 * Wrapper for <code>entityManager.whereIs()</code>.
	 * 
	 * @author 	ram
	 * @param 	e the entity to find
	 * @return 	the <code>Location</code> of that entity, or null if it's not in this grid
	 */
	public Location find(SWEntityInterface e) {
		return entityManager.whereIs(e); //cast and return a SWLocation?
	}

	/**
	 * This is only here for compliance with the abstract base class's interface and is not supposed to be
	 * called.
	 */

	@SuppressWarnings("unchecked")
	public EntityManager<SWEntityInterface, SWLocation> getEntityManager() {
		return SWWorld.getEntitymanager();
	}

	/**
	 * Returns the <code>EntityManager</code> which keeps track of the <code>SWEntities</code> and
	 * <code>SWLocations</code> in <code>SWWorld</code>.
	 * 
	 * @return 	the <code>EntityManager</code> of this <code>SWWorld</code>
	 * @see 	{@link #entityManager}
	 */
	public static EntityManager<SWEntityInterface, SWLocation> getEntitymanager() {
		return entityManager;
	}
	
	/**
	 * Returns the name of this <code>SWWorld</code> 
	 * 
	 * @return 	the name of the <code>SWWorld</code>
	 */
	public String getWorldName() {
		return this.name;
	}
	
	/**
	 * Returns the universe that this <code>SWWorld</code> belongs to
	 * 
	 * @return 	the SWUniverse instance this <code>SWWorld</code> belongs to
	 */
	public SWUniverse getUniverse() {
		return this.universe;
	}
	
	public boolean getIsInitialised() {
		return isInitialised;
	}
	
	public void setIsInitialised(boolean setIsInit) {
		this.isInitialised = setIsInit;
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