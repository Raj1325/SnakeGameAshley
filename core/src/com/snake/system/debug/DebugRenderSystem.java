package com.snake.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.common.GameMapper;
import com.snake.component.BoundsComponent;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class DebugRenderSystem extends IteratingSystem {

    //== attributes ==
    private static final Family FAMILY = Family.all(
            BoundsComponent.class
    ).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    //== constructors ==
    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor();
        renderer.setColor(Color.RED);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BoundsComponent boundsComponent = GameMapper.BOUNDS.get(entity);
        renderer.rect(
                boundsComponent.getBound().getX(),
                boundsComponent.getBound().getY(),
                boundsComponent.getBound().width,
                boundsComponent.getBound().height
        );
    }
}
