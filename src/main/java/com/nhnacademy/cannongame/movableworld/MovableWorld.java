package com.nhnacademy.cannongame.movableworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.ballworld.BallRenderer;
import com.nhnacademy.cannongame.ballworld.PaintableBall;
import com.nhnacademy.cannongame.ballworld.World;
import javafx.scene.canvas.GraphicsContext;

public class MovableWorld extends World {
    private BallRenderer renderer = new BallRenderer();

    public MovableWorld(int width, int height) {
        super(width, height);
    }

    public void update(double deltaTime) {
        for (Ball ball : getBalls()) {
            if (ball instanceof MovableBall mBall) {
                mBall.move(deltaTime);
            }
        }
    }

    public void render(GraphicsContext gc) {
        drawBackground(gc);
        for (Ball ball : getBalls()) {
            if (ball instanceof PaintableBall pBall) {
                renderer.drawPaintableBall(gc, pBall);
            } else {
                renderer.drawBall(gc, ball);
            }
        }
    }
}
