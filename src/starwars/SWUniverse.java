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
public class SWUniverse {

	private String name;

	private ArrayList<SWWorld> worldsInUniverse;
	
	private SWWorld activeWorld;
	
	//Constructor for the SWUniverse
	
	public SWUniverse(String givenName)
	{
		this.name = givenName;
		this.worldsInUniverse = new ArrayList<SWWorld>();
	}
	
	
	/**
	 * Returns the worlds in the specified <code>SWUniverse</code>.
	 * 
	 * @return 	the Array List containing the SWWorlds of this universe
	 */
	public ArrayList<SWWorld> getWorlds() {
		return worldsInUniverse;
	}

	
	/**
	 * Sets the <code>SWUniverse</code> name.
	 * 
	 * @return 	the Array List containing the SWWorlds of this universe
	 */
	public void setUniverseName(String newName) {
		this.name = newName;
	}
	
	public String getUniverseName() {
		return this.name;
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