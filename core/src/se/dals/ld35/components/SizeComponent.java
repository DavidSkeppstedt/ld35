package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by david on 2016-04-16.
 */
public class SizeComponent implements Component {
    public int width,height;

    public SizeComponent(int w, int h) {
        this.width = w;
        this.height = h;
    }
}
