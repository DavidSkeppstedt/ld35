package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.GravityComponent;
import se.dals.ld35.components.JumpComponent;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.VelocityComponent;

/**
 * Created by david on 2016-04-17.
 */
public class GravitySystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public GravitySystem(){

    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(GravityComponent.class,VelocityComponent.class, PositionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity e:entities) {
            VelocityComponent vel = Mapper.VELOCITY.get(e);
            JumpComponent jump = Mapper.JUMP.get(e);
            float g = -1;
            if (MathUtils.isEqual(vel.x,0,0.1f)) {
                g = (-2*jump.h)/(jump.x_h*jump.x_h);
            }else {
                g = (-2 * jump.h * (vel.x * vel.x)) / (jump.x_h * jump.x_h);
            }
            vel.y +=g;
        }
    }
}
