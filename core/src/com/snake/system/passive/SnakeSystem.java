package com.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.snake.common.GameMapper;
import com.snake.component.SnakeComponent;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class SnakeSystem extends EntitySystem implements EntityListener {

    private final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    @Override
    public boolean checkProcessing() {
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(FAMILY , this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        engine.removeEntityListener(this);
    }


    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {
        SnakeComponent snakeComponent = GameMapper.SNAKE.get(entity);
        Entity head = snakeComponent.head;
        getEngine().removeEntity(head);

        for(Entity body : snakeComponent.bodyParts){
            getEngine().removeEntity(body);
        }
    }
}
