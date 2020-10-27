package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class DimensionComponent implements Component , Pool.Poolable {

    //== attributes ==
    private float width;
    private float height;


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void reset() {
        this.width = 1f;
        this.height = 1f;
    }
}
