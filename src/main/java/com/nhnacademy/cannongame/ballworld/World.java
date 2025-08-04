package com.nhnacademy.cannongame.ballworld;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {
    private int width, height;
    private List<Ball> balls = new ArrayList<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY); // 배경색
        gc.fillRect(0, 0, width, height);

    }
}
