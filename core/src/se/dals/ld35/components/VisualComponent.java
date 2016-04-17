package se.dals.ld35.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by david on 2016-04-16.
 */
public class VisualComponent implements Component {
    public TextureRegion region;

    public VisualComponent(TextureRegion texture) {
        this.region = texture;
    }
}
