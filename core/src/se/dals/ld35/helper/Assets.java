package se.dals.ld35.helper;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by david on 2016-04-17.
 */
public class Assets {

    public static final String TILE_SHEET = "tile_sheet.atlas";
    public static final String TILE_CONFIG = "tile_config.json";
    public static final String MAP = "map.png";

    public AssetManager manager;
    public Assets() {
        this.manager = new AssetManager();
        loadGamedata();
    }

    public void loadGamedata(){
        manager.load(TILE_SHEET, TextureAtlas.class);
        manager.load(MAP, Pixmap.class);
        manager.finishLoading();//blocks main thread til done.
    }


}
