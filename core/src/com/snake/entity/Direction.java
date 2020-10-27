package com.snake.entity;

/*
    Created by rppdi on 25/10/2020
*/
public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    //== public methods ==
    public boolean isUp(){return this == UP;}
    public boolean isDown(){return this == DOWN;}
    public boolean isLeft(){return this == LEFT;}
    public boolean isRight(){return this == RIGHT;}

}
