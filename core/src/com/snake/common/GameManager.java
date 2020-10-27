package com.snake.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.snake.SnakeGame;

/**
 * Created by RAJ DIGHE on 25/10/2020
 **/


public class GameManager {

    //== constants ==
    public static final GameManager INSTANCE = new GameManager();
    private final String HIGHSCORE = "highscore";

    //== attributes ==
    private GameState state = GameState.READY;
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

    private Preferences preferences;

    // == CONSTRUCTORS ==
    private GameManager(){
        preferences = Gdx.app.getPreferences(SnakeGame.class.getSimpleName());
        highScore = preferences.getInteger(HIGHSCORE , 0);
        displayHighScore = highScore;
    }

    // == PUBLIC METHODS ==
    public boolean isReady(){
        return state.isReady();
    }

    public boolean isPlaying(){
        return state.isPlaying();
    }

    public boolean isGameOver(){
        return state.isGameOver();
    }

    public void setStateReady(){
        this.state = GameState.READY;
    }

    public void setStatePlaying(){
        this.state = GameState.PLAYING;
    }

    public void setStateGameOver(){
        this.state = GameState.GAME_OVER;
    }

    public int getScore() {
        return score;
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void incrementScore(int amount){
        score += amount;
        if(score > highScore){
            highScore = score;
        }
    }

    public void reset(){
        score = 0;
        displayScore = 0;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public void updateDisplayScore(float delta){
        if(displayScore < score){
            displayScore = Math.min(score , displayScore + (int)(100 * delta));
        }

        if(displayHighScore < highScore){
            displayHighScore = Math.min(highScore , displayHighScore + (int)(100*delta));
        }
    }

    public void updateHighScore(){
        if(score < highScore){return;}

        highScore = score;
        preferences.putInteger(HIGHSCORE , highScore);
        preferences.flush();
    }

}
