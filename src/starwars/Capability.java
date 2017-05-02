package starwars;
/**
 * Capabilities that various entities may have.  This is useful in <code>canDo()</code> methods of 
 * <code>SWActionInterface</code> implementations.
 *  
 * @author 	ram
 * @see 	SWActionInterface
 * @see     starwars.entities.Fillable
 */

public enum Capability {
	CHOPPER,//CHOPPER capability allows an entity to Chop another entity which has the Chop Affordance
	WEAPON,//WEAPON capability allows an entity to Attack another entity which has the Attack Affordance
	FILLABLE,//FILLABLE capability allows an entity to be refilled by another entity that 
	            // has the Dip affordance.  Any FILLABLE Entity MUST implement the Fillable interface
	DRINKABLE,//DRINKABLE capability allows an entity to be consumed by another entity 
	
	FORCE_CONTROL, //MIND_CONTROL capability allows an entity with the force to attempt to mind control those without it
	FORCE_RESIST,	//FORCE_RESIST capability allows an entity with the force to resist attempted mind control from another entity
	
}