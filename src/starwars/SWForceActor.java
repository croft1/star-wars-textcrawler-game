package starwars;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.Force;

/**
 * This class represents "legends" - major characters - in the Star Wars universe.  
 * They use a variation of the Singleton
 * pattern to ensure that only ONE of each legend can exist.
 * 
 * Subclasses are intended to contain a static instance which represents the one
 * and only instance of the subclass.  
 * 
 * Subclasses should implement their own "getLegendClass" method that returns 
 * the single instance. There is no abstract method for this to avoid an 
 * unnecessary downcast.
 * 
 * To prevent SWLegends acting until intended, this abstract class implements
 * an API for activating them when getInstance is called.
 * 
 * Rather than implement act() like regular SWActors, Legends should implement
 * legendAct().  
 * 
 * TODO fix writeup here
 * 
 * @author Michael Carter
 *
 */
public abstract class SWForceActor extends SWActor implements SWForceEntityInterface {

	
	
	/**The set actors entity of the <code>Force </code> of this <code>SWActor</code>*/
	private Force force = null;
	
	private String[] titles = {" the Lost "," the Enlightened ", " the Jedi ", " the Master Jedi ", " the CHOSEN ONE "};
	
	/** 
	 * Protected constructor to prevent random other code from creating 
	 * SWLegends or their descendents.
	 * @param team
	 * @param hitpoints
	 * @param m
	 * @param world
	 */
	
	protected SWForceActor(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		Force defaultForce = new Force(m, 5);
		setForce(defaultForce);
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
	
	
	
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public String getShortDescription() {
		
		return super.getShortDescription() + this.getTitle() ;
	}




	/**
	 * Returns the points of the <code>Force</code> in terms of strength
	 * 
	 * @return 	the boolean of force presence in an <code>SWActor</code> 
	 * @see 	#force
	 * @see 	#isDead()
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
	public void setForce(Force force) {
		this.force = force;
	}
	

	/**
	 * Attempts to mindcontrol with the <code>Force</code>
	 * @see 	#force
	 */
	public void tryForce() {
		//add in the attack code
	}

	protected void forceAct(){
		
	}
	
	protected void trainForce(SWForceActor target){	
			target.trainForce(target);
			
	}
	
	protected void useLightsaber(){
		
	}
	
	protected void setTitle(int forcePower){
		
	}
	
	protected String getTitle(){
		return titles[getForcePower() / 20];
	}
}
