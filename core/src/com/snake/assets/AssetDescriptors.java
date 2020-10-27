package com.snake.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by RAJ DIGHE on 26/10/2020
 **/


public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> UI_FONT =
            new AssetDescriptor<BitmapFont>(AssetPath.UI_FONT , BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> GAMEPLAY =
            new AssetDescriptor<TextureAtlas>(AssetPath.GAMEPLAY , TextureAtlas.class);

    public static final AssetDescriptor<Skin> UISKIN =
            new AssetDescriptor<Skin>(AssetPath.UISKIN , Skin.class);

    public static final AssetDescriptor<Sound> COIN_SOUND =
            new AssetDescriptor<Sound>(AssetPath.COIN_SOUND , Sound.class);

    public static final AssetDescriptor<Sound> LOSE_SOUND =
            new AssetDescriptor<Sound>(AssetPath.LOSE_SOUND , Sound.class);

    public static final AssetDescriptor<Sound> POWERUP_SOUND =
            new AssetDescriptor<Sound>(AssetPath.POWER_UP , Sound.class);

    private AssetDescriptors(){}
}
