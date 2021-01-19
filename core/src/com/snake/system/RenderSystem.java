package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.common.GameManager;
import com.snake.common.GameMapper;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;
import com.snake.component.RenderComponent;
import com.snake.component.ZOrderComponent;
import com.snake.util.ZOrderComparator;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class RenderSystem extends SortedIteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            RenderComponent.class,
            ZOrderComponent.class
    ).get();

    private final Viewport viewport;
    private final SpriteBatch batch;

    public RenderSystem(Viewport viewport , SpriteBatch batch){
        super(FAMILY , ZOrderComparator.INSTANCE);
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent renderComponent = GameMapper.RENDERER.get(entity);
        PositionComponent positionComponent = GameMapper.POSITION.get(entity);
        DimensionComponent dimensionComponent = GameMapper.DIMENSION.get(entity);

        batch.draw(
                renderComponent.region ,
                positionComponent.getX(), positionComponent.getY(),
                dimensionComponent.getWidth() , dimensionComponent.getHeight()
        );

    }
}
