package com.snake.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.common.EntityFactory;
import com.snake.config.GameConfig;
import com.snake.system.BoundSystem;
import com.snake.system.CoinSpawnSystem;
import com.snake.system.CollisionSystem;
import com.snake.system.InputSystem;
import com.snake.system.MovementSystem;
import com.snake.system.WorldWrapSystem;
import com.snake.system.debug.DebugCameraSystem;
import com.snake.system.debug.DebugRenderSystem;
import com.snake.system.debug.GridRenderSystem;
import com.snake.system.passive.SnakeSystem;
import com.snake.util.GdxUtils;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class GameScreen extends ScreenAdapter {

    private final Logger log = new Logger(GameScreen.class.getName() , Logger.DEBUG);

    //== attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;

    private EntityFactory entityFactory;
    private Entity snake;
    private Entity coin;


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

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            engine.removeEntity(snake);
        }
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
        entityFactory = new EntityFactory(engine);

        engine.addSystem(new GridRenderSystem(this.viewport, this.renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X,
                GameConfig.WORLD_CENTER_Y,
                (OrthographicCamera) this.viewport.getCamera())
        );
        engine.addSystem(new DebugRenderSystem(viewport , renderer));
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new InputSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSpawnSystem());
        engine.addSystem(new CollisionSystem(this.entityFactory));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundSystem());

        snake = entityFactory.createSnake();
        entityFactory.addCoin();

        engine.addEntity(snake);

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
