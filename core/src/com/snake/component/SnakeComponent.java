package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class SnakeComponent implements Pool.Poolable , Component {

    //== attributes ==
    private final Logger log = new Logger(SnakeComponent.class.getName() , Logger.DEBUG);

    public Entity head;
    public final Array<Entity> bodyParts = new Array<>();

    @Override
    public void reset() {
        head = null;
        bodyParts.clear();
    }
}
