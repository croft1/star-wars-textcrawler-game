package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.Mace;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

import java.util.ArrayList;

/**
 * Class for General Ackbar SWActors
 * 
 * A Humanoid character who resides on Tatooine. A general of the Rebel forces!
 * 
 * @author jas
 * @author mewc
 */
public class GeneralAckbar extends SWActor {

	//Name private variable
	private String name;

	/**
	 * General Ackbar constructor
	 * 
	 * Implements a new instance of General Ackbar into the world - placed into the 
	 * current world defined in its parameters.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that General Ackbar will be placed in
	 */
	public GeneralAckbar(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "General Ackbar";

	}

	/**
	 * act() method for General Ackbar
	 * 
	 * Implements the actions defined that will be taken by this Genral Ackbar instance.
	 * 
	 * General Ackbar , along with Mon Mothma, will be waiting at the moon Yavin IV for their
	 * General (Leia) - which wins the game. If Luke visits the moon before Leia and R2-D2 is
	 * followed, Ackbar has a 10% of yelling uncontrollably!
	 *  
	 */
	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		say(describeLocation());
	}

	/**
	 * getShortDescription() method
	 * 
	 * Returns the name of this SWActor (Ackbar), which is a String.
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getShortDescription() {
		return name;
	}

	/**
	 * getLongDescription() method
	 * 
	 * Returns the name of this SWActor (Ackbar), which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	//Private function describeLocation()
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		String says = " looks at you curiously";
		if (Math.random() >= 0.9){
			says += " then bellows: it's a Trap!";
		}

		return this.getShortDescription() + says;
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
