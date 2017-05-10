package starwars.entities.actors;

import java.util.List;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWLegend;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Leave;
import starwars.actions.MindControl;
import starwars.actions.Move;
import starwars.actions.Take;
import starwars.actions.TrainForce;
import starwars.entities.Canteen;
import starwars.entities.Force;
import starwars.entities.LightSaber;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.entities.actors.behaviors.Patrol;

/**
 * Ben (aka Obe-Wan) Kenobi.  
 * 
 * At this stage, he's an extremely strong critter with a <code>Lightsaber</code>
 * who wanders around in a fixed pattern and neatly slices any Actor not on his
 * team with his lightsaber.
 * 
 * Note that you can only create ONE Ben, like all SWLegends.
 * @author rober_000
 *
 */
public class BenKenobi extends SWLegend  {

	private static BenKenobi ben = null; // yes, it is OK to return the static instance!
	private Patrol path;
	private boolean trainingPupil = false;
	
	private BenKenobi(MessageRenderer m, SWWorld world, Direction [] moves) {
		super(Team.GOOD, 1000, m, world);
		path = new Patrol(moves);
		this.setShortDescription("Ben Kenobi");
		this.setLongDescription("Ben Kenobi, an old man who has perhaps seen too much");
		LightSaber bensweapon = new LightSaber(m);
		setItemCarried(bensweapon);
		Force bensForce = new Force(m, 79);	//80+ means hes the chosen one
		setForce(bensForce);
		
		this.addAffordance(new TrainForce(this, m));	//allow those with the force to perform mindcontrol
		
	}

	public static BenKenobi getBenKenobi(MessageRenderer m, SWWorld world, Direction [] moves) {
		ben = new BenKenobi(m, world, moves);
		ben.activate();
		return ben;
	}
	
	@Override
	protected void legendAct() {

		if(isDead()) {
			return;
		}
		
		//Ben healing priorities...
		
		//When Ben (Obiwan) is not at 100% health..
		if (this.getHitpoints() != this.getInitialHP()) {
			say("Ben isnt feeling good. Needs the power of H20.");
			
			//See if Ben is nearby a water canteen that is FULL		
			List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
						
			//Analyse items and entities around Ben
			if (contents.size() > 1) { // if it is equal to one, the only thing here is this Player, so there is nothing to report
				for (SWEntityInterface entityBen : contents) {
					if (entityBen != this) { // don't include self in scene description
						if (entityBen.getSymbol() == "o") {
							
							//Cast enetity as a Canteen to find its level.
							Canteen foundCanteen = (Canteen) entityBen;
							
							if (foundCanteen.getLevel() == 10)
							{
								say("found a canteen full");
								//Drop current item
								if (this.getItemCarried() != null)
								{
									say("doing a leave");
									say(this.getItemCarried().getShortDescription());
									Leave benIitemLeave = new Leave(this.getItemCarried(), messageRenderer);
									scheduler.schedule(benIitemLeave, this, 1);
								}
								else
								{
									say(this.getShortDescription() + " found a canteen that is full.\nHe decided to pick it up.");
									//Droid takes the oil can. Scheduler implements the Take.
									Take benTakes = new Take(entityBen, messageRenderer);
									scheduler.schedule(benTakes, this, 1);
								}
								
							}
							else 
							{
								say(this.getShortDescription() + " found a canteen that isnt full.\nHe decided not to pick it up.");
								return;
							}
						}
					}	
				}
			}	
			
			//Drink from the canteen
			
			
			
		}
		
		//Ben attacking neighbours
		AttackInformation attack;
		attack = AttackNeighbours.attackLocals(ben,  ben.world, true, true);
		
		if (attack != null) {
			say(getShortDescription() + " suddenly looks sprightly and attacks " +
		attack.entity.getShortDescription());
			scheduler.schedule(attack.affordance, ben, 1);
			
		}
		else {
			if(trainingPupil){
				trainingPupil = false;
			} else{
				
			
			Direction newdirection = path.getNext();
			say(getShortDescription() + " moves " + newdirection);
			Move myMove = new Move(newdirection, messageRenderer, world);
			scheduler.schedule(myMove, this, 1);
			}
		}
			
	}
	
	public boolean isTrainingPupil(){
		return trainingPupil;
	}
	
	public void setTrainingPupil(boolean t){
		trainingPupil = t;
	}




}
