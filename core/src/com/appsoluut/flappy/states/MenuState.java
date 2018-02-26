package com.appsoluut.flappy.states;

import com.appsoluut.flappy.Flappy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, Flappy.WIDTH, Flappy.HEIGHT);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);
        batch.draw(playButton, camera.position.x - playButton.getWidth() / 2, camera.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();

        System.out.println("Disposed menu state");
    }
}
