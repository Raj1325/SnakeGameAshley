package com.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake.SnakeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DesktopLauncher {
	public static void main (String[] arg) {
		List<Boolean> isOutlier = Arrays.asList(new Boolean[10]);
		Collections.fill(isOutlier, Boolean.FALSE);
		for (Boolean val:
			 isOutlier) {
			System.out.println(val);
		}
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new SnakeGame(), config);
	}
}
