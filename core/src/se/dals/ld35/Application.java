package se.dals.ld35;

import com.badlogic.gdx.Game;
import se.dals.ld35.screens.GameScreen;

/**
 * Created by david on 2016-04-16.
 */
public class Application extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }


}
