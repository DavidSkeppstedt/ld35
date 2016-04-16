package se.dals.ld35.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.dals.ld35.components.PositionComponent;
import se.dals.ld35.components.SizeComponent;
import se.dals.ld35.components.VisualComponent;
import se.dals.ld35.systems.RenderSystem;

/**
 * Created by david on 2016-04-16.
 */
public class GameScreen implements Screen {

    private Game game;
    private PooledEngine engine;
    private OrthographicCamera cam;
    private Viewport viewport;

    public GameScreen(Game game) {
        this.game = game;
        this.engine = new PooledEngine();
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(20,20f * (720f/1280f),cam);
        this.viewport.apply();
        this.cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
        this.cam.update();


        engine.addSystem(new RenderSystem(cam));


        Texture block = new Texture("block.png");


        Entity e = engine.createEntity();
        e.add(new PositionComponent());
        e.add(new VisualComponent(block));
        e.add(new SizeComponent(1,1));


        engine.addEntity(e);
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
