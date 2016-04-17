package se.dals.ld35.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.SizeComponent;
import se.dals.ld35.components.VisualComponent;

/**
 * Created by david on 2016-04-17.
 */
public class WorldBuilder {
    private static int[][] world;
    private static PooledEngine engine;

    public static final int UNIT_TO_WORLD = 64;


    public static void BuildWorld(PooledEngine engine, int [][] world,String path) {
        WorldBuilder.engine = engine;
        WorldBuilder.world = world;
        for (int y = 0; y < world.length; y++) {
            for (int x = 0; x < world[y].length; x++) {
                createEntity(x,y);
            }
        }
    }

    private static void createEntity(int x, int y) {
        Entity e = engine.createEntity();
        switch (world[x][y]) {
            case 1:
                e.add(new VisualComponent(new Texture("block.png")));
                e.add(new SizeComponent(1,1));
                e.add(new PositionComponent(x,y));
                break;
            default:
                return;
        }
        engine.addEntity(e);
    }
}
