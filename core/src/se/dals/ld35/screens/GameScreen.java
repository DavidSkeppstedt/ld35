package se.dals.ld35.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.dals.ld35.components.*;
import se.dals.ld35.entities.WorldBuilder;
import se.dals.ld35.helper.Assets;
import se.dals.ld35.helper.LevelParser;
import se.dals.ld35.systems.*;

/**
 * Created by david on 2016-04-16.
 */
public class GameScreen implements Screen {

    private Game game;
    private Assets assets;
    private PooledEngine engine;
    private OrthographicCamera cam;
    private Viewport viewport;

    public GameScreen(Game game,Assets assets) {
        this.assets = assets;
        this.game = game;
        this.engine = new PooledEngine();
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(20,20f * (720f/1280f),cam);
        this.viewport.apply();
        this.cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
        this.cam.update();

        //Game systems..
        engine.addSystem(new RenderSystem(cam));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerMovementSystem());
        engine.addSystem(new PlayerJumpSystem());
        engine.addSystem(new GravitySystem());


        //Debug systems.
        engine.addSystem(new DebugRenderSystem(cam));

        //Adding entities...
        LevelParser parser = new LevelParser(assets.manager.get(Assets.MAP,Pixmap.class));
        WorldBuilder.BuildWorld(this.engine,parser.parse(),assets);

        Entity player = engine.createEntity();
        player.add(new PositionComponent(1,1));
        player.add(new SizeComponent(1,1));
        player.add(new PlayerTagComponent());
        player.add(new VelocityComponent(0,0));
        player.add(new JumpComponent(64*4,64*2));
        player.add(new VisualComponent(new TextureRegion(new Texture("player.png"))));

        engine.addEntity(player);
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 100/255f, 238/255f, 238/255f, 1 );
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
