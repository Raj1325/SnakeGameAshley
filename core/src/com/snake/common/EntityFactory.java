package com.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.snake.assets.AssetDescriptors;
import com.snake.assets.AssetPath;
import com.snake.assets.RegionName;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.InputComponent;
import com.snake.component.PlayerComponent;
import com.snake.component.PositionComponent;
import com.snake.component.RenderComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.SpeedComponent;
import com.snake.component.WorldWrapComponent;
import com.snake.component.ZOrderComponent;
import com.snake.config.GameConfig;
import com.snake.util.ZOrderComparator;

import java.lang.reflect.GenericArrayType;

import javax.swing.text.ZoneView;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class EntityFactory {

    //== attributes ==
    private final PooledEngine engine;
    private final AssetManager assetManager;
    private TextureAtlas textureAtlas;

    private static final int background_zorder = 0;
    private static final int coin_zorder = 1;
    private static final int body_zorder = 2;
    private static final int head_zorder = 3;

    //== constructors ==
    public EntityFactory(PooledEngine engine , AssetManager assetManager) {
        this.engine = engine;
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        textureAtlas = this.assetManager.get(AssetDescriptors.GAMEPLAY);
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

        InputComponent inputComponent = engine.createComponent(InputComponent.class);

        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        renderComponent.region = textureAtlas.findRegion(RegionName.HEAD);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.zorder = head_zorder;

        Entity head = engine.createEntity();
        head.add(positionComponent);
        head.add(dimensionComponent);
        head.add(boundsComponent);
        head.add(speedComponent);
        head.add(directionComponent);
        head.add(playerComponent);
        head.add(worldWrapComponent);
        head.add(inputComponent);
        head.add(renderComponent);
        head.add(zOrderComponent);

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

        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        renderComponent.region = textureAtlas.findRegion(RegionName.COIN);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.zorder = coin_zorder;

        Entity coin = engine.createEntity();
        coin.add(coinComponent);
        coin.add(positionComponent);
        coin.add(dimensionComponent);
        coin.add(boundsComponent);
        coin.add(renderComponent);
        coin.add(zOrderComponent);

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

        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        renderComponent.region = textureAtlas.findRegion(RegionName.BODY);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.zorder = body_zorder;

        Entity body = engine.createEntity();
        body.add(positionComponent);
        body.add(dimensionComponent);
        body.add(boundsComponent);
        body.add(renderComponent);
        body.add(zOrderComponent);

        engine.addEntity(body);

        return body;

    }

    public Entity addBackground(){
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.setPosition(0,0);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setWidth(GameConfig.WORLD_WIDTH);
        dimensionComponent.setHeight(GameConfig.WORLD_HEIGHT);

        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        renderComponent.region = textureAtlas.findRegion(RegionName.BACKGROUND);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.zorder = background_zorder;

        Entity background = engine.createEntity();

        background.add(positionComponent);
        background.add(dimensionComponent);
        background.add(renderComponent);
        background.add(zOrderComponent);

        return background;
    }
}
