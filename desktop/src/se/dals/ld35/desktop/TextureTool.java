package se.dals.ld35.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by david on 2016-04-17.
 */
public class TextureTool {
    public static void main(String[] args) {
        TexturePacker.process("Tiles/in","Tiles/out","tile_sheet");
    }
}
