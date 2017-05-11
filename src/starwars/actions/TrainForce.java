package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.entities.actors.BenKenobi;
import starwars.entities.actors.Player;

/**
 * Command to mind control entities.
 * 
 * This affordance is attached to all attackable entities
 * 
 * 
 */
/*
 * 
 */
public class TrainForce extends SWAffordance implements SWActionInterface {

	
	/**
	 * Constructor for the <code>TrainForce</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>TrainForce</code> a priority of 1 (lowest priority is 0).
	 * 
	 * @param theTarget the target being attacked
	 * @param m message renderer to display messages
	 */
	public TrainForce(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>TrainForce</code> action.
	 * 
	 * @return The duration of the Attack action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	
	/**
	 * A String describing what this <code>TrainForce</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "attack " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return "Get training for the ways of the force from " + this.target.getShortDescription();
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can TrainForce on the target.
	 * 
	 * @author 	croft1
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> can always try an attack, it just won't do much 
	 * 			good unless this <code>SWActor a</code> has a suitable weapon.
	 */
	@Override
	public boolean canDo(SWActor a) {
		if(a instanceof SWForceActor){
			return true;
		}
		return false;
		
	}

	
	/**
	 * Perform the <code>TrainForce</code> command on an entity.
	 * <p>
	 * This method does not perform a train (an TrainForce) if,
	 * 
	 * <p>


	 * @author 	croft1 - adapted from mind control to train force
	 * @param 	a the <code>SWActor</code> who is attacking
	 * @pre 	this method should only be called if the <code>SWActor a</code> is alive
	 * @pre		an <code>Attack</code> must not be performed on a dead <code>SWActor</code>
	 * @post	if a <code>SWActor</code>dies in an <code>Attack</code> their <code>Attack</code> affordance would be removed
	 * @see		starwars.SWActor#isDead()
	 * @see 	starwars.Team
	 */
	@Override
	public void act(SWActor a) {

		//Currently only players can be trained. We can change this over time.
		if (a instanceof Player && target instanceof BenKenobi){
			target.say(a.getShortDescription() + ", prepare your mind.\nTraining Commences...");
			((BenKenobi) target).setTrainingPupil(true);
			for (int i = 0; i < Math.floor(Math.random() * 6); i++){	///random training value
				((SWForceActor)a).trainForce();
			}
			
			a.say("Thanks " + target.getShortDescription() + ", I feel closer to the ways of the force than ever!");
			
		}
		
	}
}
