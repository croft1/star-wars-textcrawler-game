package starwars;

import edu.monash.fit2099.simulator.matter.EntityInterface;


/**
 * All <code>ForceEntities</code> and <code>ForceActors</code> in the starwars client package should implement this interface.
 * 
 * It allows them to be managed by the <code>EntityManager</code> and perform force related functions and behaviours
 * 
 * we set the default power requirements for certain objects, like mind control and wielding a force weapon.
 * 
 * @author ram
 * @see	edu.monash.fit2099.simulator.matter.EntityInterface
 * @see edu.monash.fit2099.simulator.matter.EntityManager
 */
public interface SWForceEntityInterface extends EntityInterface {

	
	public static final int WIELD_FORCE_PWR_REQ = 25;
	public static final int MINDCONTROL_FORCE_PWR_REQ = 10;
	
	
}