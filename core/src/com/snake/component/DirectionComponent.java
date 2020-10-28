package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.snake.entity.Direction;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class DirectionComponent implements Component , Pool.Poolable {

    public Direction direction = Direction.RIGHT;

    public boolean isLeft(){
        return direction.isLeft();
    }

    public boolean isRight(){
        return direction.isRight();
    }

    public boolean isUp(){
        return direction.isUp();
    }

    public boolean isDown(){
        return direction.isDown();
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    @Override
    public void reset() {
        direction = Direction.RIGHT;
    }
}
