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

/**
 * Command to attack entities.
 * 
 * This affordance is attached to all weak minded entities
 * 
 * @author croft1
 */

public class Obey extends SWAffordance implements SWActionInterface {

	
	/**
	 * Constructor for the <code>Obey</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>Obey</code> a priority of 1 (lowest priority is 0).
	 * 
	 * @param theTarget the target being subject to mind control
	 * @param m message renderer to display messages
	 */
	public Obey(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>Obey</code> action.
	 * 
	 * @return The duration of the Obey action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	
	/**
	 * A String describing what this <code>Obey</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "Obey " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return this.target.getShortDescription() + " will obey your command, master.";
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can attack the target.
	 * 
	 * @author 	croft1
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> that tried to perform obey must be a <code>SWForceActor</code> and
	 * 	also have the necessary force power requirements to perform the mind control
	 * @return false if not a forceactor, and not enough force power will print a message 
	 */
	@Override
	public boolean canDo(SWActor a) {
		if(a instanceof SWForceActor){
			if ( ((SWForceActor)a).getForcePower() > SWForceEntityInterface.MINDCONTROL_FORCE_PWR_REQ //has the force power requirement
					&& ((SWForceActor)a).getForceCharge() > SWForceEntityInterface.MINDCONTROL_CHARGE_USE //has enough charge
					&& ((SWForceActor)a).hasCapability(Capability.MIND_CONTROL)){	//has the capability
				return true;
			}

			//just for text output
			String say = "My power with The Force isn't strong enough for mind control right now";
			if(((SWForceActor)a).getForceCharge() > SWForceEntityInterface.MINDCONTROL_CHARGE_USE){
				say = "I need to wait and recharge to use that again";
			}
			a.say(target.getShortDescription() + " seems weak minded.\n"
					+ say +
					"[" + ((SWForceActor)a).getForcePower() + "/" + SWForceEntityInterface.MINDCONTROL_FORCE_PWR_REQ + "]");
		}
		return false;
	}

	
	/**
	 * Perform the <code>Obey</code> command on an entity.
	 * <p>
	 * This method does not make a user control the mind of a subject if
	 * <ul>
	 * 	<li>The target of the <code>Obey</code> and the <code>SWForceActor a</code> are both a force actor ></li>
	 * 	<li>The <code>SWForceActor a</code> contains inadquate force power</li>
	 * </ul>
	 * <p>
	 
	 * @author 	mewc
	 * @param 	a the <code>SWActor</code> who is attacking
	 * @pre 	this method should only be called if the <code>SWActor a</code> is a <code>SWForceActor a</code>

	 */
	@Override
	public void act(SWActor a) {
		Obey temp = this;
		target.removeAffordance(this);
		a.say("#  Your force strength allows you to MIND CONTROL " + target.getLongDescription() + ".\nChoose Movement:");
		if(target instanceof SWActor){
			((SWForceActor)a).getScheduler().schedule(SWGridController.getUserDecision(a), (SWActor) target, 1);
			target.addAffordance(temp);
			((SWForceActor)a).useCharge(SWForceEntityInterface.MINDCONTROL_CHARGE_USE);
		}
		

		

		
	}
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
