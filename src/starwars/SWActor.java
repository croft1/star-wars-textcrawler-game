/**
 * Class that represents an Actor (i.e. something that can perform actions) in the starwars world.
 * 
 * @author ram
 * 
 * @modified 20130414 dsquire
 * 	- changed constructor so that affordances that all SWActors must have can be added
 * 	- changed team to be an enum rather than a string
 */
/*
 * Change log
 * 2017-01-20: Added missing Javadocs and improved comments (asel)
 * 2017-02-08: Removed the removeEventsMethod as it's no longer required.
 * 			   Removed the tick and act methods for SWActor as they are never called
 */
package starwars;

import java.util.ArrayList;
import java.util.HashSet;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.Actor;
import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.time.Scheduler;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.actions.Attack;
import starwars.actions.Move;
import starwars.entities.Force;
import starwars.entities.LightSaber;
import starwars.swinterfaces.SWGridController;

/**
 * Abstrct Class for SWActor
 * 
 * The SWActor class describes those actors (Droids, player, Tusken Raiderese, Bens) that
 * whose inherited class comes from. All SWActors have a team, health, hitpoints and so
 * forth. All actors aswell have methods that can be called for any actor - despite the
 * subclass that they are instansiated from.* 
 *
 */
public abstract class SWActor extends Actor<SWActionInterface> implements SWEntityInterface {
	
	/**the <code>Team</code> to which this <code>SWActor</code> belongs to**/
	private Team team;
	
	/**The amount of <code>hitpoints</code> of this actor. If the hitpoints are zero or less this <code>Actor</code> is dead*/
	private int hitpoints;
	
	/*The initial hitpoints of the Actor. Set in constructor - does not change (used for healing) */
	private int initHP;
	
	/**The world this <code>SWActor</code> belongs to.*/
	protected SWWorld world;
	
	/**Scheduler to schedule this <code>SWActor</code>'s events*/
	protected static Scheduler scheduler;
	
	/**The item carried by this <code>SWActor</code>. <code>itemCarried</code> is null if this <code>SWActor</code> is not carrying an item*/
	private SWEntityInterface itemCarried;
	
	/**The item carried by this <code>SWActor</code> may also be wielded. <code>isWielded</code> is false if this <code>SWActor</code> is not wielding the carried item, or cannot*/
	private boolean isWielding;
	
	/**If or not this <code>SWActor</code> is human controlled. <code>SWActor</code>s are not human controlled by default*/
	protected boolean humanControlled = false;
	
	/**A string symbol that represents this <code>SWActor</code>, suitable for display*/
	private String symbol;
	
	/**A set of <code>Capabilities</code> of this <code>SWActor</code>*/
	private HashSet<Capability> capabilities;
	
	/* Followers Array (follower Symbols) for display */
	private ArrayList<String> followingActorSymbol = new ArrayList<String>();
	
	/* Followers Array (follower Symbols) */
	private ArrayList<SWActor> followingSWActor = new ArrayList<SWActor>();

	/* Universe that the SWActor is in */
	private SWUniverse universe;
	
	/**
	 * Constructor for the <code>SWActor</code>.
	 * <p>
	 * The constructor initializes the <code>actions</code> list of this <code>SWActor</code>.
	 * <p>
	 * By default,
	 * <ul>
	 * 	<li>All <code>SWActor</code>s can be attacked.</li> 
	 * 	<li>Have their symbol set to '@'</li>
	 * </ul>
	 * 
	 * @param 	team to which this <code>SWActor</code> belongs to
	 * @param 	hitpoints initial hitpoints of this <code>SWActor</code> to start with
	 * @param 	m	message renderer for this <code>SWActor</code> to display messages
	 * @param 	world the <code>World</code> to which <code>SWActor</code> belongs to
	 * 
	 * @see 	#team
	 * @see 	#hitpoints
	 * @see 	#world
	 * @see 	starwars.actions.Attack
	 */
	public SWActor(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(m);
		actions = new HashSet<SWActionInterface>();
		this.team = team;
		this.hitpoints = hitpoints;
		this.initHP = hitpoints;
		this.world = world;
		this.symbol = "@";

		
		//ALL SWActors are given the Attack affordance hence they can be attacked
		SWAffordance attack = new Attack(this, m);
		this.addAffordance(attack);
		

	}
	
	/**
	 * Sets the <code>scheduler</code> of this <code>SWActor</code> to a new <code>Scheduler s</code>
	 * 
	 * @param	s the new <code>Scheduler</code> of this <code>SWActor</code> 
	 * @see 	#scheduler
	 */
	public static void setScheduler(Scheduler s) {
		scheduler = s;
	}
	
	/**
	 * Returns the team to which this <code>SWActor</code> belongs to.
	 * <p>
	 * Useful in comparing the teams different <code>SWActor</code> belong to.
	 * 
	 * @return 	the team of this <code>SWActor</code>
	 * @see 	#team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Returns the hit points of this <code>SWActor</code>.
	 * 
	 * @return 	the hit points of this <code>SWActor</code> 
	 * @see 	#hitpoints
	 * @see 	#isDead()
	 */
	@Override
	public int getHitpoints() {
		return hitpoints;
	}
	
	
	
	
	/**
	 * Enforces the obey command from <code>MindControlNeighbours</code> acording to the <code>Force</code> affordance presence
	 * when an actor gets this called, its next movement will be user controlled.
	 * > 
	 * @see 	#force
	 * 
	 */

	public void obey() {
		// TODO compare force values / if has force and make Actor move by default.
		scheduler.schedule(SWGridController.getUserDecision(this), this, 1);
	}
	
	/**
	 * Determined whether the actor is able to obey - is weak minded 
	 * True for all SWActors, needs <code>@override</code> within forceActor
	 * @see 	#force
	 * 
	 */

	public boolean canObey() {
		for (Affordance affEntity : this.getAffordances()) {
			if (affEntity.getDescription().contains("obey")) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
	
	/**
	 * Attempts to attack with the <code>Force</code>
	 */
	public void tryAttack() {
		//add in attak code
	}
	
	

	/**
	 * Returns an ArrayList containing this Actor's available Actions, including the Affordances of items
	 * that the Actor is holding.
	 * 
	 * @author ram
	 */
	public ArrayList<SWActionInterface> getActions() {
		ArrayList<SWActionInterface> actionList = super.getActions();
		
		//If the HobbitActor is carrying anything, look for its affordances and add them to the list
		SWEntityInterface item = getItemCarried();
		if (item != null)
			for (Affordance aff : item.getAffordances())
				if (aff instanceof SWAffordance)
				actionList.add((SWAffordance)aff);
		return actionList;
	}
	
	/**
	 * Returns the item carried by this <code>SWActor</code>. 
	 * <p>
	 * This method only returns the reference of the item carried 
	 * and does not remove the item held from this <code>SWActor</code>.
	 * <p>
	 * If this <code>SWActor</code> is not carrying an item this method will return null.
	 * 
	 * @return 	the item carried by this <code>SWActor</code> or null if no item is held by this <code>SWActor</code>
	 * @see 	#itemCarried
	 */
	public SWEntityInterface getItemCarried() {
		return itemCarried;
	}
	
	
	/**
	 * Returns the status of wield by this <code>SWActor</code>. 
	 * <p>
	 * 
	 * If this <code>SWActor</code> is unable to wield the item (lightsaber) itll be false
	 * 
	 * @return 	the item carried by this <code>SWActor</code> or null if no item is held by this <code>SWActor</code>
	 * @see 	#itemCarried
	 */
	public boolean isWielding() {
		
		//TODO get wielded item
		return isWielding;
	}
	
	

	/**
	 * Sets the team of this <code>SWActor</code> to a new team <code>team</code>.
	 * <p>
	 * Useful when the <code>SWActor</code>'s team needs to change dynamically during the simulation.
	 * For example, a bite from an evil actor makes a good actor bad.
	 *
	 * @param 	team the new team of this <code>SWActor</code>
	 * @see 	#team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Method insists damage on this <code>SWActor</code> by reducing a 
	 * certain amount of <code>damage</code> from this <code>SWActor</code>'s <code>hitpoints</code>
	 * 
	 * @param 	damage the amount of <code>hitpoints</code> to be reduced
	 * @pre 	<code>damage</code> should not be negative
	 */
	@Override
	public void takeDamage(int damage) {
		//Precondition 1: Ensure the damage is not negative. Negative damage could increase the SWActor's hitpoints
		assert (damage >= 0)	:"damage on SWActor must not be negative";
		this.hitpoints -= damage;
	}

	/**
	 * Assigns this <code>SWActor</code>'s <code>itemCarried</code> to 
	 * a new item <code>target</code>
	 * <p>
	 * This method will replace items already held by the <code>SWActor</code> with the <code>target</code>.
	 * A null <code>target</code> would signify that this <code>SWActor</code> is not carrying an item anymore.
	 * 
	 * @param 	target the new item to be set as item carried
	 * @see 	#itemCarried
	 */
	public void setItemCarried(SWEntityInterface target) {
		this.itemCarried = target;
	}
	
	
	
	/**
	 * Returns true if this <code>SWActor</code> is dead, false otherwise.
	 * <p>
	 * A <code>SWActor</code> is dead when it's <code>hitpoints</code> are less than or equal to zero (0)
	 *
	 * @author 	ram
	 * @return 	true if and only if this <code>SWActor</code> is dead, false otherwise
	 * @see 	#hitpoints
	 */
	public boolean isDead() {
		return hitpoints <= 0;
	}

	/**
	 * getSymbol() method
	 * 
	 * Returns the symbol denoting the SWActor in the SWWorld in question
	 * 
	 * @return 	the symbol denoting this <code>SWActor</code>.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	@Override
	public String getSymbol() {
		if(isDead()){
			return "{"+symbol+"}";
		}else{
			return symbol;
		}
		
	}
	
	/**
	 * setSymbol() method
	 * 
	 * Sets the symbol denoting the SWActor in the SWWorld in question
	 * 
	 * @param 	s	- the new symbol denoting this <code>SWActor</code>.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	@Override
	public void setSymbol(String s) {
		symbol = s;
	}
	
	/**
	 * Returns if or not this <code>SWActor</code> is human controlled.
	 * <p>
	 * Human controlled <code>SWActors</code>' <code>SWActions</code> are selected by the user as commands from the Views.
	 * 
	 * @return 	true if the <code>SWActor</code> is controlled by a human, false otherwise
	 * @see 	#humanControlled
	 */
	public boolean isHumanControlled() {
		return humanControlled;
	}
	
	/**
	 * hasCapability() method
	 * 
	 * Returns the boolean describing if this SWActor has a certain Capability
	 * 
	 * @param 	c	- the capability in question if the <code>SWActor</code> can perform.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	@Override
	public boolean hasCapability(Capability c) {
		return capabilities.contains(c);
	}
	
	/**
	 * This method will poll this <code>SWActor</code>'s current <code>Location loc</code>
	 * to find potential exits, and replaces all the instances of <code>Move</code>
	 * in this <code>SWActor</code>'s command set with <code>Moves</code> to the new exits.
	 * <p>
	 * This method doesn't affect other non-movement actions in this <code>SWActor</code>'s command set.
	 *  
	 * @author 	ram
	 * @param 	loc this <code>SWActor</code>'s location
	 * @pre		<code>loc</code> is the actual location of this <code>SWActor</code>
	 */
	public void resetMoveCommands(Location loc) {
		HashSet<SWActionInterface> newActions = new HashSet<SWActionInterface>();
		
		// Copy all the existing non-movement options to newActions
		for (SWActionInterface a: actions) {
			if (!a.isMoveCommand())
				newActions.add(a);
		}
		
		// add new movement possibilities
		for (CompassBearing d: CompassBearing.values()) { 														  
			if (loc.getNeighbour(d) != null) //if there is an exit from the current location in direction d, add that as a Move command
				newActions.add(new Move(d,messageRenderer, world)); 
		}
		
		// replace command list of this SWActor
		this.actions = newActions;		
		
		// TODO: This assumes that the only actions are the Move actions. This will clobber any others. Needs to be fixed.
		/* Actually, that's not the case: all non-movement actions are transferred to newActions before the movements are transferred. --ram */
	}
	

	/**
	 * setHitPoints() method
	 * 
	 * Sets the current hitpoints (HP) of this <code>SWActor</code>.
	 * 
	 * @param  	newHP	- the current HP of the <code>SWActor</code>.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	public void setHitpoints(int newHP) {
		
		//Set this Actors' HP to the integer newHP
		this.hitpoints = newHP;
	}
	
	/**
	 * getInitialHP() method
	 * 
	 * Gets the initial, full hitpoints (HP) of this <code>SWActor</code>.
	 * 
	 * @return  	initHP	- the initial (full) HP of the <code>SWActor</code>.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	public int getInitialHP() {
		return initHP;
	}

	/**
	 * setWielding() method
	 * 
	 * Sets a Boolean describing if a <code>SWActor</code> is wielding a weapon.
	 * 
	 * @param  	b	- Boolean representing if the <code>SWActor</code> is wielding a weapon.
	 * @ore 	The SWActor is defined and not null
	 * 
	 */
	public void setWielding(boolean b) {
		isWielding = b;
	}
	
	/**
	 * getCarryDescription() method
	 * 
	 * Returns a string describing the item currently held by this  <code>SWActor</code>.
	 * 
	 * @return  	String of the current weapon or item that the  <code>SWActor</code> is holding.
	 * @ore 	The SWActor is defined, not null and holding an item
	 * 
	 */
	public String getCarryDescription(){
		String wieldDesc = (isWielding()) ?  " is wielding " :  " is holding ";
		
		return this.getShortDescription() 
		+ wieldDesc + itemCarried.getShortDescription() + " [" + itemCarried.getHitpoints() + "]";
	}

	protected void randomMovement(){
		ArrayList<Direction> possibledirections = new ArrayList<Direction>();

		// build a list of available directions

		for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
			if (SWWorld.getEntitymanager().seesExit(this, d)) {
				possibledirections.add(d);
			}
		}

		Direction heading = possibledirections.get((int) (Math.floor(Math.random() * possibledirections.size())));
		say(getShortDescription() + " is heading " + heading + " next.");
		Move myMove = new Move(heading, messageRenderer, world);

		scheduler.schedule(myMove, this, 1);
	}
	
	/**
	 * getFollowerList() method
	 * 
	 * Returns the Array List of symbols who is following the <code>SWActor</code> at a given time
	 * 
	 * @return  	 Array List of symbols describing the followers of a <code>SWActor</code>.
	 * @ore 	The SWActor is defined, not null and either has or has no followers
	 * 
	 */
	public ArrayList<String> getFollowerList(){
		return this.followingActorSymbol;
	}
	
	/**
	 * getFollowerListSWActor() method
	 * 
	 * Returns the Array List of SWActors who is following the <code>SWActor</code> at a given time
	 * 
	 * @return  	 Array List of symbols describing the followers of a <code>SWActor</code>.
	 * @ore 	The SWActor is defined, not null and either has or has no followers
	 * 
	 */
	public ArrayList<SWActor> getFollowerListSWActors(){
		return this.followingSWActor;
	}
	
	
	/**
	 * setWorld() method
	 * 
	 * Sets the world in which the <code>SWActor</code> resides in.
	 * 
	 * @param  	newWorld	- SWWorld that the SWActor will be set to
	 * @ore 	The SWActor & SWWorld being transferred to are defined and not null
	 * 
	 */
	public void setWorld(SWWorld newWorld) {
		this.world = newWorld;
	}
	
	/**
	 * getWorld() method
	 * 
	 * Gets the world in which the <code>SWActor</code> resides in.
	 * 
	 * @param  	actor	- The SWActor obtaining the query for
	 * @return	The <code>SWWorld</code> in which the actor is set to
	 * 
	 */
	public SWWorld getWorld() {
		return this.world;
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
