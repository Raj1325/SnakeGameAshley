package com.snake.entity;

import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 25/10/2020
 **/

@Deprecated
public class BodyPart extends EntityBase {

    public BodyPart(){
        setSize(GameConfig.SNAKE_SIZE , GameConfig.SNAKE_SIZE);
    }
}
