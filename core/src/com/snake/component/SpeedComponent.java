package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class SpeedComponent implements Component , Pool.Poolable {

    public float speedX;
    public float speedY;


    @Override
    public void reset() {
        speedX = 0;
        speedY = 0;
    }
}
