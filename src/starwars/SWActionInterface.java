package starwars;

import edu.monash.fit2099.simulator.matter.ActionInterface;

/**
 * Interface that allows <code>SWAction</code> and <code>SWAffordance</code> to have a common ancestor (and thus
 * be stored in the same structures) despite one extending <code>Action</code> and the other extending <code>Affordance</code>.
 * 
 * @author ram
 */
public interface SWActionInterface extends ActionInterface {
	
	/**Returns if or not the action is a move command. Returns true if so, false otherwise*/
	public boolean isMoveCommand();
	
	/**
	 * Returns if the given <code>SWActor a</code> can perform this action
	 * 
	 * @param 	a the <code>SWActor</code> being queried
	 * @return	true if <code>a</code> can perform this action, false otherwise
	 */	
	public boolean canDo(SWActor a);
	
	/**
	 * The method that defines what needs to be performed when this action is performed.
	 * 
	 * @param a the <code>SWActor</code> who performs this action.
	 */
	public void act(SWActor a);	
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