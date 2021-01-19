package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class ZOrderComponent implements Component , Pool.Poolable {

    public int zorder = -1;

    @Override
    public void reset() {
        zorder = -1;
    }
}
