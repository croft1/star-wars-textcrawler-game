package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.swinterfaces.SWGridController;

import static starwars.SWForceEntityInterface.CHOKE_CHARGE_USE;
import static starwars.SWForceEntityInterface.CHOKE_FORCE_DMG;

/**
 * Command to attack entities.
 *
 * This affordance is attached to all weak minded entities
 *
 * @author croft1
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
	 * @author 	croft1
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> that tried to perform Influence must be a <code>SWForceActor</code> and
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
