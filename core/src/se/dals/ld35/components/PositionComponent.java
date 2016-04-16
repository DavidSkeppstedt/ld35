package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by david on 2016-04-16.
 */
public class PositionComponent implements Component {
    public Vector2 vec;

    public PositionComponent(){
         this(0,0);
    }

    public PositionComponent(Vector2 vec) {
        this(vec.x,vec.y);
    }

    public PositionComponent(float x, float y) {
        this.vec = new Vector2(x,y);
    }

    public float X(){
        return this.vec.x;
    }

    public float Y() {
        return this.vec.y;
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
