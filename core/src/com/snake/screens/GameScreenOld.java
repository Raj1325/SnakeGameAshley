package com.snake.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Logger;
import com.snake.SnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.collision.CollisionListener;
import com.snake.common.GameManager;

/*
    Created by rppdi on 25/10/2020
*/
@Deprecated
public class GameScreenOld extends ScreenAdapter {

    private final Logger log = new Logger(GameScreenOld.class.getName() , Logger.DEBUG);

    //== attributes ==
    private final SnakeGame game;

    private final AssetManager assetManager;
    private GameControllerOld controller;
    private GameRendererOld renderer;
    private CollisionListener listener;

    private Sound newCoin;
    private Sound hitCoin;
    private Sound hitBody;

    //== constructors ==
    public GameScreenOld(SnakeGame game){
        this.game = game;
        assetManager = game.getAssetManager();
        newCoin = assetManager.get(AssetDescriptors.COIN_SOUND);
        hitBody = assetManager.get(AssetDescriptors.LOSE_SOUND);
        hitCoin = assetManager.get(AssetDescriptors.POWERUP_SOUND);
        GameManager.INSTANCE.setStateReady();
        listener = new CollisionListener() {
            @Override
            public void newCoin() {
                newCoin.play();
            }

            @Override
            public void hitBody() {
                hitBody.play();
            }

            @Override
            public void hitCoin() {
                hitCoin.play();
            }
        };
    }

    //== public methods ==
    @Override
    public void show() {
        super.show();
        controller = new GameControllerOld(this.listener);
        renderer    = new GameRendererOld(game.getBatch() , game.getAssetManager() , controller);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        controller.update(delta);
        renderer.render(delta);
        if(GameManager.INSTANCE.isGameOver()){
            game.setScreen(new GameOverScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
        dispose(); //as dispose is not called automatically
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }
}
