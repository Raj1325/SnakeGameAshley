package com.snake.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.InputComponent;
import com.snake.component.PositionComponent;
import com.snake.component.RenderComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.SpeedComponent;
import com.snake.component.ZOrderComponent;

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

    public static final ComponentMapper<InputComponent> INPUT_QUEUE =
            ComponentMapper.getFor(InputComponent.class);

    public static final ComponentMapper<RenderComponent> RENDERER =
            ComponentMapper.getFor(RenderComponent.class);

    public static final ComponentMapper<ZOrderComponent> ZORDER =
            ComponentMapper.getFor(ZOrderComponent.class);


    private GameMapper(){}
}
