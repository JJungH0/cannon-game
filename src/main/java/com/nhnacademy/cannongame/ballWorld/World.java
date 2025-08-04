package com.nhnacademy.cannongame.ballWorld;

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
}
