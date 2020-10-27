package com.snake.entity;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 25/10/2020
 **/

@Deprecated
public class Snake {

    private final Logger log = new Logger(Snake.class.getName() , Logger.DEBUG);

    //== attributes ==
    private SnakeHead head;
    private Array<BodyPart> body;
    private Direction direction;

    public Snake(){
        head = new SnakeHead();
        body = new Array<>();
        direction = Direction.RIGHT;
    }

    //== public methods ==
    public void move(){
        float previousHeadX , previousHeadY;
        previousHeadX = head.getX();
        previousHeadY = head.getY();

        if(direction.isRight()){
            head.updateX(GameConfig.SNAKE_SPEED);
        } else if(direction.isLeft()){
            head.updateX(-GameConfig.SNAKE_SPEED);
        }else if(direction.isUp()){
            head.updateY(GameConfig.SNAKE_SPEED);
        }else if(direction.isDown()){
            head.updateY(-GameConfig.SNAKE_SPEED);
        }

        updateBodyPart(previousHeadX , previousHeadY);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public SnakeHead getHead() {
        return this.head;
    }

    public Array<BodyPart> getBody() {
        return body;
    }

    public void insertBodyPart(){
        BodyPart bodyPart = new BodyPart();
        float x , y;
        if(body.size > 0){
            BodyPart currentTail = body.peek();
            x = currentTail.getX();
            y = currentTail.getY();
        }
        else {
            x = head.getX();
            y = head.getY();
        }

        bodyPart.setPosition(x, y);
        body.add(bodyPart);
    }

    public Direction getDirection(){
        return direction;
    }

    public void reset(){
        body.clear();
        head.reset();
    }

    //== private methods ==
    private void updateBodyPart(float trailBodyX , float trailBodyY) {

        float tempX , tempY;

        for(BodyPart bodyPart : body){
            tempX = bodyPart.getX();
            tempY = bodyPart.getY();

            bodyPart.setPosition(trailBodyX , trailBodyY);

            trailBodyX = tempX;
            trailBodyY = tempY;
        }

    }
}
