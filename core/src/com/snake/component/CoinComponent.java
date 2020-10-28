package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class CoinComponent implements Component, Pool.Poolable {

    public boolean available;

    @Override
    public void reset() {
        available = false;
    }
}
