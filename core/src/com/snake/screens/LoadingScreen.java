package com.snake.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 26/10/2020
 **/


public class LoadingScreen extends ScreenAdapter {

    //== constants ==
    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH/2f;
    private static final float PROGRRESS_BAR_HEIGHT = 60f;

    //== attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float waitTime = 0.75f;
    private boolean doneLoading;

    //== constructor ==
    public LoadingScreen(SnakeGame game){
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH , GameConfig.HUD_HEIGHT , camera);
        renderer = new ShapeRenderer();
        assetManager.load(AssetDescriptors.UI_FONT);
        assetManager.load(AssetDescriptors.GAMEPLAY);
        assetManager.load(AssetDescriptors.UISKIN);
        assetManager.load(AssetDescriptors.COIN_SOUND);
        assetManager.load(AssetDescriptors.LOSE_SOUND);
        assetManager.load(AssetDescriptors.POWERUP_SOUND);
        doneLoading = false;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
        if(doneLoading){
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width , height , true);
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }

    //== private methods ==
    private void update(float delta){
        progress = assetManager.getProgress();
        if(assetManager.update()){
            //if true everything is loaded
            waitTime -= delta;
            if(waitTime <= 0){
                doneLoading = true;
            }
        }
    }

    private void draw(){
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f,
                (GameConfig.HUD_HEIGHT - PROGRRESS_BAR_HEIGHT)/2f,
                progress * PROGRESS_BAR_WIDTH,
                PROGRRESS_BAR_HEIGHT
        );
        renderer.end();
    }
}
