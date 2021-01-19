package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.snake.common.GameMapper;
import com.snake.component.DirectionComponent;
import com.snake.component.InputComponent;
import com.snake.entity.Direction;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class InputQueueSystem  extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            InputComponent.class,
            DirectionComponent.class
    ).get();

    public InputQueueSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent inputComponent = GameMapper.INPUT_QUEUE.get(entity);
        DirectionComponent directionComponent = GameMapper.DIRECTION.get(entity);

        boolean leftPressed = Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyJustPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyJustPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);

        Direction lastDirection = directionComponent.direction;
        if(inputComponent.inputQueue.size > 0){
            lastDirection = inputComponent.inputQueue.peek();
        }

        if(leftPressed && !lastDirection.isLeft()){
            inputComponent.inputQueue.add(Direction.LEFT);
        }else if(rightPressed && !lastDirection.isRight()){
            inputComponent.inputQueue.add(Direction.RIGHT);
        }else if(upPressed && !lastDirection.isUp()){
            inputComponent.inputQueue.add(Direction.UP);
        }else if(downPressed && !lastDirection.isDown()){
            inputComponent.inputQueue.add(Direction.DOWN);
        }
    }
}
