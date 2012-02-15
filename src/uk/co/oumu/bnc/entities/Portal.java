package uk.co.oumu.bnc.entities;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;


public class Portal implements Interactor {
	
	String dest;
	int desti = -1;
	int destj = -1;
	int playerdir;

	public Portal(String dest, int desti, int destj) {
		this.dest = dest;
		this.desti = desti;
		this.destj = destj;
	}
	
	public Portal(String dest) {
		this.dest = dest;
	}

	@Override
	public void act() {
		Assets.door.play();
		playerdir = Game.LEVEL.player.direction;
		Game.changeLevel(dest);
		if(desti >= 0 && destj >= 0) {
			Game.LEVEL.player.i = desti;
			Game.LEVEL.player.j = destj;
		}
		Game.LEVEL.player.direction = playerdir;
	}
}
