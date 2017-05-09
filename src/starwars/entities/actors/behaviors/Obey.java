package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import edu.monash.fit2099.simulator.space.Direction;
import starwars.swinterfaces.SWGridController;

public class Obey {

	private ArrayList<Direction> moves;
	private int position = 0;
	
	public Obey(Direction [] moves) {
		this.moves = new ArrayList<Direction>(Arrays.asList(moves));
		
		// TODO Auto-generated constructor stub
	}

	public Obey(Collection<Direction> moves) {
		this.moves = new ArrayList<Direction>(moves);
	}
	
	
}
