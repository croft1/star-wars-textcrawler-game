package starwars.worlds;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.*;
import starwars.entities.*;
import starwars.entities.actors.*;

/**
 * Class representing the planet Tatooine in the Star Wars universe. This is a very sandy
 * planet full of Tusken Raiders and remote Droids - but also houses the Master Jedi Obi Wan
 * 'Ben' Kenobi.
 * 
 * @author ram
 * @author jas
 * @author mewc
 */
public class Tatooine extends SWWorld {

	/**
	 * Constructor of <code>Tatooine</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid for setup of the Tatooine instance.
	 * 
	 * @param 	inUniverse		The universe into which this Tatooine instance belongs to.
	 */
    public Tatooine(SWUniverse inUniverse) {

        SWLocation.SWLocationMaker factory = SWLocation.getMaker();
        myGrid = new SWGrid(10,10,factory);
        space = myGrid;
        name = "Tatooine";
        universe = inUniverse;
    }

    /**
     * Sets up Tatooine, setting descriptions for locations and placing items and actors
     * on the grid.
     *
     * @param iface a MessageRenderer to be passed onto newly-created entities
     * @author ram
     */
    public void initializeWorld(MessageRenderer iface) {
        SWLocation loc;

        // Set default location string
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                loc = myGrid.getLocationByCoordinates(col, row);
                loc.setLongDescription(name + " (" + col + ", " + row + ")");
                loc.setShortDescription(name + " (" + col + ", " + row + ")");
                loc.setSymbol('.');
            }
        }


        // BadLands
        for (int row = 5; row < 8; row++) {
            for (int col = 4; col < 7; col++) {
                loc = myGrid.getLocationByCoordinates(col, row);
                loc.setLongDescription("Badlands (" + col + ", " + row + ")");
                loc.setShortDescription("Badlands (" + col + ", " + row + ")");
                loc.setSymbol('b');
            }
        }

        //Ben's Hut
        loc = myGrid.getLocationByCoordinates(5, 6);
        loc.setLongDescription("Ben's Hut");
        loc.setShortDescription("Ben's Hut");
        loc.setSymbol('H');

        Direction[] patrolmoves = {CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.SOUTH,
                CompassBearing.WEST, CompassBearing.WEST,
                CompassBearing.SOUTH,
                CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.NORTHWEST, CompassBearing.NORTHWEST};

        BenKenobi ben = BenKenobi.getBenKenobi(iface, this, patrolmoves);
        ben.setSymbol("B");
        loc = myGrid.getLocationByCoordinates(4, 5);
        entityManager.setLocation(ben, loc);

        // Luke
        Player luke = new Player(Team.GOOD, 100, iface, this);
        luke.setShortDescription("Luke");
        loc = myGrid.getLocationByCoordinates(5, 9);
        entityManager.setLocation(luke, loc);
        luke.resetMoveCommands(loc);
        
        // Owen and Beru
        Humanoid owen = new Humanoid(Team.GOOD, 200, iface, this);
        owen.setShortDescription("Uncle Owen");
        loc = myGrid.getLocationByCoordinates(3, 8);
        entityManager.setLocation(owen, loc);
        owen.setSymbol("H");

        Humanoid beru = new Humanoid(Team.GOOD, 200, iface, this);
        beru.setShortDescription("Aunty Beru");
        loc = myGrid.getLocationByCoordinates(5, 8);
        entityManager.setLocation(beru, loc);
        beru.setSymbol("H");


        // Beggar's Canyon
        for (int col = 3; col < 8; col++) {
            loc = myGrid.getLocationByCoordinates(col, 8);
            loc.setShortDescription("Beggar's Canyon (" + col + ", " + 8 + ")");
            loc.setLongDescription("Beggar's Canyon  (" + col + ", " + 8 + ")");
            loc.setSymbol('C');
            loc.setEmptySymbol('='); // to represent sides of the canyon
        }

        // Moisture Farms
        for (int row = 0; row < 10; row++) {
            for (int col = 8; col < 10; col++) {
                loc = myGrid.getLocationByCoordinates(col, row);
                loc.setLongDescription("Moisture Farm (" + col + ", " + row + ")");
                loc.setShortDescription("Moisture Farm (" + col + ", " + row + ")");
                loc.setSymbol('F');

                // moisture farms have reservoirs
                entityManager.setLocation(new Reservoir(iface), loc);
            }
        }

        // Ben Kenobi's hut
		/*
		 * Scatter some other entities and actors around
		 */
        // a canteen
        loc = myGrid.getLocationByCoordinates(3, 1);
        SWEntity canteen = new Canteen(iface, 10, 0);
        canteen.setSymbol("o");
        canteen.setHitpoints(500);
        entityManager.setLocation(canteen, loc);
        canteen.addAffordance(new Take(canteen, iface));

        // an oil can treasure
        loc = myGrid.getLocationByCoordinates(1, 5);
        SWEntity oilcan = new SWEntity(iface);
        oilcan.setShortDescription("an oil can");
        oilcan.setLongDescription("an oil can, which would theoretically be useful for fixing robots");
        oilcan.setSymbol("x");
        oilcan.setHitpoints(100);
        // add a Take affordance to the oil can, so that an actor can take it
        entityManager.setLocation(oilcan, loc);
        oilcan.addAffordance(new Take(oilcan, iface));

        // a lightsaber
        LightSaber lightSaber = new LightSaber(iface);
        loc = myGrid.getLocationByCoordinates(5, 5);
        entityManager.setLocation(lightSaber, loc);


        // A blaster
        Blaster blaster = new Blaster(iface);
        loc = myGrid.getLocationByCoordinates(3, 4);
        entityManager.setLocation(blaster, loc);

        // The Millennium Falcon
        MilleniumFalcon millFalcTattoine = new MilleniumFalcon(iface);
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(millFalcTattoine, loc);
        
        //Append the MF location to the MF Location list in SWUNiverse
        this.universe.getMFList().add(loc);
        
        millFalcTattoine.addAffordance(new FlyToYavinFour(millFalcTattoine, this.getEntityManager(), iface));
        millFalcTattoine.addAffordance(new FlyToDeathStar(millFalcTattoine, this.getEntityManager(), iface));
        
        // A Tusken Raider
        TuskenRaider tim = new TuskenRaider(10, "Tim", iface, this);
        tim.setSymbol("T");
        loc = myGrid.getLocationByCoordinates(2, 0);
        entityManager.setLocation(tim, loc);

        // A second Tusken Raider
        TuskenRaider lawrence = new TuskenRaider(20, "Lawrence Jacoby", iface, this);
        lawrence.setSymbol("T");
        loc = myGrid.getLocationByCoordinates(4, 3);
        entityManager.setLocation(lawrence, loc);

        // A third Tusken Raider
        TuskenRaider james = new TuskenRaider(14, "James Hurley", iface, this);
        james.setSymbol("T");
        loc = myGrid.getLocationByCoordinates(6, 3);
        entityManager.setLocation(james, loc);

        // A fourth Tusken Raider
        TuskenRaider dale = new TuskenRaider(88, "Dale Cooper", iface, this);
        dale.setSymbol("T");
        loc = myGrid.getLocationByCoordinates(4, 7);
        entityManager.setLocation(dale, loc);

        // A new canteen for Ben to Drink from
        Canteen c = new Canteen(iface, 100, 100);
        c.setSymbol("o");
        loc = myGrid.getLocationByCoordinates(6, 6);
        c.setLevel(10);
        entityManager.setLocation(c, loc);

        // A Droid
        Droid Droid_1 = new Droid(50, "Droid 1", iface, this, null);
        Droid_1.setSymbol("D1");
        loc = myGrid.getLocationByCoordinates(1, 2);
        entityManager.setLocation(Droid_1, loc);

        //Adding a TakeOwnership Affordance to the Droid - thus an SWActor can take ownership of it.
        Droid_1.addAffordance(new TakeOwnership(Droid_1, iface));
        //Adding a HealDroid affordance - that SWACtors act upon
        Droid_1.addAffordance(new HealDroid(Droid_1, iface));
        //Adding a Repair affordance to the Droid - can be repaired
        Droid_1.addAffordance(new Repair(Droid_1, iface));
        //Adding a Disassesmble affordance to the Droid - can be disassembled into DroidParts
        Droid_1.addAffordance(new Disassemble(Droid_1, iface));

        // A second Droid
        Droid Droid_2 = new Droid(50, "Droid 2", iface, this, null);

        Droid_2.setSymbol("D2");
        loc = myGrid.getLocationByCoordinates(1, 4);
        entityManager.setLocation(Droid_2, loc);


        //Adding a TakeOwnership Affordance to the Droid - thus an SWActor can take ownership of it.
        Droid_2.addAffordance(new TakeOwnership(Droid_2, iface));
        //Adding a HealDroid affordance - that SWACtors act upon
        Droid_2.addAffordance(new HealDroid(Droid_2, iface));
        //Adding a Repair affordance to the Droid - can be repaired
        Droid_2.addAffordance(new Repair(Droid_2, iface));
        //Adding a Disassesmble affordance to the Droid - can be disassembled into DroidParts
        Droid_2.addAffordance(new Disassemble(Droid_2, iface));

        //A third Droid
        Droid Droid_3 = new Droid(50, "Droid 3", iface, this, null);

        Droid_3.setSymbol("D3");
        loc = myGrid.getLocationByCoordinates(3, 0);
        entityManager.setLocation(Droid_3, loc);


        //Adding a TakeOwnership Affordance to the Droid - thus an SWActor can take ownership of it.
        Droid_3.addAffordance(new TakeOwnership(Droid_3, iface));
        //Adding a HealDroid affordance - that SWACtors act upon
        Droid_3.addAffordance(new HealDroid(Droid_3, iface));
        //Adding a Repair affordance to the Droid - can be repaired
        Droid_3.addAffordance(new Repair(Droid_3, iface));
        //Adding a Disassesmble affordance to the Droid - can be disassembled into DroidParts
        Droid_3.addAffordance(new Disassemble(Droid_3, iface));

        //Setting D3 to be immobile and no health (for R2-D2 to help)
        Droid_3.setHitpoints(0);
        Droid_3.setIsImmobile(true);


        //Creating C-3PO & attributes
        Droid C3PO = new Droid(200, "C-3PO", iface, this, null);
        C3PO.setSymbol("C3");
        loc = myGrid.getLocationByCoordinates(0, 7);
        entityManager.setLocation(C3PO, loc);

        //Adding a TakeOwnership Affordance to C-3PO - thus an SWActor can take ownership of him.
        C3PO.addAffordance(new TakeOwnership(C3PO, iface));
        //Adding a HealDroid affordance - that SWACtors act upon
        C3PO.addAffordance(new HealDroid(C3PO, iface));
        //Adding a Repair affordance to C-3PO- can be repaired
        C3PO.addAffordance(new Repair(C3PO, iface));
        //Adding a Disassesmble affordance to C-3PO - he can be disassembled into DroidParts
        C3PO.addAffordance(new Disassemble(C3PO, iface));

        //Creating R2-D2's patrol pattern
        Direction[] R2PatrolMoves = {CompassBearing.EAST,
                CompassBearing.EAST,
                CompassBearing.EAST,
                CompassBearing.EAST,
                CompassBearing.EAST,
                CompassBearing.WEST,
                CompassBearing.WEST,
                CompassBearing.WEST,
                CompassBearing.WEST,
                CompassBearing.WEST};

        //Creating R2-D2 and attributes
        Droid R2D2 = new Droid(200, "R2-D2", iface, this, R2PatrolMoves);
        R2D2.setSymbol("R2");
        loc = myGrid.getLocationByCoordinates(0, 0);
        entityManager.setLocation(R2D2, loc);

        //Adding a TakeOwnership Affordance to R2-D2 - thus an SWActor can take ownership of him.
        R2D2.addAffordance(new TakeOwnership(R2D2, iface));
        //Adding a HealDroid affordance - that SWACtors act upon
        R2D2.addAffordance(new HealDroid(R2D2, iface));
        //Adding a Repair affordance to R2-D2 - he can be repaired
        R2D2.addAffordance(new Repair(R2D2, iface));
        //Adding a Disassesmble affordance to R2-D2 - he can be disassembled into DroidParts
        R2D2.addAffordance(new Disassemble(R2D2, iface));

        //Make R2D2 hold some Droid Parts from the beginning
        DroidParts r2dp = new DroidParts(iface);
        R2D2.setItemCarried(r2dp);
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