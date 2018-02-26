package com.appsoluut.flappy.states;

import com.appsoluut.flappy.Flappy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 */
public class GameOverState extends State {
    private Texture gameOver;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, Flappy.WIDTH, Flappy.HEIGHT);
        gameOver = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.pop();
            gameStateManager.set(new MenuState(gameStateManager));
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
        batch.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
    }
}
