package starwars.entities.actors;

import java.util.List;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntityInterface;
import starwars.SWForceActor;
import starwars.SWLegend;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.HealPlayer;
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
	private boolean wantsToHeal = false;
	private boolean recollect = false;
	
	private BenKenobi(MessageRenderer m, SWWorld world, Direction [] moves) {
		super(Team.GOOD, 1000, m, world);
		path = new Patrol(moves);
		this.setShortDescription("Ben Kenobi");
		this.setLongDescription("Ben Kenobi, an old man who has perhaps seen too much");
		//LightSaber bensweapon = new LightSaber(m);
		//setItemCarried(bensweapon);
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

		//Describes Bens location & HP to output
		say(describeLocation());
		
		if(isDead()) {
			return;
		}
		
		//Ben healing priorities...

		
		//When Ben (Obiwan) is not at 100% health..
		if (this.getHitpoints() != this.getInitialHP()) {
			say("Ben isnt feeling too good. He needs the power of H20.");
		}
			
		
		if (recollect) 
		{
			//See if Ben is nearby a water canteen that is FULL		
			List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
							
			//Analyse items and entities around Ben
			if (contents.size() > 1) 
			{ // if it is equal to one, the only thing here is this Player, so there is nothing to report
				for (SWEntityInterface entityBen : contents) 
				{
					if (entityBen != this)
					{ // don't include self in scene description
						
						char charEntity = entityBen.getSymbol().charAt(0);
						
						say("THE CHAR ENTITY IS" + charEntity);
						
						//If at full HP
						if (this.getHitpoints() == this.getInitialHP())
						{
							if(Character.isLowerCase(charEntity) && entityBen.getSymbol() == "o")
							{
								say("Ben found a water canteen, but he is at full HP!");
								recollect = false;
								return;
							}
							else if (Character.isLowerCase(charEntity) || entityBen.getSymbol() == "|")
							{
								say("Ben found a" + entityBen.getSymbol());
								Take benRetakes = new Take(entityBen, messageRenderer);
								scheduler.schedule(benRetakes, this, 0);
								recollect = false;
								return;
							}
							else
							{
								say("Ben didnt recollect anything");
								recollect = false;
								return;
							}
						}
						else
						{
							if(Character.isLowerCase(charEntity) && entityBen.getSymbol() == "o")
							{
								say("Ben found a water canteen, but he is at full HP!");
								recollect = false;
								return;
							}
							else if (Character.isLowerCase(charEntity) || entityBen.getSymbol() == "|")
							{
								say("Ben found a" + entityBen.getSymbol());
								Take benRetakes = new Take(entityBen, messageRenderer);
								scheduler.schedule(benRetakes, this, 0);
								recollect = false;
								return;
							}
							else
							{
								say("Ben didnt recollect anything");
								recollect = false;
								return;
							}
						}
				
					}
				}
			}
		}
		
		//If Ben is carrying an item (DROP IT LIKES IT HOT!!)
		if (this.getItemCarried() != null)
		{
			if (this.getItemCarried().getSymbol() != "o") 
			{
				//See if Ben is nearby a water canteen that is FULL		
				List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
								
				//Analyse items and entities around Ben
				if (contents.size() > 1) 
				{ // if it is equal to one, the only thing here is this Player, so there is nothing to report
					for (SWEntityInterface entityBen : contents) {
						if (entityBen != this) { // don't include self in scene description
							if (entityBen.getSymbol() == "o") {
								
							//Cast enetity as a Canteen to find its level.
								Canteen foundCanteen = (Canteen) entityBen;
								
								if (foundCanteen.getLevel() == 10 && (this.getHitpoints() != this.getInitialHP()))
								{
									say("Ben found a canteen thats full. He has taken damage so he will heal using the found\ncanteen. He will drop the item he is carrying: " + this.getItemCarried().getShortDescription());
									
									Leave benIitemLeave = new Leave(this.getItemCarried(), messageRenderer);
									scheduler.schedule(benIitemLeave, this, 0);							
									
									return;
				
								}
								else 
								{
									say(this.getShortDescription() + " found a canteen that isnt full or Ben is at full health.\nHe decided not to pick it up.");
								}
							}
						}	
					}
				}
			}
		}
		
		else if (this.getItemCarried() == null)
		{
		
			//See if Ben is nearby a water canteen that is FULL		
			List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
							
			//Analyse items and entities around Ben
			if (contents.size() > 1) 
			{ // if it is equal to one, the only thing here is this Player, so there is nothing to report
				for (SWEntityInterface entityBen : contents) {
					if (entityBen != this) { // don't include self in scene description
						if (entityBen.getSymbol() == "o") {
							
						//Cast enetity as a Canteen to find its level.
							Canteen foundCanteen = (Canteen) entityBen;
							
							if (foundCanteen.getLevel() == 10 && (this.getHitpoints() != this.getInitialHP()))
							{
								say("Ben found a canteen that is full. He is carrying nothing at the monemt, and\nhe has taken damage, so he will heal.");
								
								Take benTakes = new Take(foundCanteen, messageRenderer);
								scheduler.schedule(benTakes, this, 0);
								
								//Set wanting to heal = true;
								wantsToHeal = true;
								return;
							}
							else 
							{
								say(this.getShortDescription() + " found a canteen that isnt full or Ben is at full health.\nHe decided not to pick it up.");
							}
						}
					}	
				}
			}
		}
		
		if (wantsToHeal) 
		{
			if (this.getItemCarried() != null) 
			{
				//SChedule a heal
				if (this.getHitpoints() != this.getInitialHP())
				{
					//Check to see if the canteen he has is not empty
					Canteen benCanteen = (Canteen) this.getItemCarried();
					
					//If the canteens empty
					if (benCanteen.getLevel() == 0)
					{
						say("Ben has used up all of the water canteen in healing. Dropping...");
						Leave benIitemLeave = new Leave(this.getItemCarried(), messageRenderer);
						scheduler.schedule(benIitemLeave, this, 0);							
						wantsToHeal = false;
						recollect = true;
						return;
					}
					else
					{
						HealPlayer benHeal = new HealPlayer(this, messageRenderer);
						scheduler.schedule(benHeal, this, 0);
					}
					
					
				}
				else
				{
					//Ben now is at full health. Leave canteen and grab what we had

					//If there isnt a item on the ground
					if (this.getItemCarried().getSymbol() == ("o"))
					{
						//Leave the canteen & RETURN
						Leave benIitemLeave = new Leave(this.getItemCarried(), messageRenderer);
						scheduler.schedule(benIitemLeave, this, 0);							
						wantsToHeal = false;
						recollect = true;
						return;
					}
					
				}
			} 
		}
		else
		{
			//Ben attacking neighbours
			AttackInformation attack;
			attack = AttackNeighbours.attackLocals(ben,  ben.world, true, true);
				 
			if (attack != null) 
			{
				say(getShortDescription() + " suddenly looks sprightly and attacks " +
						attack.entity.getShortDescription());
				scheduler.schedule(attack.affordance, ben, 1);
						
			}
			else 
			{
				if (trainingPupil)
				{
					trainingPupil = false;
				} 
				else
				{
					Direction newdirection = path.getNext();
					say(getShortDescription() + " moves " + newdirection);
					Move myMove = new Move(newdirection, messageRenderer, world);
					scheduler.schedule(myMove, this, 1);
				
				}
			}	
		}
	}
	
	
			
			/*
			//Ben attacking neighbours
			AttackInformation attack;
			attack = AttackNeighbours.attackLocals(ben,  ben.world, true, true);
				 
			if (attack != null) 
			{
				say(getShortDescription() + " suddenly looks sprightly and attacks " +
						attack.entity.getShortDescription());
				scheduler.schedule(attack.affordance, ben, 1);
						
			}
			else 
			{
				if (trainingPupil)
				{
					trainingPupil = false;
				} 
				else
				{
					Direction newdirection = path.getNext();
					say(getShortDescription() + " moves " + newdirection);
					Move myMove = new Move(newdirection, messageRenderer, world);
					scheduler.schedule(myMove, this, 1);
				
				}
			}	
			*/
		
	
		
		
		
		//Check to see if ben has a item
			//if ben does have a water canteen"o""
		//otherwise
			//usual movement
	
	
	public boolean isTrainingPupil(){
		return trainingPupil;
	}
	
	public void setTrainingPupil(boolean t){
		trainingPupil = t;
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();
	}



}
