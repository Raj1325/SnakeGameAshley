package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.snake.common.GameMapper;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class CoinSpawnSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CoinComponent.class,
            PositionComponent.class
    ).get();

    public CoinSpawnSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CoinComponent coinComponent = GameMapper.COIN.get(entity);
        PositionComponent positionComponent = GameMapper.POSITION.get(entity);

        if(coinComponent.available){return;}

        float coinX = (int) MathUtils.random(GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE);
        float coinY = (int)MathUtils.random(GameConfig.WORLD_HEIGHT - GameConfig.GAME_Y_OFFSET - GameConfig.COIN_SIZE);
        coinComponent.available = true;
        positionComponent.setPosition(coinX , coinY);
    }
}
