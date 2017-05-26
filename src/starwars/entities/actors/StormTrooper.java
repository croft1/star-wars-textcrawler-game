package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.Attack;
import starwars.actions.Move;
import starwars.actions.Summon;
import starwars.entities.Blaster;
import starwars.entities.Mace;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.worlds.DeathStar;

import java.util.ArrayList;

/**
 * Class for Stormtroopers
 * 
 * A soldier of the Empire. Will protect Vader at all costs - will try to call for backup if needed
 * also, which can be a problem for the player!
 * 
 * @author jas
 * @author mewc
 */
public class StormTrooper extends SWActor {

	//Private name variable - String of the name of this 
	private String name;
    private static int cloneNumber = 1471;
   	private SWAffordance summon;  //because it was close to the end
    /**
	 * Constructor for a <code>StormTrooper</code> soldier. They are of team EVIL, and wander about on the Death
	 * Star in search for Luke and compatriots.
	 * 
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>StormTrooper</code> belongs to
	 * 
	 */
	public StormTrooper(MessageRenderer m, SWWorld world) {
		super(Team.EVIL, 100, m, world);
		// TODO Auto-generated constructor stub
		this.name = "#" + getNextCloneNumber();
		setItemCarried(new Blaster(m));
		summon = new Summon(this, m);
		this.addAffordance(summon);
	}
	
	/**
	 * act() method for <code>StormTrooper</code>
	 * 
	 * Implements the <code>StormTrooper</code> actions - which is extremely similar to Tusken Raiders. If they
	 * come across an enemy, they will have a 75% chance of hitting their enemy - as these 
	 * soldiers are known to be springy and sometimes take actions that would not be suitable for
	 * a situation!
	 * 
	 * <code>StormTrooper</code> can also call for backup - which will create a new <code>StormTrooper</code>
	 * in the same map - who can also call for backup!
	 * 
	 */
	@Override
	public void act() {
		if (isDead()) {
			return;
		}
		say(describeLocation());

		AttackInformation attack = AttackNeighbours.attackLocals(this, this.world, true, true);

		//75% chance to miss
		if (attack != null ) {
			if(Math.random() > 0.74 ){
				say(getShortDescription() + " has attacked " + attack.entity.getShortDescription());
				scheduler.schedule(attack.affordance, this, 1);
			} else{
				say(getShortDescription() + " shoots wildly");
			}
		}else if (Math.random() > 0.94) { //5% chzance
			//Call for backup!
			say(getShortDescription() + " called for backup. ");
			scheduler.schedule(summon, this, 1);  //iterate through available affordances, or be lazy....	

		}else{
			
			randomMovement();
		}
	}

	/**
	 * getShortDescription() method for <code>StormTrooper</code>
	 * 
	 * Returns the short string description of a <code>StormTrooper</code>
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
	@Override
	public String getShortDescription() {
		return name + " Storm Trooper";
	}

	/**
	 * getLongDescription() method for <code>StormTrooper</code>
	 * 
	 * Returns the long string description of a <code>StormTrooper</code>, calling the getShortDescription() method
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	/**
	 * getSymbol() method for <code>StormTrooper</code>
	 * 
	 * Returns the character symbol a <code>StormTrooper</code>.
	 * 
	 * @return 	description	- The <code>StormTrooper</code>'s short description in String format
	 */
    @Override
    public String getSymbol() {
        return "S";
    }
    
    //Private method describeLocation()
  	private String describeLocation() {
  		SWLocation location = this.world.getEntityManager().whereIs(this);
  		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

  	}

  	//Private method nextNextCloneNumber()
    private int getNextCloneNumber(){
	    cloneNumber ++;
	    return cloneNumber;
    }
}

/*
REFERENCES

Javatpoint 2017, Java Switch Statement, viewed 10 May 2017,
https://www.javatpoint.com/java-switch 

Javin, P 2017, How to find length/size of ArrayList in Java? Example, Java67, viewed 22 May 2017,
http://www.java67.com/2016/07/how-to-find-length-size-of-arraylist-in-java.html

Stack Overflow 2011, Creating a new ArrayList in Java, viewed 22 May 2017,
https://stackoverflow.com/questions/5915892/creating-a-new-arraylist-in-java

Stack Overflow 2011, Getting random numbers in Java [duplicate], viewed 10 May 2017,
http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

Stack Overflow 2012, How to remove first and last character of a string?, viewed 22 May 2017,
https://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string

Stack Overflow 2010, In Java, how do I check if a string contains a substring (ignoring case)? [duplicate], viewed 7 May 2017,
http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case

Stack Overflow 2010, In Java how does one turn a String into a char or a char into a String?, viewed 10 April 2017,
http://stackoverflow.com/questions/2429228/in-java-how-does-one-turn-a-string-into-a-char-or-a-char-into-a-string

Stack Overflow 2010, Java ArrayList Index, viewed 22 May 2017,
https://stackoverflow.com/questions/4313457/java-arraylist-index

The Internet Movie Database 2017, Quotes for C-3PO (Character), viewed 10 April 2017,
http://www.imdb.com/character/ch0000048/quotes 

*/
