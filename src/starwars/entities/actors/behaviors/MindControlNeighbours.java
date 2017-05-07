package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.matter.EntityManager;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.MindControl;
import starwars.entities.actors.behaviors.MindControlInformation;

public class MindControlNeighbours {



	
	public static MindControlInformation controlLocals(SWActor actor, SWWorld world, boolean avoidFriendlies, boolean avoidNonActors) {
		SWLocation location = world.getEntityManager().whereIs(actor);
		EntityManager<SWEntityInterface, SWLocation> em = world.getEntityManager();
		List<SWEntityInterface> entities = em.contents(location);

		// select the attackable things that are here

		ArrayList<MindControlInformation> controllables = new ArrayList<MindControlInformation>();
		//Call can only come from a SWForceActor
		//Entity is a possible enemy
		for (SWEntityInterface e : entities) {
			// Figure out if we should be attacking this entity
			if( e != actor && 
					(e instanceof SWActor && 
							(avoidFriendlies==false || ((SWActor)e).getTeam() != actor.getTeam()) 
					|| (avoidNonActors == false && !(e instanceof SWActor)
						
					)))	 {
				for (Affordance a : e.getAffordances()) {
					
					
					//(((SWActor)e).getForcePower() > actor.getForcePower())
					
					if (a instanceof MindControl) {

						//add in possible entity to be controlled
						controllables.add(new MindControlInformation(e, a));
						
						break;
						
						
					}
				}
			}
		}

		// if there's at least one thing we can control, randomly choose
		// something to control
		if (controllables.size() > 0) {
			
			//LOGIC HERE OF CHECKING IF POSSIBLE TO PERFORM CONTROL
			
			return controllables.get((int) (Math.floor(Math.random() * controllables.size())));
		} else {
			return null;
		}
		
	}
}
