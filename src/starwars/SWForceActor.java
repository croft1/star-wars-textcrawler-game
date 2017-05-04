package starwars;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.Force;

public class SWForceActor extends SWActor implements SWForceActorInterface {
	
	protected Force force;
	

	public SWForceActor(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		//setting the default force value meaning that
		force = new Force(m, 1);
				
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasForce() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getForcePower() {
		// TODO Auto-generated method stub
		return force.forcePower;
	}

	@Override
	public void setForce(Force force) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tryForce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trainForce(int increase) {
		if((getForcePower() + increase)>100){
			force.forcePower = 100;
		}else{
			force.forcePower += increase;
		}
	}

	@Override
	public void setForcePower(int power) {
		// TODO Auto-generated method stub
		
	}

}
