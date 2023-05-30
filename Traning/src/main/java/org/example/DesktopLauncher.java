package org.example;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setIdleFPS(60);
        configuration.useVsync(true);
        configuration.setWindowedMode(960, 640);

        new Lwjgl3Application(new Boot(), configuration);
    }
}
