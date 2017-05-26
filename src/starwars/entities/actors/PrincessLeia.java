/**
 * starwars.entities.actors package
 * 
 *  Used in the SWApplication (roguelike game) for all actors (both human
 *  and non human) who will wander the map in survival & questing!	
 *
 */
package starwars.entities.actors;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.*;
import starwars.entities.Canteen;
import starwars.entities.Force;
import starwars.entities.Plans;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.entities.actors.behaviors.Patrol;

import java.util.List;

/**
 * General 'Princess' Leia Organa.  
 * 
 * The General of the rebel alliance - also Lukes twin brother, who will be waiting on
 * the Death Star in hopes of a rescue from her twin brother!
 * 
 * @author jas
 * @author mewc
 *
 */
public class PrincessLeia extends SWLegend  {

	private static PrincessLeia leia = null; // yes, it is OK to return the static instance!
	private boolean trainingPupil = false;
	private boolean lukeFollow = false;
	private SWActor Luke;
	
	//Private constructor to set the current instance of Princess Leia
	private PrincessLeia(MessageRenderer m, SWWorld world, Direction [] moves) {
		super(Team.GOOD, 500, m, world);
		this.setShortDescription("Princess Leia (General Organa)");
		this.setLongDescription("Princess Leia, or General Organa - a very important woman to the rebels");
		
		//Set Princess Leias' Force levels
		Force f = new Force(m, 40);	
		setForce(f);
		
		//Set Leia to be holding the plans for the rebel attack
		setItemCarried(new Plans(m));
	}

	/**
	 * getPrincessLeia() method 
	 * 
	 * Implements a new Leia Organa to the world - and activates the SWLegend into the SWWorld map.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that Leia will be placed in
	 * @param 	moves	- The Direction array that Leia will follow in his Patrol (set in constructor)
	 * @return	leia	- The SWLegend PrincessLeia that is created
	 */
	public static PrincessLeia getPrincessLeia(MessageRenderer m, SWWorld world, Direction [] moves) {
		leia = new PrincessLeia(m, world, moves);
		leia.activate();
		return leia;
	}
	
	//Protected method lenegdAct() which describes Leia's actions.
	@Override
	protected void legendAct() {

		//Describes Leia's location & HP to output
		say(describeLocation());
		
		if(isDead()) {
			
			//Message stating Leia was killed in action
			this.messageRenderer.render("\n\nLeia was killed!");
			
			//Scheduler schedules the win
			scheduler.lossSchedule(this.messageRenderer);
		}
		
		//Check to see if Luke is around (if not following already)
		if (this.lukeFollow == false)
		{
			
			//Get surrounds of Leia
			List<SWEntityInterface> leiaSurrounds = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
			//and describe the contents
			if (leiaSurrounds.size() > 1) { // if it is equal to one, the only thing here is R2, so there is nothing to report
				for (SWEntityInterface entity : leiaSurrounds) {
					if (entity.getSymbol().contains("@")) { // If Leia sees Luke
						Luke = (SWActor) entity;
						
						say("Luke came accross Leia. General Organa will follow.");
						
						//Set Leia to follow Luke
						this.lukeFollow = true;
						
						//Add Leia to Lukes' following list (symbol and SWActor)
						Luke.getFollowerList().add(this.getSymbol());
						Luke.getFollowerListSWActors().add(this);
					}
				}
			}
			else
			{
				return;
			}
		}
		
		//When following Luke
		if (this.lukeFollow == true) 
		{
			//Get Lukes' Location
			SWLocation lukeLocation = this.world.getEntityManager().whereIs(Luke);
			
			//Set Leia's position to Lukes' location (follow)
			this.world.getEntityManager().setLocation(this, lukeLocation);
		}
		
		SWWorld ownedWorld = this.getWorld();
		
		say(ownedWorld.getWorldName());
		
		say(ownedWorld.getUniverse().getUniverseName());
		
	}
	

	/**
	 * isTrainingPupil() method
	 * 
	 * Boolean describing if Leia is currently training a pupil
	 * 
	 * @return 	trainingPupil	- Boolean implementing Leia's status of training a pupil currently.
	 *
	 */
	public boolean isTrainingPupil(){
		return trainingPupil;
	}
	
	/**
	 * setTrainingPupil() method
	 * 
	 * Sets the Boolean describing if Leia is currently training a pupil
	 * 
	 * @param 	t	- Boolean describing Leia's current status of training a pupil currently.
	 *
	 */
	public void setTrainingPupil(boolean t){
		trainingPupil = t;
	}

	/**
	 * describeLocation() method
	 * 
	 * Returns the SWLocation of the created Leia in the SWWorld map currently.
	 * 
	 * @return	SWLocation of the SWLegend Leia Organa
	 *
	 */
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getLongDescription() + " is at " + location.getShortDescription();
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
