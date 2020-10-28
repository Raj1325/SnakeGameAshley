package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.snake.common.GameMapper;
import com.snake.component.DirectionComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.SpeedComponent;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class MovementSystem extends IntervalIteratingSystem {

    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    public MovementSystem(){
        super(FAMILY , GameConfig.SNAKE_TIMER);
    }

    @Override
    protected void processEntity(Entity entity) {

        SnakeComponent snakeComponent = GameMapper.SNAKE.get(entity);
        PositionComponent previousHeadPos  = GameMapper.POSITION.get(snakeComponent.head);
        float trailBodyX = previousHeadPos.getX();
        float trailBodyY = previousHeadPos.getY();

        moveHead(snakeComponent.head);
        moveBody(snakeComponent , trailBodyX , trailBodyY);

    }

    private void moveBody(SnakeComponent snake , float trailBodyX, float trailBodyY) {
        Array<Entity> bodyParts = snake.bodyParts;

        float tempX , tempY;
        for(Entity body : bodyParts){
            PositionComponent positionComponent = GameMapper.POSITION.get(body);
            tempX = positionComponent.getX();
            tempY = positionComponent.getY();

            positionComponent.setPosition(trailBodyX , trailBodyY);

            trailBodyX = tempX;
            trailBodyY = tempY;
        }
    }

    private void moveHead(Entity head) {

        SpeedComponent speedComponent = GameMapper.SPEED.get(head);
        DirectionComponent directionComponent = GameMapper.DIRECTION.get(head);
        PositionComponent positionComponent = GameMapper.POSITION.get(head);

        speedComponent.reset();

        if(directionComponent.isRight()){
            speedComponent.speedX += GameConfig.SNAKE_SPEED;
        }else if(directionComponent.isLeft()){
            speedComponent.speedX -= GameConfig.SNAKE_SPEED;
        }else if(directionComponent.isUp()){
            speedComponent.speedY += GameConfig.SNAKE_SPEED;
        }else if(directionComponent.isDown()){
            speedComponent.speedY -= GameConfig.SNAKE_SPEED;
        }

        positionComponent.setX(speedComponent.speedX + positionComponent.getX());
        positionComponent.setY(speedComponent.speedY + positionComponent.getY());

    }
}
