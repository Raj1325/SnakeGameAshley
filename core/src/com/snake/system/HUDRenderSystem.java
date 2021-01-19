package com.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class HUDRenderSystem extends EntitySystem {

    private static final float PADDING = 20f;
    private final Viewport viewport;
    private final SpriteBatch batch;
    private final GlyphLayout layout = new GlyphLayout();
    private final BitmapFont font;


    public HUDRenderSystem(Viewport viewport, SpriteBatch batch, BitmapFont font) {
        this.viewport = viewport;
        this.batch = batch;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void draw(){
        drawHighScore();
        drawScore();
    }

    private void drawHighScore(){
        String highScoreString = "HIGH SCORE :" + GameManager.INSTANCE.getHighScore();
        layout.setText(font, highScoreString);
        font.draw(batch, layout, PADDING, GameConfig.HUD_HEIGHT - PADDING);
    }

    private void drawScore(){
        String scoreString = "SCORE :" + GameManager.INSTANCE.getScore();
        layout.setText(font, scoreString);
        font.draw(batch, layout,
                GameConfig.HUD_WIDTH - layout.width - PADDING,
                GameConfig.HUD_HEIGHT - PADDING);
    }
}
