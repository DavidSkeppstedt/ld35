package se.dals.ld35.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.SizeComponent;
import se.dals.ld35.components.VisualComponent;
import se.dals.ld35.helper.Assets;
import se.dals.ld35.helper.LevelParser;
import se.dals.ld35.model.Tile;
import se.dals.ld35.model.Tiles;

/**
 * Created by david on 2016-04-17.
 */
public class WorldBuilder {
    private static int[][] world;
    private static PooledEngine engine;

    public static void BuildWorld(PooledEngine engine, int [][] world,Assets assets) {
        WorldBuilder.engine = engine;
        WorldBuilder.world = world;
        for (int y = 0; y < world.length; y++) {
            for (int x = 0; x < world[y].length; x++) {
                createEntity(x,y,assets);
            }
        }
    }

    private static void createEntity(int x,int y,Assets assets) {
        Entity e = engine.createEntity();

        for(Tile t : LevelParser.tiles.tiles){
            if (t.id == world[x][y] && !t.name.equals("Air")){

                e.add(new VisualComponent(
                        assets.manager.get(
                                Assets.TILE_SHEET,
                                TextureAtlas.class
                        ).findRegion(t.image,t.index)));

                e.add(new SizeComponent(1,1));
                e.add(new PositionComponent(x,y));

                engine.addEntity(e);
                return;
            }
        }

    }
}
