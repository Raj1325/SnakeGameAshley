package com.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.snake.collision.CollisionListener;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;
import com.snake.entity.BodyPart;
import com.snake.entity.Coin;
import com.snake.entity.Direction;
import com.snake.entity.Snake;
import com.snake.entity.SnakeHead;

/*
    Created by rppdi on 25/10/2020
*/
@Deprecated
public class GameControllerOld {

    private final Logger log = new Logger(GameControllerOld.class.getName() , Logger.DEBUG);

    //== attributes ==
    private Snake snake;
    private float timer;
    private final CollisionListener listener;

    private Coin coin;

    public GameControllerOld(CollisionListener listener){
        snake = new Snake();
        coin = new Coin();
        this.listener = listener;
    }


    //== public methods ==
    public Snake getSnake() {
        return snake;
    }

    public Coin getCoin(){
        return coin;
    }

    public void update(float delta){

        if(GameManager.INSTANCE.isPlaying()) {

            queryInput();
            queryDebugInput();
            checkOutOfBounds();
            timer += delta;
            if (timer > GameConfig.SNAKE_TIMER) {
                timer = 0;
                snake.move();
            }
            spawnCoin();
            checkCollision();
            GameManager.INSTANCE.updateDisplayScore(delta);
        }
        else if(GameManager.INSTANCE.isReady()){
            checkForRestart();
        }
    }

    private void queryInput() {

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN){
            if(leftPressed){
                snake.setDirection(Direction.LEFT);
            }
            else if(rightPressed){
                snake.setDirection(Direction.RIGHT);
            }
        }else if(snake.getDirection() == Direction.LEFT || snake.getDirection() == Direction.RIGHT)
        {
            if(upPressed){
                snake.setDirection(Direction.UP);
            }
            else if(downPressed){
                snake.setDirection(Direction.DOWN);
            }
        }
        else{
            log.debug("U r in wrong business bruh....");
        }

    }

    private void queryDebugInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.PLUS)){
            snake.insertBodyPart();
        }
    }

    private void checkOutOfBounds(){
        SnakeHead head = snake.getHead();
        // x axis
        if(head.getX() >= GameConfig.WORLD_WIDTH){
            head.setPosition(0 , head.getY());
        }
        else if(head.getX() < 0){
            head.setPosition(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE , head.getY());
        }

        // y axis
        if(head.getY() >= GameConfig.WORLD_HEIGHT - GameConfig.GAME_Y_OFFSET){
            head.setPosition(head.getX() , 0);
        }else if(head.getY() < 0){
            head.setPosition(head.getX() , GameConfig.WORLD_HEIGHT - GameConfig.SNAKE_SIZE -GameConfig.GAME_Y_OFFSET);
        }
    }

    private void spawnCoin(){

        if(coin.isAvailable()){return;}

        float coinX = (int)MathUtils.random(GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE);
        float coinY = (int)MathUtils.random(GameConfig.WORLD_HEIGHT - GameConfig.GAME_Y_OFFSET - GameConfig.COIN_SIZE);
        coin.setAvailable(true);
        coin.setPosition(coinX , coinY);
        listener.newCoin();
    }

    private void checkCollision(){
        SnakeHead head = snake.getHead();

        if(coin.isAvailable() && Intersector.overlaps(head.getBounds() , coin.getBounds())){
            coin.setAvailable(false);
            GameManager.INSTANCE.incrementScore(GameConfig.COIN_SCORE);
            snake.insertBodyPart();
            listener.hitCoin();
        }


        Array<BodyPart> body = snake.getBody();
        //start with index 1 as 0th element is just behind head and will give false collision
        for(int i = 1 ; i < snake.getBody().size ; i++){
            BodyPart bodyPart = body.get(i);
            if(Intersector.overlaps(bodyPart.getBounds() , head.getBounds())){
                log.debug("Game Over...");
                GameManager.INSTANCE.updateHighScore();
                GameManager.INSTANCE.setStateGameOver();
                snake.reset();
                coin.setAvailable(false);
                listener.hitBody();
            }
        }

    }

    private void checkForRestart(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            restart();
        }
    }

    private void restart() {
        GameManager.INSTANCE.setStatePlaying();
    }
}
