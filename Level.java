package bin.tenseconds.level;

import java.util.Timer;

public class Level {
    //地圖的生成

    public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}
}