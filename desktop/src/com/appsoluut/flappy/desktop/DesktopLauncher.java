package com.appsoluut.flappy.desktop;

import com.appsoluut.flappy.Flappy;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Flappy.WIDTH;
        config.height = Flappy.HEIGHT;
        config.title = Flappy.TITLE;

        new LwjglApplication(new Flappy(), config);
    }
}
