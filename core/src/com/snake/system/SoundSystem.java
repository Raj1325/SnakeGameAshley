package com.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.snake.assets.AssetDescriptors;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;
    private Sound newCoin;
    private Sound hitCoin;
    private Sound hitBody;

    public SoundSystem(AssetManager assetManager){
        this.assetManager = assetManager;
        newCoin = assetManager.get(AssetDescriptors.COIN_SOUND);
        hitCoin = assetManager.get(AssetDescriptors.POWERUP_SOUND);
        hitBody = assetManager.get(AssetDescriptors.LOSE_SOUND);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public boolean checkProcessing() {
        return false;
    }

    public void playCoinSpawn(){
        newCoin.play();
    }

    public void playHitCoin(){
        hitCoin.play();
    }

    public void playBodyHit(){
        hitBody.play();
    }

}
