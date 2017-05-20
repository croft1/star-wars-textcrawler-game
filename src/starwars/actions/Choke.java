package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;

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
public class Choke extends SWAffordance implements SWActionInterface {


	/**
	 * Constructor for the <code>Mind Control</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>MindControl</code> a priority of 1 (lowest priority is 0).
	 *
	 * @param theTarget the target being attacked
	 * @param m message renderer to display messages
	 */
	public Choke(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>Attack</code> action.
	 * 
	 * @return The duration of the Attack action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	
	/**
	 * A String describing what this <code>Attack</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "attack " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return "mind control " + this.target.getShortDescription();
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can attack the target.
	 * 
	 * @author 	dsquire
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true any <code>SWActor</code> can always try an attack, it just won't do much 
	 * 			good unless this <code>SWActor a</code> has a suitable weapon.
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	
	/**
	 * Perform the <code>Mind Control</code> command on an entity.
	 * <p>
	 * This method does not perform any damage (an attack) if,
	 * <ul>
	 * 	<li>The target of the <code>Mind Control</code> doesnt posess the force</li>
	 * 	<li>The <code>SWForceActor a</code> has the required forcePower to perform the action</li>
	 * </ul>
	 * <p>
	 * else it would damage the entity attacked, tires the attacker, and blunts any weapon used for the attack.
	 * 

	 * @author 	croft1

	 */
	@Override
	public void act(SWActor a) {
		
		//TODO IMPLEMENT TESTING FORCE CHOKE
		SWEntityInterface target = this.getTarget();
		boolean targetIsActor = target instanceof SWActor;
		SWActor targetActor = null;
		int energyForAttackWithWeapon = 1;//the amount of energy required to attack with a weapon
		
		if (targetIsActor) {
			targetActor = (SWActor) target;
		}
					
		
		if (targetIsActor && (a.getTeam() == targetActor.getTeam())) { //don't attack SWActors in the same team
			a.say("\t" + a.getShortDescription() + " says: Silly me! We're on the same team, " + target.getShortDescription() + ". No harm done");
		}
		else if (a.isHumanControlled() // a human-controlled player can attack anyone
			|| (targetIsActor && (a.getTeam() != targetActor.getTeam()))) {  // others will only attack actors on different teams
				
			a.say(a.getShortDescription() + " is attacking " + target.getShortDescription() + "!");
			
			SWEntityInterface itemCarried = a.getItemCarried();
			if (itemCarried != null) {//if the actor is carrying an item 
				if (itemCarried.hasCapability(Capability.WEAPON)) {
					target.takeDamage(itemCarried.getHitpoints() + 1); // blunt weapon won't do much, but it will still do some damage
					itemCarried.takeDamage(1); // weapon gets blunt
					a.takeDamage(energyForAttackWithWeapon); // actor uses energy to attack
				}
				else {//an attack with a none weapon
					if (targetIsActor) {
						targetActor.say("\t" + targetActor.getShortDescription()
								+ " is amused by " + a.getShortDescription()
								+ "'s attempted attack with "
								+ itemCarried.getShortDescription());
					}
				} 
			}
			else { // attack with bare hands
				target.takeDamage((a.getHitpoints()/20) + 1); // a bare-handed attack doesn't do much damage.
				a.takeDamage(2*energyForAttackWithWeapon); // actor uses energy. It's twice as tiring as using a weapon
			}
			
			
			
			//After the attack
			
			if (a.isDead()) {//the actor who attacked is dead after the attack
							
				a.setLongDescription(a.getLongDescription() + ", that died of exhaustion while attacking someone");
				
				//remove the attack affordance of the dead actor so it can no longer be attacked
				a.removeAffordance(this);
				
				
			}
			if (this.getTarget().getHitpoints() <= 0) {  // can't use isDead(), as we don't know that the target is an actor
				target.setLongDescription(target.getLongDescription() + ", that was killed in a fight");
							
				//remove the attack affordance of the dead actor so it can no longer be attacked
				targetActor.removeAffordance(this);

				
			}
		} // not game player and different teams
		
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
