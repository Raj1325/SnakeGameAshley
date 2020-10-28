package com.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.PlayerComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.SpeedComponent;
import com.snake.component.WorldWrapComponent;
import com.snake.config.GameConfig;
import com.snake.entity.Coin;
import com.snake.system.WorldWrapSystem;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class EntityFactory {

    //== attributes ==
    private final PooledEngine engine;


    //== constructors ==
    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    //== public methods ==
    public Entity createSnakeHead(){

        //components..
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setWidth(GameConfig.SNAKE_SIZE);
        dimensionComponent.setHeight(GameConfig.SNAKE_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.setSize(dimensionComponent.getWidth() , dimensionComponent.getHeight());

        SpeedComponent speedComponent = engine.createComponent(SpeedComponent.class);
        DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        WorldWrapComponent worldWrapComponent = engine.createComponent(WorldWrapComponent.class);

        Entity head = engine.createEntity();
        head.add(positionComponent);
        head.add(dimensionComponent);
        head.add(boundsComponent);
        head.add(speedComponent);
        head.add(directionComponent);
        head.add(playerComponent);
        head.add(worldWrapComponent);

        engine.addEntity(head);

        return head;
    }

    public Entity createSnake(){
        //components ....

        SnakeComponent snakeComponent = engine.createComponent(SnakeComponent.class);
        snakeComponent.head = createSnakeHead();

        Entity snake = engine.createEntity();
        snake.add(snakeComponent);

        return snake;
    }

    public void addCoin(){
        CoinComponent coinComponent = engine.createComponent(CoinComponent.class);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setWidth(GameConfig.COIN_SIZE);
        dimensionComponent.setHeight(GameConfig.COIN_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);

        Entity coin = engine.createEntity();
        coin.add(coinComponent);
        coin.add(positionComponent);
        coin.add(dimensionComponent);
        coin.add(boundsComponent);

        engine.addEntity(coin);

    }

    public Entity createBody(float x , float y){

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);

        positionComponent.setX(x);
        positionComponent.setY(y);

        dimensionComponent.setWidth(GameConfig.SNAKE_SIZE);
        dimensionComponent.setHeight(GameConfig.SNAKE_SIZE);

        boundsComponent.setPosition(x , y);
        boundsComponent.setSize(GameConfig.SNAKE_SIZE , GameConfig.SNAKE_SIZE);

        Entity body = engine.createEntity();
        body.add(positionComponent);
        body.add(dimensionComponent);
        body.add(boundsComponent);

        engine.addEntity(body);

        return body;

    }
}
