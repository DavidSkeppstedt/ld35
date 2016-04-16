package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by david on 2016-04-16.
 */
public class VisualComponent implements Component {
    public Texture texture;

    public VisualComponent(Texture texture) {
        this.texture = texture;
    }
}
