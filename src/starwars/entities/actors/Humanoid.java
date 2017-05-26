package starwars.entities.actors;

import java.util.ArrayList;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.actions.Obey;
import starwars.entities.Mace;
import starwars.entities.Rake;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

public class Humanoid extends SWActor {

	private String name;

	/**
	 * Create a Humanoid.  THey won't move and are weak minded
	 *
	 * 
	 * @param hitpoints
	 *            the number of hit points of this HJumanoid. If this
	 *            decreases to below zero, the Raider will die.
	 * @param name
	 *            this raider's name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>Humanoid</code> belongs to
	 * 
	 */
	public Humanoid(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		
		setItemCarried(new Rake(m));
		setWielding(true);
		this.addAffordance(new Obey(this, m));	//allow any humanoid to obey those with force
		
	}

	/**
	 * act() method for Humanoid Characters
	 * 
	 * Implements the actions defined that will be taken by this Humanoid instance.
	 * 
	 * Humanoids dont need to do anything apart from display messages - which essentially
	 * means no movement!  
	 */
	@Override
	public void act() {
		
	}
	
	/**
	 * getShortDescription() method
	 * 
	 * Returns the name of this Humanoid, which is a String.
	 * 
	 * @return	name	The name of this Humanoid, as a String 
	 */
	@Override
	public String getShortDescription() {
		return shortDescription ;
	}

	/**
	 * getLongDescription() method
	 * 
	 * Returns the name of this Humanoid, which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this Humanoid, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription() + " the regular Humanoid";
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
