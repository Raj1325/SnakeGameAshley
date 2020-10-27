package com.snake.config;

/*
    Created by rppdi on 25/10/2020
*/
public class GameConfig {

    // == constants ==
    public static final float WIDTH = 800f; //pixels
    public static final float HEIGHT = 480f; //pixels

    public static final float WORLD_WIDTH = 25f;
    public static final float WORLD_HEIGHT = 15f;

    public static final float HUD_WIDTH = 800f;
    public static final float HUD_HEIGHT = 480f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;

    public static final float SNAKE_SIZE = 1;
    public static final float SNAKE_SPEED = 1;
    public static final float SNAKE_TIMER = 0.15f;

    public static final float COIN_SIZE = 1;
    public static final int COIN_SCORE = 10;

    public static final int GAME_Y_OFFSET = 2;

    public static final float GAMEOVER_SCREENTIMER = 2f;

    private GameConfig(){};

}
