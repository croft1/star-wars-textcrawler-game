package starwars;

import edu.monash.fit2099.simulator.matter.EntityInterface;


/**
 * All <code>ForceEntities</code> and <code>ForceActors</code> in the starwars client package should implement this interface.
 * 
 * It allows them to be managed by the <code>EntityManager</code> and perform force related functions and behaviours
 * 
 * we set the default power requirements for certain objects, like mind control and wielding a force weapon.
 * 
 * @author ram
 * @see	edu.monash.fit2099.simulator.matter.EntityInterface
 * @see edu.monash.fit2099.simulator.matter.EntityManager
 */
public interface SWForceEntityInterface extends EntityInterface {

	
	public static final int WIELD_FORCE_PWR_REQ = 25;
	public static final int MINDCONTROL_FORCE_PWR_REQ = 10;
	public static final int CHOKE_FORCE_PWR_REQ = 5;
	public static final int CHOKE_FORCE_DMG = 50;

	public static final int MINDCONTROL_CHARGE_USE = 25;
	public static final int CHOKE_CHARGE_USE = 0;

	public static final int LIGHTSIDE_MAX = 100;
	public static final int DARKSIDE_MAX = -100;
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