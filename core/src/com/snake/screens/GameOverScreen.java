package com.snake.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.assets.RegionName;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 27/10/2020
 **/


public class GameOverScreen extends ScreenAdapter {

    private final Logger log = new Logger(GameOverScreen.class.getName() , Logger.DEBUG);
    //== attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private Stage stage;
    private TextureRegion background;
    private BitmapFont font;

    private float timer;

    //== constructors ==
    public GameOverScreen(SnakeGame game){
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        timer += delta;
        if(timer < GameConfig.GAMEOVER_SCREENTIMER){
            stage.act();
            stage.draw();
        }
        else{
            game.setScreen(new MenuScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height , true);
    }

    @Override
    public void show() {
        super.show();
        viewport = new FitViewport(GameConfig.HUD_WIDTH , GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport , game.getBatch());
        TextureAtlas gameplayaltas = assetManager.get(AssetDescriptors.GAMEPLAY);
        background = gameplayaltas.findRegion(RegionName.BACKGROUND);
        font = assetManager.get(AssetDescriptors.UI_FONT);
        stage.addActor(createUI());
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    private Actor createUI() {
        Table table = new Table();
        table.defaults().pad(20);
        table.setBackground(new TextureRegionDrawable(background));

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.CORAL;
        Label label = new Label("GAME OVER" , style);

        table.add(label);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;

    }
}
