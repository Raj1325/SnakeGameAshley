package com.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.assets.ButtonStyleNames;
import com.snake.assets.RegionName;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;
import com.snake.util.GdxUtils;

/**
 * Created by RAJ DIGHE on 27/10/2020
 **/


public class MenuScreen extends ScreenAdapter {

    //== attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private TextureAtlas gamePlayAtlas;

    //== constructors ==
    public MenuScreen(SnakeGame game){
        this.game = game;
        this.assetManager = game.getAssetManager();
        GameManager.INSTANCE.setStateReady();
    }

    //== public methods ==
    @Override
    public void show() {
        super.show();
        viewport = new FitViewport(GameConfig.HUD_WIDTH , GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport , game.getBatch());
        skin = assetManager.get(AssetDescriptors.UISKIN);
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(createUI());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        GdxUtils.clearScreen();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height ,true);
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

    //== private methods ==
    private Actor createUI(){
        Table table = new Table(skin);
        table.defaults().pad(10);

        TextureRegion background = gamePlayAtlas.findRegion(RegionName.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(background));

        Image title = new Image(skin , RegionName.TITLE);

        Button playButton = new Button(skin , ButtonStyleNames.PLAY);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        Button quitButton = new Button(skin , ButtonStyleNames.QUIT);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        table.add(title).row();
        table.add(playButton).row();
        table.add(quitButton);

        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }

    private void play(){
        game.setScreen(new GameScreen(game));
    }

    private void quit(){
        Gdx.app.exit();
    }
}
