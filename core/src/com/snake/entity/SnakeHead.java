package com.snake.entity;

import com.badlogic.gdx.utils.Logger;
import com.snake.config.GameConfig;

/*
    Created by rppdi on 25/10/2020
*/
@Deprecated
public class SnakeHead extends EntityBase{

    private final Logger log = new Logger(SnakeHead.class.getName() , Logger.DEBUG);

    //== attributes ==

    public SnakeHead(){
        setSize(GameConfig.SNAKE_SIZE , GameConfig.SNAKE_SIZE);
    }


    //== public methods ==
    public void updateY(float v) {
        setPosition((int)getX()  , (int)(getY() + v));
    }

    public void updateX(float snakeSpeed) {
        setPosition((int)(getX() + snakeSpeed) , (int)getY());
    }

    public void reset(){
        setPosition(0 , 0);
    }
}
