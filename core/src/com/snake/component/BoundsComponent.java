package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;


/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class BoundsComponent implements Pool.Poolable , Component {

    private final Rectangle bound = new Rectangle(0,0,1,1);

    public Rectangle getBound() {
        return bound;
    }

    public void setPosition(float x , float y){
        bound.setPosition(x , y);
    }

    public void setSize(float width , float height){
        bound.setSize(width , height);
    }

    public float getWidth(){
        return bound.width;
    }

    public float getHeight(){
        return bound.height;
    }

    @Override
    public void reset() {
        bound.setPosition(0,0);
        bound.setSize(1,1);
    }
}
