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
import starwars.entities.LightSaber;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.entities.actors.behaviors.Patrol;

import java.util.List;

/**
 * Darth Vader (aka Obe-Wan) Kenobi.
 * 
 * At this stage, he's an extremely strong critter with a <code>Lightsaber</code>
 * who wanders around in a fixed pattern and neatly slices any Actor not on his
 * team with his lightsaber.
 * 
 * Note that you can only create ONE Darth Vader, like all SWLegends.
 * @author rober_000
 *
 */
public class DarthVader extends SWLegend  {

	private static DarthVader dv = null; // yes, it is OK to return the static instance!
	private Patrol path;
	private boolean trainingPupil = false;
	private boolean wantsToHeal = false;
	private boolean recollect = false;

	private DarthVader(MessageRenderer m, SWWorld world, Direction [] moves) {
		super(Team.EVIL, 10000, m, world);
		path = new Patrol(moves);
		this.setShortDescription("Darth Vader");
		this.setLongDescription("Darth Vader, is your father");
		LightSaber dvweap = new LightSaber(m);
		setItemCarried(dvweap);
		setWielding(true);
		Force dv = new Force(m, 79);	//80+ means hes a sith lord
		setForce(dv);
		setInfluence(-100);
        estSideOfForce();



	}

	/**
	 * getDarth VaderKenobi() method
	 * 
	 * Implements a new DV to the world - and activates the SWLegend into the SWWorld map.
	 * 
	 * @param	m	- Messagerenderer used for displaying messages to output.
	 * @param 	world	- SWWorld that Darth Vader will be placed in
	 * @param 	moves	- The Direction array that Darth Vader will follow in his Patrol (set in constructor)
	 * @return	Darth Vader		- The SWLegend Darth Vader Kenobi that is created
	 */
	public static DarthVader getDarthVader(MessageRenderer m, SWWorld world, Direction [] moves) {
		dv = new DarthVader(m, world, moves);
		dv.activate();
		return dv;
	}
	
	@Override
	protected void legendAct() {

		//Describes Darth Vaders location & HP to output
		say(describeLocation());
		
		if(isDead()) {
			say("Darth Vader has been killed!");
			
			Win deathToVader = new Win(this, messageRenderer);
			
			scheduler.scheduleWin(deathToVader, this, -2);
			
		}
		

		if (this.getHitpoints() < 100) {
			say("Darth Vader is almost gone!");
		}
			
        //patrolling
		//TODO DV ATTACKING / choking PEEPS / seeing luke
        if(Math.random() > 0.5){        //50% chance to attack

            tryAttack();
            AttackInformation attack = AttackNeighbours.attackLocals(this, this.world, false, true);
            if (attack != null) {
                say(getShortDescription() + " has attacked " + attack.entity.getShortDescription());
                scheduler.schedule(attack.affordance, this, 1);
            }

            else {

            }
        }else{ //move
            randomMovement();
        }





	}
	

	/**
	 * isTrainingPupil() method
	 * 
	 * Boolean describing if Darth Vader is currently training a pupil (assumed to be Luke!)
	 * 
	 * @return 	trainingPupil	- Boolean implementing Darth Vaders' status of training a pupil currently.
	 *
	 */
	public boolean isTrainingPupil(){
		return trainingPupil;
	}
	
	/**
	 * setTrainingPupil() method
	 * 
	 * Sets the Boolean describing if Darth Vader is currently training a pupil (assumed to be Luke!)
	 * 
	 * @param 	t	- Boolean describing Darth Vaders' current status of training a pupil currently.
	 *
	 */
	public void setTrainingPupil(boolean t){
		trainingPupil = t;
	}

	/**
	 * describeLocation() method
	 * 
	 * Returns the SWLocation of the created Darth Vader in the SWWorld map currently.
	 * 
	 * @return	SWLocation of the SWLegend Darth Vader Kenobi.
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
