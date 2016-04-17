package se.dals.ld35.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import se.dals.ld35.Mapper;
import se.dals.ld35.components.PlayerJumpComponent;
import se.dals.ld35.components.PlayerTagComponent;
import se.dals.ld35.components.VelocityComponent;

/**
 * Created by david on 2016-04-17.
 */
public class PlayerMovementSystem extends EntitySystem implements InputProcessor {
    private ImmutableArray<Entity> entities;
    private VelocityComponent velocity;
    boolean up,down,left,right;
    private float speed = 4;

    public PlayerMovementSystem(){
        Gdx.input.setInputProcessor(this);
        up = down = left = right = false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerTagComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        velocity = Mapper.VELOCITY.get(entities.first()); // There is only ever one player..
        if(right){
            velocity.x = speed;
        }else if (left) {
            velocity.x  = -speed;
        }else{
            velocity.x = 0;
        }
        if (up){
            entities.first().add(new PlayerJumpComponent());
            up = false;
        }
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.LEFT) {
            left = true;
        }

        if (keycode == Input.Keys.RIGHT){
            right = true;
        }

        if (keycode == Input.Keys.UP){
            up = true;
        }

        if (keycode == Input.Keys.DOWN){
            down = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT) {
            left = false;
        }

        if (keycode == Input.Keys.RIGHT){
            right = false;
        }

        if (keycode == Input.Keys.UP){
            up = false;
        }

        if (keycode == Input.Keys.DOWN){
            down = false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
