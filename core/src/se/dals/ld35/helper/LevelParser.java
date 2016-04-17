package se.dals.ld35.helper;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by david on 2016-04-16.
 */

enum Tile {
    Air(LevelParser.AIR_COLOR,0),
    Ground(LevelParser.GROUND_COLOR,1);
    private final int value;
    private final int tile;
    Tile(int value,int tile) {
        this.value = value;
        this.tile = tile;
    }
    public int getTile(){return this.tile;};
    public int getValue(){
        return this.value;
    }
}



public class LevelParser {
    public static final int AIR_COLOR = 0xEA2206FF;
    public static final int GROUND_COLOR = 0xF250C6FF;

    private Pixmap level;


    public LevelParser(String path) {
        level = new Pixmap(new FileHandle(path));
    }

    public int[][] parse(){
        int[][] tiles = new int[level.getWidth()][level.getHeight()];
        int h = level.getHeight() - 1;
        for (int y =0; y < level.getHeight(); y++) {
            for (int x = 0; x<level.getWidth(); x++) {
                int currColor = level.getPixel(x,y);
                switch (currColor) {
                    case GROUND_COLOR:
                         System.out.print("[Wall]" );
                         tiles[x][h-y] = Tile.Ground.getTile();
                    break;
                    case AIR_COLOR:
                        System.out.print("[Air]");
                        tiles[x][h-y] = Tile.Air.getTile();
                        break;
                    default:
                        tiles[x][h-y] = -1; //ERROR...
                        System.out.print("[" +hex(currColor)+"]");
                }
            }
            System.out.println("");
        }

        return tiles;
    }

  private String hex(int n) {
      return Integer.toHexString(n);
  }

}

