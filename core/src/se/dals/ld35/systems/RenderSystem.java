package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.SizeComponent;
import se.dals.ld35.components.VisualComponent;

/**
 * Created by david on 2016-04-16.
 */
public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private ShapeRenderer shape;
    private OrthographicCamera camera;

    public RenderSystem(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.shape = new ShapeRenderer();

    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(VisualComponent.class,PositionComponent.class,SizeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent position;
        VisualComponent sprite;
        SizeComponent size;

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);

            sprite = Mapper.VISUAL.get(e);
            position = Mapper.POSITION.get(e);
            size = Mapper.SIZE.get(e);

            batch.draw(
                    sprite.texture,
                    position.X(),
                    position.Y(),
                    size.width,
                    size.height);
        }

        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);

            sprite = Mapper.VISUAL.get(e);
            position = Mapper.POSITION.get(e);

            shape.setColor(Color.RED);

            shape.rect(position.X(),position.Y(),sprite.texture.getWidth(),sprite.texture.getHeight());
        }
        shape.end();

    }
}
