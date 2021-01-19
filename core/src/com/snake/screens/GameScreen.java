package com.snake.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.common.EntityFactory;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;
import com.snake.system.BoundSystem;
import com.snake.system.CoinSpawnSystem;
import com.snake.system.CollisionSystem;
import com.snake.system.HUDRenderSystem;
import com.snake.system.InputQueueSystem;
import com.snake.system.InputSystem;
import com.snake.system.MovementSystem;
import com.snake.system.RenderSystem;
import com.snake.system.SoundSystem;
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

    private Viewport hudViewport;
    private BitmapFont font;

    private EntityFactory entityFactory;
    private Entity snake;


    //== constructors ==
    public GameScreen(SnakeGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        GameManager.INSTANCE.setStateReady();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        GdxUtils.clearScreen();

        if(GameManager.INSTANCE.isPlaying()) {
            engine.update(delta);

            if (Gdx.input.isKeyPressed(Input.Keys.R)) {
                engine.removeEntity(snake);
            }
        }else if(GameManager.INSTANCE.isReady()){
            checkForRestart();
        }else if(GameManager.INSTANCE.isGameOver()){
            game.setScreen(new GameOverScreen(game));
        }
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
        hudViewport.update(width, height , true);
    }

    @Override
    public void show() {
        super.show();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH , GameConfig.HUD_HEIGHT);
        font = assetManager.get(AssetDescriptors.UI_FONT);

        engine = new PooledEngine();
        entityFactory = new EntityFactory(engine , assetManager);

        engine.addSystem(new SoundSystem(assetManager));
        engine.addSystem(new GridRenderSystem(this.viewport, this.renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X,
                GameConfig.WORLD_CENTER_Y,
                (OrthographicCamera) this.viewport.getCamera())
        );
        engine.addSystem(new DebugRenderSystem(viewport , renderer));
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new InputQueueSystem());
        engine.addSystem(new InputSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSpawnSystem());
        engine.addSystem(new CollisionSystem(this.entityFactory));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundSystem());
        engine.addSystem(new RenderSystem(viewport , game.getBatch()));
        engine.addSystem(new HUDRenderSystem(hudViewport , game.getBatch() , font));

        snake = entityFactory.createSnake();
        entityFactory.addCoin();

        engine.addEntity(snake);
        engine.addEntity(entityFactory.addBackground());

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
        engine.removeAllEntities();
    }

    private void checkForRestart(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            restart();
        }
    }

    private void restart() {
        GameManager.INSTANCE.setStatePlaying();
    }
}
