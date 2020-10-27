package com.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.util.ViewportUtils;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class GridRenderSystem extends EntitySystem {

    public final Viewport viewport;
    public final ShapeRenderer renderer;

    public GridRenderSystem(Viewport viewport , ShapeRenderer renderer){
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        viewport.apply();
        ViewportUtils.drawGrid(viewport , renderer);
    }
}
