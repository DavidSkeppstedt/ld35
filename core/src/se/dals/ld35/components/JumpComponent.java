package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by david on 2016-04-17.
 */
public class JumpComponent implements Component {
    public float x_h;
    public float h;

    public JumpComponent(float h, float xh) {
        this.x_h = xh;
        this.h = h;
    }
}
