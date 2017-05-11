package starwars;

import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.time.Scheduler;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.Force;
import starwars.entities.LightSaber;

/**
 * This class represents "FORCE ACTORS" whom are people posessing the power of the force
 * These people are able to perform the obey command on weak minded people (non force posessors) 
 * You start with a force power of 5 and are able to train that up as you progress to unlock certain traits like
 * being able to pweform MindControl and wielding a lightsabre
 * @author Michael Carter
 *
 */
public abstract class SWForceActor extends SWActor implements SWForceEntityInterface {

	
	
	/**The set actors entity of the <code>Force </code> of this <code>SWActor</code>*/
	private Force force = null;
	
	private String[] titles = {" the Wanderer"," the Enlightened ", " the Jedi ", " the Master Jedi ", " the CHOSEN ONE "};
	
	/** 
	 * Protected constructor to prevent random other code
	 * 
	 * @param team
	 * @param hitpoints
	 * @param m
	 * @param world
	 */
	
	protected SWForceActor(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		Force defaultForce = new Force(m, 5);
		setForce(defaultForce);
		for (Affordance affEntity : this.getAffordances()) {
			if (affEntity.getDescription().contains("obey")) {
				this.removeAffordance(affEntity);		//removes the obey affordance from actor
			}
		}
		
	}

	

	
	/**
	 * Returns whether the SWActor has the <code>Force</code>.
	 * 
	 * @return 	the boolean of force presence in an <code>SWActor</code> 
	 * @see 	#force
	 */
	
	public boolean hasForce() {
		return (force != null);
	}
	
	
	
	
	



	/**
	 * Returns  the  <code>Title</code> of this Force Actor according to their forcePower.
	 * 
	 * @return 	the string of the force actors name 
	 */
	@Override
	public String getShortDescription() {
		
		return super.getShortDescription() + this.getTitle() ;
	}
	




	/**
	 * Returns the points of the <code>Force</code> in terms of strength
	 * 
	 * @return 	the boolean of force presence in an <code>SWActor</code> 
	 * @see 	#force
	 */
	
	public int getForcePower() {
		return (force != null)? force.getPower() : -1;
	}
	
	
	/**
	 * Sets the <code>Force</code> of the <code>SWActor</code>.
	 * <p>
	 * Useful when the <code>SWActor</code>'s team needs to change dynamically during the simulation.
	 * For example, a bite from an evil actor makes a good actor bad.
	 *
	 * @param 	force the force of this <code>SWActor</code>
	 * @see 	#force
	 */
	protected void setForce(Force force) {
		this.force = force;
	}                                                                                                       
	
	
	/**
	 * Incrememts the <code>ForcePower</code> in terms of strength
	 * 
	 * @see 	#force
	 * @see 	#forcePower
	 */
	public void trainForce(){	
			force.trainPower();	
	}	
	
	
	/**
	 * Returns the string of the SWForceActors title
	 * 
	 * @see 	#force
	 * @see 	#forcePower
	 */
	protected String getTitle(){
		return titles[getForcePower() / 20];
	}
	
	//needed for moving other players on mind control
	public Scheduler getScheduler(){
		return scheduler;
	}
	
	
}
