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
public class PrincessLeia extends SWLegend  {

	private static PrincessLeia leia = null; // yes, it is OK to return the static instance!
	private Patrol path;
	private boolean trainingPupil = false;
	private boolean wantsToHeal = false;
	private boolean lukeFollow = false;
	private SWActor Luke;
	
	private PrincessLeia(MessageRenderer m, SWWorld world, Direction [] moves) {
		super(Team.GOOD, 500, m, world);
		this.setShortDescription("Princess Leia (General Organa)");
		this.setLongDescription("Princess Leia, or General Organa - a very important woman to the rebels");
		//LightSaber bensweapon = new LightSaber(m);
		//setItemCarried(bensweapon);
		Force f = new Force(m, 40);	//80+ means hes the chosen one
		setForce(f);
		setItemCarried(new Plans(m));
		//this.addAffordance();	//ADD IN FOLLOW AFFORDANCE

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
	public static PrincessLeia getPrincessLeia(MessageRenderer m, SWWorld world, Direction [] moves) {
		leia = new PrincessLeia(m, world, moves);
		leia.activate();
		return leia;
	}
	
	@Override
	protected void legendAct() {

		//Describes Bens location & HP to output
		say(describeLocation());
		
		if(isDead()) {
			return;//DO YOU LOSE STUFF HERE
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
						
						say("Luke came accross Leia. She will follow.");
						
						//Set Leia to follow Luke
						this.lukeFollow = true;
						
						//Add Leia to Lukes' following list
						Luke.getFollowerList().add(this.getSymbol());
					}
				}
			}
			else
			{
				say("Leia cant see Luke");
			}
		}
		
		//When following Luke
		if (this.lukeFollow == true) 
		{
			//Get Lukes' Location
			SWLocation lukeLocation = this.world.getEntityManager().whereIs(Luke);
			
			//Set Droids' position to owners' location (follow)
			this.world.getEntityManager().setLocation(this, lukeLocation);
		}
		
		SWWorld ownedWorld = this.getWorld(this);
		
		say(ownedWorld.getWorldName());
		
		say(ownedWorld.getUniverse().getUniverseName());
		
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
