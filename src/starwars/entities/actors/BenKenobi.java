/**
 * starwars.entities.actors package
 * 
 *  Used in the SWApplication (roguelike game) for all actors (both human
 *  and non human) who will wander the map in survival & questing!	
 *
 */
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
		setInfluence(100);
		this.addAffordance(new TrainForce(this, m));	//allow those with the force to perform mindcontrol
		
	}

	/**
	 * getBenKenobi() method 
	 * 
	 * Implements a new Ben Kenobi to the world - and activates the SWLegend into the SWWorld map.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that Ben will be placed in
	 * @param 	moves	- The Direction array that Ben will follow in his Patrol (set in constructor)
	 * @return	ben		- The SWLegend Ben Kenobi that is created
	 */
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
	

	/**
	 * isTrainingPupil() method
	 * 
	 * Boolean describing if Ben is currently training a pupil (assumed to be Luke!)
	 * 
	 * @return 	trainingPupil	- Boolean implementing Bens' status of training a pupil currently.
	 *
	 */
	public boolean isTrainingPupil(){
		return trainingPupil;
	}
	
	/**
	 * setTrainingPupil() method
	 * 
	 * Sets the Boolean describing if Ben is currently training a pupil (assumed to be Luke!)
	 * 
	 * @param 	t	- Boolean describing Bens' current status of training a pupil currently.
	 *
	 */
	public void setTrainingPupil(boolean t){
		trainingPupil = t;
	}

	/**
	 * describeLocation() method
	 * 
	 * Returns the SWLocation of the created Ben in the SWWorld map currently.
	 * 
	 * @return	SWLocation of the SWLegend Ben Kenobi.
	 *
	 */
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();
	}



}

/*
REFERENCES

Javatpoint 2017, Java Switch Statement, viewed 10 May 2017,
https://www.javatpoint.com/java-switch 

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

*/
