package com.snake.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.SpeedComponent;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class GameMapper {

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<SnakeComponent> SNAKE =
            ComponentMapper.getFor(SnakeComponent.class);

    public static final ComponentMapper<DirectionComponent> DIRECTION =
            ComponentMapper.getFor(DirectionComponent.class);

    public static final ComponentMapper<SpeedComponent> SPEED =
            ComponentMapper.getFor(SpeedComponent.class);

    public static final ComponentMapper<CoinComponent> COIN =
            ComponentMapper.getFor(CoinComponent.class);


    private GameMapper(){}
}
