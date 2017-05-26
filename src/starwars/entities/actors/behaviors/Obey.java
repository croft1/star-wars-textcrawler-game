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
