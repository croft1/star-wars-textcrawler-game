package starwars.entities;

import java.util.HashSet;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;

import starwars.SWEntity;
import starwars.actions.MindControl;
import starwars.actions.Take;

/**
 * An entity that has the <code>FORCE</code> attribute and so can
 * be used to <code>MindControl</code> others, <code>TrainForce</code> and use force entities.
 * 
 * @author 	croft1
 * @see 	{@link starwars.actions.TrainForce}
 * @see 	{@link starwars.actions.MindControl}
 * @see 	{@link starwars.entities.LightSaber}
 */
/*
 *
 */
public class Force extends SWEntity {
	
	
	private int power;

	
	/**
	 * Constructor for the <code>Blaster</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Force</code></li>
	 * 	<li>Set the short description of this <code>Force</code> to "the Force"</li>
	 * 	<li>Set the long description of this <code>Force</code> to "A mysterious presence can be felt"</li>
	 * 	<li>Set the strength point of the <code>Force</code> to 0</li> 
	 * 	<li>Add a <code>Take</code> affordance to this <code>Blaster</code> so it can be taken</li> 
	 *	<li>Add a <code>MIND_CONTROL Capability</code> to this <code>FORCE</code> so it can be used to <code>MIND CONTROL</code></li>
	 * 	
	 * </ul>
	 * 
	 * @param m <code>MessageRenderer</code> to display messages.
	 * 
	 * @see {@link starwars.actions.Take}
	 * @see {@link starwars.Capability}
	 */
	public Force(MessageRenderer m, int power) {
		super(m);
		
		this.shortDescription = "The Force";
		this.longDescription = "A mysterious presence can be felt";
		this.power = power; // hit points are the strength/level of the current force entity
		
		//TODO add training this.addAffordance(new Take(this, m));//add the Take affordance so that the blaster can be picked up
		//TODO maybe -- add affordance to convert to the dark side of the force
		this.capabilities.add(Capability.MIND_CONTROL);   // and WEAPON so that it can be used to attack					
		
		this.addAffordance(new MindControl(this, m));	//allow those with the force to perform mindcontrol
		
	}
	
	
	public int getPower() {
		return power;
	}

	
	
	/**
	 * 
	 * 
	 * @return 	Single Character string "s"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public void trainPower() {
		this.power += 1;
	}


	/**
	 * A symbol that is used to represent the Blaster on a text based user interface
	 * 
	 * @return 	Single Character string "s"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public String getSymbol() {
		return "f"; 
	}
	
	
	
	

	
	
	

}
