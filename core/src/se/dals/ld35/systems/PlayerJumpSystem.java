package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.*;

/**
 * Created by david on 2016-04-17.
 */
public class PlayerJumpSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public PlayerJumpSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
      entities = engine.getEntitiesFor(Family.all(
                        PlayerTagComponent.class,
                        PlayerJumpComponent.class, //TODO should rename to ApplyJumpComponent or sometheing.
                        JumpComponent.class,
                        PositionComponent.class,
                        VelocityComponent.class
                        ).get());

    }


    @Override
    public void update(float deltaTime) {
        if (entities.size() == 0) return;
        Entity player = entities.first();
        VelocityComponent vel = Mapper.VELOCITY.get(player);
        JumpComponent jump = Mapper.JUMP.get(player);
        float v_0 = -1;
        float xVel = Math.abs(vel.x);
        if (MathUtils.isEqual(xVel,0,0.1f)){
            v_0 = (2*jump.h/jump.x_h);
        }else {
            v_0 = (2*jump.h*xVel)/(jump.x_h);
        }
        vel.y += v_0;
        player.add(new GravityComponent());
        player.remove(PlayerJumpComponent.class);
    }
}
