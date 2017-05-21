package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;

/**
 * Created by m on 20-May-17.
 */
public class Plans extends SWEntity {


    public Plans(MessageRenderer m) {
        super(m);
        this.shortDescription = " The Death Star Plans";
        this.longDescription = "a great big architectural diagram of the Death Star";

        capabilities.add(Capability.HOME);
    }
}
