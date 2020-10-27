package com.snake.entity;

import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 25/10/2020
 **/

@Deprecated
public class Coin extends EntityBase{

    //== attributes ==
    private boolean available;

    //== constructors ==
    public Coin(){
        setSize(GameConfig.COIN_SIZE , GameConfig.COIN_SIZE);
    }

    //== public methods ==
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void reset(){
        this.available = false;
    }
}
