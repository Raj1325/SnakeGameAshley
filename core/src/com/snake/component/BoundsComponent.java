package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;


/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class BoundsComponent implements Pool.Poolable , Component {

    private final Rectangle bound = new Rectangle(0,0,1,1);

    @Override
    public void reset() {
        bound.setPosition(0,0);
        bound.setSize(1,1);
    }
}
