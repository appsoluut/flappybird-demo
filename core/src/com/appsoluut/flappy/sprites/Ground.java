package com.appsoluut.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class Ground {
    public static final int WIDTH = 336;

    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;

    public Ground(int x) {
        texture = new Texture("ground.png");
        position = new Vector2(x, -(texture.getHeight() / 2));
        bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void reposition(float x) {
        position.set(x, position.y);
        bounds.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
