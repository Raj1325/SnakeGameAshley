package com.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.snake.common.EntityFactory;
import com.snake.common.GameMapper;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.config.GameConfig;
import com.snake.entity.BodyPart;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class CollisionSystem extends IntervalSystem {

    private static Family SNAKE_FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    private static final Family COIN_FAMILY = Family.all(
            CoinComponent.class
    ).get();

    private final EntityFactory factory;

    public CollisionSystem(EntityFactory factory){
        super(GameConfig.SNAKE_TIMER);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);

        for(Entity snake : snakes){
            for(Entity coin : coins){
                CoinComponent coinComponent = GameMapper.COIN.get(coin);
                SnakeComponent snakeComponent = GameMapper.SNAKE.get(snake);

                if(coinComponent.available && overlaps(coin , snakeComponent.head)){
                    //coin hit ... make it unavailable
                    coinComponent.available = false;

                    // attach body part
                    Entity head = snakeComponent.head;
                    Array<Entity> bodyParts = snakeComponent.bodyParts;
                    float x , y;
                    if(bodyParts.size > 0){
                        Entity currentTail = bodyParts.peek();
                        PositionComponent positionComponent = GameMapper.POSITION.get(currentTail);
                        x = positionComponent.getX();
                        y = positionComponent.getY();
                    }
                    else {
                        PositionComponent positionComponent = GameMapper.POSITION.get(head);
                        x = positionComponent.getX();
                        y = positionComponent.getY();
                    }

                    Entity body = factory.createBody(x , y);
                    snakeComponent.bodyParts.add(body);
                }
            }
        }
    }

    private boolean overlaps(Entity first, Entity second) {

        BoundsComponent firstBound = GameMapper.BOUNDS.get(first);
        BoundsComponent secondBound = GameMapper.BOUNDS.get(second);

        return Intersector.overlaps(firstBound.getBound() , secondBound.getBound());

    }
}
