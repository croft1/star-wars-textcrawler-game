package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWForceEntityInterface;
import starwars.entities.LightSaber;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> weild up a weapon with the attack affordance.
 * 
 * an actor w
 * 
 * @author ram
 */
/*

 */
public class Wield extends SWAffordance {

	/**
	 * Constructor for the <code>Wield</code> Class. Will initialize the message renderer, the target and 
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
			
			
			if(theItem instanceof SWForceEntityInterface 
					&& a instanceof SWForceActor  ){
				if( ((SWForceActor) a).getForcePower() > SWForceEntityInterface.WIELD_FORCE_PWR_REQ ){
					
					setWielding(true,a,theItem);
					
				}
				//a force weapon but not wieldable
				a.say(a.getItemCarried().getShortDescription() + " is so majestic to the touch -- you try to use it but you require more training with the ways of The Force."
						+ "\n" + a.getShortDescription() + " put " + a.getItemCarried().getShortDescription() + " away to try again later");
				//dont need to run setwielding here to maintain wield affordance
					
				
			} else{
				if(((SWEntityInterface) target).hasCapability(Capability.WEAPON)){
					setWielding(true,a,theItem);
				}
				
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
	
	private void setWielding(boolean w, SWActor a, SWEntityInterface item){
		
		a.setWielding(w);
		
		//if we want to wield, the wield affordance will be removed
		target.removeAffordance((w)?this:new Leave(item, messageRenderer));
		
		//if we want to wield, the leave affordance is added
		target.addAffordance((w)?new Leave(item, messageRenderer):this);
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
