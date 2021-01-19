package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class RenderComponent implements Component , Pool.Poolable {

    public TextureRegion region;

    @Override
    public void reset() {
        region = null;
    }
}
