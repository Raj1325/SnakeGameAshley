package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.snake.common.GameMapper;
import com.snake.component.DirectionComponent;
import com.snake.component.InputComponent;
import com.snake.component.PlayerComponent;
import com.snake.config.GameConfig;
import com.snake.entity.Direction;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class InputSystem extends IntervalIteratingSystem {

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class,
            InputComponent.class
    ).get();

    public InputSystem(){
        super(FAMILY , GameConfig.SNAKE_TIMER);
    }

    @Override
    protected void processEntity(Entity entity) {

        DirectionComponent directionComponent = GameMapper.DIRECTION.get(entity);
        InputComponent inputComponent = GameMapper.INPUT_QUEUE.get(entity);

        Direction direction = directionComponent.direction;
        if(inputComponent.inputQueue.size > 0){
            direction = inputComponent.inputQueue.removeIndex(0);
        }

        if(directionComponent.isUp() || directionComponent.isDown()){
            if(direction.isLeft()){
                directionComponent.setDirection(Direction.LEFT);
            }
            else if(direction.isRight()){
                directionComponent.setDirection(Direction.RIGHT);
            }
        }else if(directionComponent.isRight() || directionComponent.isLeft()){
            if(direction.isUp()){
                directionComponent.setDirection(Direction.UP);
            }
            else if(direction.isDown()){
                directionComponent.setDirection(Direction.DOWN);
            }
        }
    }
}
