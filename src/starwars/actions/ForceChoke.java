package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWForceEntityInterface;
import starwars.swinterfaces.SWGridController;

import static starwars.SWForceEntityInterface.CHOKE_CHARGE_USE;
import static starwars.SWForceEntityInterface.CHOKE_FORCE_DMG;

/**
 * Command to attack entities using the Force Choke Attack.
 *
 * This affordance is attached to all weak minded entities
 *
 * @author mewc
 */

public class ForceChoke extends SWAffordance implements SWActionInterface {


	/**
	 * Constructor for the <code>ForceChoke</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>ForceChoke</code> a priority of 1 (lowest priority is 0).
	 *
	 * @param theTarget the target being subject to mind control
	 * @param m message renderer to display messages
	 */
	public ForceChoke(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 3;
	}


	/**
	 * Returns the time is takes to perform this <code>ForceChoke</code> action.
	 *
	 * @return The duration of the ForceChoke action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}


	/**
	 * A String describing what this <code>ForceChoke</code> action will do, suitable for display on a user interface
	 *
	 * @return String comprising "ForceChoke " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return this.target.getShortDescription() + " will ForceChoke your command, master.";
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can attack the target.
	 *
	 * @author 	mewc
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> that tried to perform ForceChoke must be a <code>SWForceActor</code> and
	 * 	also have the necessary force power requirements to perform the mind control
	 * @return false if not a forceactor, and not enough force power will print a message
	 */
	@Override
	public boolean canDo(SWActor a) {
		if(a instanceof SWForceActor){
			if ( ((SWForceActor)a).getForcePower() > SWForceEntityInterface.CHOKE_FORCE_PWR_REQ
					&& ((SWForceActor)a).getForceCharge() > CHOKE_CHARGE_USE
					&& (a).hasCapability(Capability.CHOKE)){
				return true;
			}

			//just for text output
			String say = "My power with The Force isn't strong enough for CHOKE right now";
			if(((SWForceActor)a).getForceCharge() > CHOKE_CHARGE_USE){
				say = "I need to wait and recharge to use that again";
			}
			a.say(target.getShortDescription() + " is weak..\n"
					+ say +
					"[" + ((SWForceActor)a).getForcePower() + "/" + SWForceEntityInterface.CHOKE_FORCE_PWR_REQ + "]");
		}
		return false;
	}


	/**
	 * Perform the <code>ForceChoke</code> command on an entity.
	 * <p>
	 * This method does not make a user control the mind of a subject if
	 * <ul>
	 * 	<li>The target of the <code>ForceChoke</code> and the <code>SWForceActor a</code> are both a force actor ></li>
	 * 	<li>The <code>SWForceActor a</code> contains inadquate force power</li>
	 * </ul>
	 * <p>

	 * @author 	mewc
	 * @param 	a the <code>SWActor</code> who is attacking
	 * @pre 	this method should only be called if the <code>SWActor a</code> is a <code>SWForceActor a</code>

	 */
	@Override
	public void act(SWActor a) {

		if(target instanceof SWActor && Math.random() > 0.5){   //50% chance to CHOKE  ANY actor
			((SWForceActor)a).getScheduler().schedule(
			        SWGridController.getUserDecision(a), (SWActor) target, 1);

			((SWForceActor)a).useCharge(CHOKE_CHARGE_USE); //0 by default
			((SWActor) target).takeDamage(CHOKE_FORCE_DMG); //50
            a.say("Bow to the strength of the dark side -- FORCE CHOKE!");
            target.say("I SUBMIT! STOP CHOKING");

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
