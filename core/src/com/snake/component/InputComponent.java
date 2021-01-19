package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.snake.entity.Direction;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class InputComponent implements Component {

    public Array<Direction> inputQueue = new Array<>();
}
