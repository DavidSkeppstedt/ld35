package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by david on 2016-04-17.
 */
public class VelocityComponent implements Component {
    public float x,y;
    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
