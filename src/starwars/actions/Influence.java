package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.swinterfaces.SWGridController;

import static starwars.SWForceEntityInterface.CHOKE_CHARGE_USE;
import static starwars.SWForceEntityInterface.CHOKE_FORCE_DMG;

/**
 * This Influence affordance is attached to all weak minded entities that can be 
 * swayed to turn to the Dark Side
 *
 * @author mewc
 */

public class Influence extends SWAffordance implements SWActionInterface {


	/**
	 * Constructor for the <code>Influence</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>Influence</code> a priority of 1 (lowest priority is 0).
	 *
	 * @param theTarget the target being subject to mind control
	 * @param m message renderer to display messages
	 */
	public Influence(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>Influence</code> action.
	 *
	 * @return The duration of the Influence action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}


	/**
	 * A String describing what this <code>Influence</code> action will do, suitable for display on a user interface
	 *
	 * @return String comprising "Influence " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return this.target.getShortDescription() + " must turn to the dark side.";
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can attack the target.
	 *
	 * @author 	mewc
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> that tried to perform Influence must be a <code>SWForceActor</code> and
	 * 	also have the necessary force power requirements to perform the mind control
	 * @return false if not a forceactor, and not enough force power will print a message
	 */
	@Override
	public boolean canDo(SWActor a) {
		if(a instanceof SWForceActor){
			if ( ((SWForceActor)a).hasCapability(Capability.INFLUENCE) ){ //if the actors force includes influence

				return true;
			}
		}
		return false;
	}


	/**
	 * Perform the <code>Influence</code> command on an entity.
	 * <p>
	 * This method does not make a user control the mind of a subject if
	 * <ul>
	 * 	<li>The target of the <code>Influence</code> and the <code>SWForceActor a</code> are both a force actor ></li>
	 * 	<li>The <code>SWForceActor a</code> contains inadquate force power</li>
	 * </ul>
	 * <p>

	 * @author 	mewc
	 * @param 	a the <code>SWActor</code> who is attacking
	 * @pre 	this method should only be called if the <code>SWActor a</code> is a <code>SWForceActor a</code>

	 */
	@Override
	public void act(SWActor a) {
		int influenceSwing = ((SWLegend)a).getInfluencePower();

		//influence LUKE
		if(Math.random() > ((SWForceActor)a).PROPAGATE_INFLUENCE //attempt to influence to dark side according to their propagate value (chance)
				&&  Math.random() >  ((SWForceActor)target).RESIST_INFLUENCE){ //Luke chance to resist
			((SWForceActor) target).influence(influenceSwing);
			String feedback;
			feedback = (influenceSwing>0)? "The Light Side is enhanced": "The Dark Side infects you more";
			target.say(feedback);
		}
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
