package com.snake.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.config.GameConfig;
import com.snake.system.debug.DebugCameraSystem;
import com.snake.system.debug.GridRenderSystem;
import com.snake.util.GdxUtils;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class GameScreen extends ScreenAdapter {

    //== attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;


    //== constructors ==
    public GameScreen(SnakeGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        GdxUtils.clearScreen();

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        super.show();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();

        engine.addSystem(new GridRenderSystem(this.viewport, this.renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X,
                GameConfig.WORLD_CENTER_Y,
                (OrthographicCamera) this.viewport.getCamera())
        );
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
}
