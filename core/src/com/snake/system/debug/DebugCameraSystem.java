package com.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.snake.util.debug.DebugCameraController;

/**
 * Created by RAJ DIGHE on 28/10/2020
 **/


public class DebugCameraSystem extends EntitySystem {

    //== attributes ==
    private final DebugCameraController cameraController;
    private final OrthographicCamera camera;

    //== constructors ==
    public DebugCameraSystem(float startX , float startY , OrthographicCamera camera){
        this.camera = camera;
        cameraController = new DebugCameraController();
        cameraController.setStartPosition(startX , startY);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        cameraController.handleDebugInput(deltaTime);
        cameraController.applyTo(camera);
    }
}
