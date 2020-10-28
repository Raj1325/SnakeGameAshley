package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.snake.common.GameMapper;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;
import com.snake.component.WorldWrapComponent;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class WorldWrapSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            WorldWrapComponent.class
    ).get();

    public WorldWrapSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = GameMapper.POSITION.get(entity);

        // x axis
        if(positionComponent.getX() >= GameConfig.WORLD_WIDTH){
            positionComponent.setPosition(0 , positionComponent.getY());
        }
        else if(positionComponent.getX() < 0){
            positionComponent.setPosition(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE , positionComponent.getY());
        }

        // y axis
        if(positionComponent.getY() >= GameConfig.WORLD_HEIGHT - GameConfig.GAME_Y_OFFSET){
            positionComponent.setPosition(positionComponent.getX() , 0);
        }else if(positionComponent.getY() < 0){
            positionComponent.setPosition(positionComponent.getX() , GameConfig.WORLD_HEIGHT - GameConfig.SNAKE_SIZE -GameConfig.GAME_Y_OFFSET);
        }
    }
}
