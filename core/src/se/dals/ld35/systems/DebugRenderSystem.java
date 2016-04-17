package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.SizeComponent;

/**
 * Created by david on 2016-04-17.
 */
public class DebugRenderSystem extends EntitySystem {


    private ShapeRenderer shape;
    private ImmutableArray<Entity> entities;
    private OrthographicCamera camera;

    public DebugRenderSystem(OrthographicCamera camera) {
        this.camera = camera;
        shape = new ShapeRenderer();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class,SizeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent position;
        SizeComponent size;

        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setProjectionMatrix(camera.combined);
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            position = Mapper.POSITION.get(e);
            size = Mapper.SIZE.get(e);
            shape.setColor(Color.BLUE);
            shape.rect(position.X(), position.Y(), size.width, size.height);
        }
        shape.end();
    }
}
