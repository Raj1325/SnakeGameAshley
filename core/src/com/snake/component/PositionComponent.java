package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class PositionComponent implements Pool.Poolable , Component {

    //== attrivutes ==
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x , float y){
        this.x = x ;
        this.y = y;
    }

    //== public methods ==
    @Override
    public void reset() {
        this.x = 0f;
        this.y = 0f;
    }
}
