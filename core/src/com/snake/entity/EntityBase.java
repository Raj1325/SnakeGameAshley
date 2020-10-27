package com.snake.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*
    Created by rppdi on 25/10/2020
*/
@Deprecated
public abstract class EntityBase {

    //== attributes ==
    protected Vector2 position;
    protected Vector2 size;
    protected Rectangle bounds;

    //== contructors ==
    public EntityBase() {
        position = new Vector2();
        size = new Vector2(1, 1);
        bounds = new Rectangle(
                position.x, position.y,
                size.x, size.y
        );
    }

    //== public methods ==
    public void setPosition(float x , float y){
        position.x = x;
        position.y = y;
        updateBounds();
    }

    public void setSize(float width , float height) {
        size.x = width;
        size.y = height;
        updateBounds();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //== attributes ==

    private void updateBounds() {
        bounds.setPosition(position.x , position.y);
        bounds.setSize(size.x , size.y);
    }


}
