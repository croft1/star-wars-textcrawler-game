package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWForceEntityInterface;
import starwars.entities.LightSaber;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> pick up an object.
 * 
 * @author ram
 */
/*
 * Changelog
 * 2017/01/26	- candDo method changed. An actor can only take if it's not holding any items already.
 * 				- act method modified. Take affordance removed from the item picked up, since an item picked up
 * 				  cannot be taken. This is just a safe guard.
 * 2017/02/03	- Actors are no longer given a leave action after taking an item.
 * 				- Leave action was removed since students had to add this functionality. (yes there was a leave action
 * 				  but I've failed to document it here)
 * 				- canDo method changed to return true only if the actor is not carrying an item (asel)
 */
public class Wield extends SWAffordance {

	/**
	 * Constructor for the <code>Take</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being taken
	 * @param m the message renderer to display messages
	 */
	public Wield(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 2;
	}


	/**
	 * Returns if or not this <code>Wield</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is able to wield the
	 *  item (force items usually disallow) according to actor type and force strength. 
	 *  Returning true means the affordance set in act() will show up on the player input dialog
	 *  
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @author 	croft1 (8/05/2017)
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> is can take this item, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		
		return true;
		
	}

	/**
	 * Perform the <code>Take</code> action by setting the item carried by the <code>SWActor</code> to the target (
	 * the <code>SWActor a</code>'s item carried would be the target of this <code>Take</code>).
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @param 	a the <code>SWActor</code> that is taking the target
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	@Override
	public void act(SWActor a) {
		if (target instanceof SWEntityInterface) {
			SWEntityInterface theItem = (SWEntityInterface) target;
			a.setWielding(true);
			
			if(a.getItemCarried() instanceof SWForceEntityInterface 
					&& a instanceof SWForceActor ){
				if( ((SWForceActor) a).getForcePower() > SWForceEntityInterface.WIELD_FORCE_PWR_REQ){
					//a force weapon and can wield
					//remove the wield affordance
					target.removeAffordance(this);
					
					// add a leave affordance - no need to just "hold" it again
					target.addAffordance(new Leave(theItem, messageRenderer));
					
				}
				//a force weapon but not wieldable
				a.say(a.getItemCarried().getShortDescription() + " is so majestic to the touch -- you try to use it but you require more training with the ways of The Force."
						+ "\n" + a.getShortDescription() + " put " + a.getItemCarried().getShortDescription() + " away to try again later");
				
					
				
			}
			
			
			
			
			//POSSIBLY ADD A TURN ON/OFF affordance
			
		}
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author ram
	 * @return String comprising "take " and the short description of the target of this <code>Take</code>
	 */
	@Override
	public String getDescription() {
		return "wield " + target.getShortDescription();
	}

}
