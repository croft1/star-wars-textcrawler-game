package starwars;

import java.util.HashSet;

import edu.monash.fit2099.simulator.matter.Entity;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;


/**
 * Class that represents inanimate objects in the Star Wars world that have a mysterious property to them: the force.. Objects that cannot move for example lightsabres.
 * 
 * @author 	croft1
 * @see 	edu.monash.fit2099.simulator.matter.Entity
 * @see 	SWEntityInterface
 */

public class SWForceEntity extends SWEntity implements SWForceEntityInterface {
	
	
	/**
	 * Constructor for this <code>SWForceEntity</code>. Will initialize this <code>SWForceEntity</code>'s
	 * <code>messageRenderer</code> and set of capabilities.
	 * 
	 * @param m the <code>messageRenderer</code> to display messages
	 */
	protected SWForceEntity(MessageRenderer m) {
		super(m);
		capabilities = new HashSet<Capability>();
		
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
