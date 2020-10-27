package com.snake.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.assets.AssetDescriptors;
import com.snake.assets.RegionName;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;
import com.snake.entity.BodyPart;
import com.snake.entity.Coin;
import com.snake.entity.Snake;
import com.snake.entity.SnakeHead;
import com.snake.util.GdxUtils;
import com.snake.util.ViewportUtils;
import com.snake.util.debug.DebugCameraController;

/*
    Created by rppdi on 25/10/2020
*/
@Deprecated
public class GameRendererOld implements Disposable {

    private final Logger log = new Logger(GameRendererOld.class.getName(), Logger.DEBUG);
    private final float PADDING = 20.0f;

    //== attributes ==
    private final GameControllerOld controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private Viewport hudViewport;
    private final BitmapFont font;
    private GlyphLayout layout;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    private TextureRegion backGroundRegion;
    private TextureRegion bodyRegion;
    private TextureRegion headRegion;
    private TextureRegion coinRegion;

    private DebugCameraController cameraController;

    //== constructors ==
    public GameRendererOld(SpriteBatch batch, AssetManager assetManager, GameControllerOld controller) {
        this.controller = controller;
        this.assetManager = assetManager;
        this.font = assetManager.get(AssetDescriptors.UI_FONT);
        this.batch = batch;
        layout = new GlyphLayout();
        init();
    }

    //== init ==
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);

        cameraController = new DebugCameraController();
        cameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        backGroundRegion = gameplayAtlas.findRegion(RegionName.BACKGROUND);
        headRegion = gameplayAtlas.findRegion(RegionName.HEAD);
        bodyRegion = gameplayAtlas.findRegion(RegionName.BODY);
        coinRegion = gameplayAtlas.findRegion(RegionName.COIN);
    }


    //== public methods ==
    public void render(float delta) {
        cameraController.handleDebugInput(delta);
        cameraController.applyTo(camera);

        GdxUtils.clearScreen();

        //renderDebug();
        renderGamePlay();
        renderHUD();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
    }

    @Override
    public void dispose() {

    }


    //== private methods ==
    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer);
        viewport.apply();
        Color oldColor = new Color(renderer.getColor());
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        renderer.end();
        renderer.setColor(oldColor);
    }

    private void renderGamePlay(){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        drawGamePlay();
        batch.end();
    }

    private void drawGamePlay() {

        //background
        batch.draw(backGroundRegion , 0,0,GameConfig.WORLD_WIDTH , GameConfig.WORLD_HEIGHT);

        //coin
        Coin coin = controller.getCoin();
        if(coin.isAvailable()){
            batch.draw(coinRegion , coin.getX() , coin.getY() , coin.getWidth() , coin.getHeight());
        }

        //snake
        Snake snake = controller.getSnake();
        SnakeHead head = snake.getHead();
        batch.draw(headRegion , head.getX() , head.getY() , head.getWidth() , head.getHeight());
        for(BodyPart bodyPart : snake.getBody()){
            batch.draw(bodyRegion , bodyPart.getX() , bodyPart.getY() , bodyPart.getWidth() , bodyPart.getHeight());
        }
    }

    private void drawDebug() {

        renderer.setColor(Color.YELLOW);
        Array<BodyPart> body = controller.getSnake().getBody();
        for (BodyPart bodyPart : body) {
            renderer.rect(bodyPart.getX(), bodyPart.getY(), bodyPart.getWidth(), bodyPart.getHeight());
        }

        renderer.setColor(Color.GREEN);

        SnakeHead head = controller.getSnake().getHead();
        Rectangle headBounds = head.getBounds();
        renderer.rect(headBounds.x, headBounds.y, headBounds.width, headBounds.height);

        renderer.setColor(Color.BLUE);
        Coin coin = controller.getCoin();
        if (coin.isAvailable()) {
            Rectangle coinBounds = coin.getBounds();
            renderer.rect(coinBounds.getX(), coinBounds.getY(), coinBounds.getWidth(), coinBounds.getHeight());
        }
    }

    private void renderHUD() {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();
        drawHUD();
        batch.end();
    }

    private void drawHUD() {

        String highScoreString = "HIGH SCORE :" + GameManager.INSTANCE.getDisplayHighScore();
        String scoreString = "SCORE :" + GameManager.INSTANCE.getDisplayScore();

        layout.setText(font, highScoreString);
        font.draw(batch, layout, PADDING, hudViewport.getWorldHeight() - PADDING);

        layout.setText(font, scoreString);
        font.draw(batch, layout,
                hudViewport.getWorldWidth() - layout.width - PADDING,
                hudViewport.getWorldHeight() - PADDING);

    }
}
