package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.snake.common.GameMapper;
import com.snake.component.DirectionComponent;
import com.snake.component.PlayerComponent;
import com.snake.component.PositionComponent;
import com.snake.config.GameConfig;
import com.snake.entity.Direction;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class InputSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    public InputSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity , float deltaTime) {
        DirectionComponent directionComponent = GameMapper.DIRECTION.get(entity);

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(directionComponent.isUp() || directionComponent.isDown()){
            if(leftPressed){
                directionComponent.setDirection(Direction.LEFT);
            }
            else if(rightPressed){
                directionComponent.setDirection(Direction.RIGHT);
            }
        }else if(directionComponent.isRight() || directionComponent.isLeft()){
            if(upPressed){
                directionComponent.setDirection(Direction.UP);
            }
            else if(downPressed){
                directionComponent.setDirection(Direction.DOWN);
            }
        }
    }
}
