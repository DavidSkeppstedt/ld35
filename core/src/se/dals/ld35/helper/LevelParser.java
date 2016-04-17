package se.dals.ld35.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Json;
import se.dals.ld35.model.Tile;
import se.dals.ld35.model.Tiles;

import java.util.HashMap;

/**
 * Created by david on 2016-04-16.
 */
public class LevelParser {
    private Pixmap level;
    public static Tiles tiles;
    HashMap<Integer, Tile> tileMap;

    public LevelParser(Pixmap level) {
        this.level = level;
        this.tileMap = new HashMap<Integer, Tile>();

        Json json = new Json();
        String text = Gdx.files.internal(Assets.TILE_CONFIG).readString();
        tiles = json.fromJson(Tiles.class, text);

        for (Tile t : tiles.tiles) {
            int colorValue = Long.decode(t.color).intValue();
            tileMap.put(colorValue, t);
        }
    }

    public int[][] parse() {

        int[][] tiles = new int[level.getWidth()][level.getHeight()];
        int h = level.getHeight() - 1;

        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {

                int currColor = level.getPixel(x, y);
                Tile currentTile = tileMap.get(currColor);

                if (currentTile == null) {
                    System.err.println("Not a defined color tile! \n No match for="+currColor);
                    tiles[x][h-y] = -1;
                }else {
                    tiles[x][h - y] = currentTile.id;
                }
            }
        }
        return tiles;
    }
}

