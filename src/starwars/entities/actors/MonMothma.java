package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;


/**
 * Class for Mon Mothma SWActors
 * 
 * A Humanoid character who resides on Tatooine.
 * 
 * @author jas
 * @author mewc
 */
public class MonMothma extends SWActor {

	//Private String variable name
	private String name;

	/**
	 * General Ackbar Mon Mothma
	 * 
	 * Implements a new instance of Mon Mothma into the world - placed into the 
	 * current world defined in its parameters.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that Mon Mothma will be placed in
	 */
	public MonMothma(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world);
		// TODO Auto-generated constructor stub
		this.name = "Mon Mothma";

	}

	/**
	 * act() method for Mon Mothma General Ackbar
	 * 
	 * Implements the actions defined that will be taken by this Mon Mothma instance.
	 * 
	 * Mon Mothma, along with Genral Ackbar, will be waiting at the moon Yavin IV for their
	 * General (Leia) - which wins the game. If Luke visits the moon before Leia and R2-D2 is
	 * followed, Mothma will comment to the 'farmboy' to bring back Leia as soon as possible!
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
	 * Returns the name of this SWActor (Mon Mothma), which is a String.
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
	 * Returns the name of this SWActor (Mon Mothma), which is a String (calls getShortDescrription()).
	 * 
	 * @return	name	The name of this SWActor, as a String 
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	//Private describeLocation() method
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		Boolean winConditionMet = false;
		String says = " says: 'What are you doing here, farmboy!? Bring us \nGeneral Organa (Princess Leia) and the plans!'";
		if (winConditionMet){
			says += " YOU WIN!";
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
