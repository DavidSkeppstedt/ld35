package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.VelocityComponent;

/**
 * Created by david on 2016-04-17.
 */
public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public MovementSystem(){

    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent position;
        VelocityComponent velocity;

        for(Entity e: entities) {
            velocity = Mapper.VELOCITY.get(e);
            Vector2 vec = new Vector2(velocity.x,velocity.y);
            vec.scl(deltaTime);
            position = Mapper.POSITION.get(e);
            position.vec.add(vec);
        }
    }
}
