package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.snake.common.GameMapper;
import com.snake.component.BoundsComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class BoundSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            BoundsComponent.class,
            DimensionComponent.class
    ).get();

    public BoundSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = GameMapper.POSITION.get(entity);
        DimensionComponent dimensionComponent = GameMapper.DIMENSION.get(entity);
        BoundsComponent boundsComponent = GameMapper.BOUNDS.get(entity);

        boundsComponent.setPosition(positionComponent.getX() , positionComponent.getY());
        boundsComponent.setSize(dimensionComponent.getWidth() , dimensionComponent.getHeight());
    }
}
