package starwars;

import edu.monash.fit2099.simulator.matter.EntityInterface;


/**
 * All <code>Entities</code> and <code>Actors</code> in the starwars client package should implement this interface.
 * 
 * It allows them to be managed by the <code>EntityManager</code>.
 * 
 * @author ram
 * @see	edu.monash.fit2099.simulator.matter.EntityInterface
 * @see edu.monash.fit2099.simulator.matter.EntityManager
 */
public interface SWForceEntityInterface extends EntityInterface {

	
	public static final int WIELD_FORCE_PWR_REQ = 20;
	
	
}