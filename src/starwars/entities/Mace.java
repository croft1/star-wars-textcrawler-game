package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Take;

/**
 * An entity that has the <code>WEAPON</code> attribute and so can
 * be used to <code>Attack</code> others, etc.
 * 
 * @author 	dsquire
 * @see 	{@link starwars.entities.Reservoir}
 * @see 	{@link starwars.actions.Chop}
 * @see 	{@link starwars.actions.Attack}
 */
/*
 * a weak weapon for farmers, mostly humanoids
 */
public class Mace extends SWEntity {

	/**
	 * @author croft1  added class
	 * @see {@link starwars.actions.Take}
	 * @see {@link starwars.Capability}
	 */
	public Mace(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "a Raider Mace";
		this.longDescription = "A dirty Raider Mace";
		this.hitpoints = 25; // start with a weak Mace - it's a Mace after all
		
		this.addAffordance(new Take(this, m));//add the Take affordance so that the blaster can be picked up										//the blaster has capabilities 
		this.capabilities.add(Capability.WEAPON);   // and WEAPON so that it can be used to attack
	}
	
	
	/**
	 * A symbol that is used to represent the Mace on a text based user interface
	 * 
	 * @return 	Single Character string "s"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public String getSymbol() {
		return "m"; 
	}

	/**
	 * Method insists damage on this <code>Mace</code> by reducing a certain 
	 * amount of <code>damage</code> from this <code>Mace</code> <code>hitpoints</code>
	 * <p>
	 * This method will also change this <code>Mace</code>s <code>longDescription</code> to
	 * "A broken sword that was once gleaming"  and this <code>Blaster</code>s <code>shortDescription</code> to
	 * "a broken sword" if the <code>hitpoints</code> after taking the damage is zero or less.
	 * <p>
	 * If the <code>hitpoints</code> after taking the damage is zero or less, this method will remove the 
	 * <code>CHOPPER</code> and <code>WEAPON</code> capabilities from this <code>Mace</code> 
	 * <p>
	 * 
	 * @author 	croft1
	 * @param 	damage the amount of <code>hitpoints</code> to be reduced
	 * @see 	{@link starwars.actions.Attack}
	 */
	@Override
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		
		if (this.hitpoints<=0) {
			this.shortDescription = "an busted mace";
			this.longDescription  = "A Raider Mace that is more useful as a back scratcher than anything else at the moment";
			
			this.capabilities.remove(Capability.WEAPON);
		}
	}
	
	

}
