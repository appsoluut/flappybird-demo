package com.appsoluut.flappy.states;

import com.appsoluut.flappy.Flappy;
import com.appsoluut.flappy.sprites.Bird;
import com.appsoluut.flappy.sprites.Ground;
import com.appsoluut.flappy.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 *
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background;

    private Array<Tube> tubes;
    private Array<Ground> grounds;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, Flappy.WIDTH / 2, Flappy.HEIGHT / 2);

        bird = new Bird(50, 300);
        background = new Texture("bg.png");

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.WIDTH)));
        }

        grounds = new Array<Ground>();
        for (int i = 0; i < 2; i++) {
            grounds.add(new Ground(i * Ground.WIDTH));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + ((Tube.WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) {
                gameStateManager.push(new GameOverState(gameStateManager));
                break;
            }
        }

        for (Ground ground : grounds) {
            if (camera.position.x - (camera.viewportWidth / 2) > ground.getPosition().x + ground.getTexture().getWidth()) {
                ground.reposition((camera.position.x - (camera.viewportWidth / 2)) + ground.getTexture().getWidth());
            }

            if (ground.collides(bird.getBounds())) {
                gameStateManager.set(new PlayState(gameStateManager));
                break;
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            batch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        for (Ground ground : grounds) {
            batch.draw(ground.getTexture(), ground.getPosition().x, ground.getPosition().y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        for (Ground ground : grounds) {
            ground.dispose();
        }

        System.out.println("Disposed play state");
    }
}
