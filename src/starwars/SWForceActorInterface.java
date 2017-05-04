package starwars;

import starwars.entities.Force;

public interface SWForceActorInterface {
	
	/**The set actors entity of the <code>Force </code> of this <code>SWActor</code>*/
	
	
	/**
	 * Returns whether the SWActor has the <code>Force</code>.
	 * 
	 * @return 	the boolean of force presence in an <code>SWActor</code> 
	 * @see 	#force
	 */
	
	public boolean hasForce();
	/*{
		return (force.get);
	}
	*/
	
	/**
	 * Returns the points of the <code>Force</code> in terms of strength
	 * 
	 * @return 	the boolean of force presence in an <code>SWActor</code> 
	 * @see 	#force
	 * @see 	#isDead()
	 */
	
	public int getForcePower();
/*	{
		return (force != null)? force.getPower() : -1;
	}
*/	
	/**
	 * Sets the <code>Force</code> of the <code>SWActor</code>.
	 * <p>
	 * Useful when the <code>SWActor</code>'s team needs to change dynamically during the simulation.
	 * For example, a bite from an evil actor makes a good actor bad.
	 *
	 * @param 	force the force of this <code>SWActor</code>
	 * @see 	#force
	 */
	public void setForce(Force force);
	/*{
		this.force = force;
	}
	*/
	
	/**
	 * Sets the <code>Force</code> of the <code>SWActor</code>.
	 * <p>
	 * Useful when the <code>SWActor</code>'s team needs to change dynamically during the simulation.
	 * For example, a bite from an evil actor makes a good actor bad.
	 *
	 * @param 	force the force of this <code>SWActor</code>
	 * @see 	#force
	 */
	public void trainForce(int increase);
	/*{
		this.force = force;
	}
	*/

	/**
	 * Attempts to mindcontrol with the <code>Force</code>
	 * @see 	#force
	 */
	public void tryForce();
/*	{
		//add in the attack code
	}
*/	

}
