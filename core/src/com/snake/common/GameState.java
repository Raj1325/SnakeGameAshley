package com.snake.common;

/**
 * Created by RAJ DIGHE on 25/10/2020
 **/


public enum GameState {
    READY,
    PLAYING,
    GAME_OVER;

    //== public methods ==
    public boolean isReady(){
        return this == READY;
    }

    public boolean isPlaying(){
        return this ==PLAYING;
    }

    public boolean isGameOver(){
        return this == GAME_OVER;
    }
}
