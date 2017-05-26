/**
 * starwars.entities package
 * 
 * Initiates entities that will be able to be usable by SWActors in the Star Wars
 * roguelike game. This includes items like a LightSaber, Blaster, Water Canteen and
 * so forth!
 *
 */

package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.actions.Take;

/**
 * Class for Droid SWActors  
 * 
 * Droid Parts are used by SWActors in the need of repairing immobile Droids.
 * Droid Parts are consumed when used - so be wary which Droid you would like to
 * bring back to mobility!
 * 
 * @author jas
 * @author mewc
 *
 */
public class DroidParts extends SWEntity {

	/**
	 * Constructor for the <code>Droid Parts</code>. 
	 * 
	 * Creates some new DroidParts with predefined short and long descriptions. Also
	 * initiates a Take affordance (these can be taken by SWActors!
	 * 
	 * @param	m	- MessageRenderer that these Droid Parts will use to display notification
	 * to (mainly their description)
	 */
	public DroidParts(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "some Droid Parts";
		this.longDescription = "some Droid parts, sourced from a immobile Droid. ";
		
		this.addAffordance(new Take(this, m));//add the Take affordance so that the Droid Parts can be picked up
	}
	
	
	/**
	 * A symbol that is used to represent the Droid Parts on a text based user interface
	 * 
	 * @return 	Single Character string "dp"
	 */
	public String getSymbol() {
		return "dp"; 
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
