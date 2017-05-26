package starwars;

import java.util.ArrayList;
import java.util.HashMap;

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
 * Class representing a universe of the Star Wars franchise. The <code>SWUniverse</code> depicts
 * the planets and spacecraft that is in within the universe as of now.
 * 
 * @author ram
 * @author jas
 * @author mewc
 * 
 */
public class SWUniverse {

	private String name;

	private ArrayList<SWWorld> worldsInUniverse;
	
	private ArrayList<SWLocation> MFLocations;
	
	private SWWorld activeWorld;
	
	/**
	 * Constructor for this <code>SWUniverse</code>. This constructor creates the universe with 
	 * a name, a world Array List and a Millenium Falcon list.
	 * 
	 * @param 	giveName	the name of this <code>SWUniverse</code>.
	 */
	public SWUniverse(String givenName)
	{
		this.name = givenName;
		this.worldsInUniverse = new ArrayList<SWWorld>();
		this.MFLocations = new ArrayList<SWLocation>();
	}
	
	/**
	 * getWorlds() function
	 * 
	 * Returns the Array List of worlds in the specified <code>SWUniverse</code>.
	 * 
	 * @return	worldsInUniverse 	the Array List containing the SWWorlds of this universe
	 */
	public ArrayList<SWWorld> getWorlds() {
		return worldsInUniverse;
	}
	
	/**
	 * getMFList() function
	 * 
	 * Returns the Array List of Millenium Falcon locations in the specified <code>SWUniverse</code>.
	 * 
	 * @return 	MFLocations 	the Array List containing the Millenium Falcon locations of this <code>SWUniverse</code>.
	 */
	public ArrayList<SWLocation> getMFList() {
		return MFLocations;
	}

	
	/**
	 * setUniverseName(newName) function
	 * 
	 * Sets the <code>SWUniverse</code> name - if desired to change
	 * 
	 * @param 	newName		The new name of the universe to be implemented
	 */
	public void setUniverseName(String newName) {
		this.name = newName;
	}
	
	/**
	 * getUniverseName() function
	 * 
	 * Returns the String name of this <code>SWUniverse</code>.
	 * 
	 * @return 	this.name 	The String name of this <code>SWUniverse</code>.
	 */
	public String getUniverseName() {
		return this.name;
	}
	
	/**
	 * Sets the <code>SWUniverse</code>'s active world - since our implementation implements a single
	 * world display at a time. If the worlds have been visited before, time does pass on them.
	 * 
	 * @param 	newActiveWorld		The current active world of this <code>SWUniverse</code>
	 */
	public void setActiveWorld(SWWorld newActiveWorld) {
		this.activeWorld = newActiveWorld;
	}
	
	/**
	 * getActiveWorld() function
	 * 
	 * Returns the active world of this <code>SWUniverse</code>.
	 * 
	 * @return 	this.activeWorld 	The active SwWorld of the <code>SWUniverse</code>.
	 */
	public SWWorld getActiveWorld() {
		return this.activeWorld;
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