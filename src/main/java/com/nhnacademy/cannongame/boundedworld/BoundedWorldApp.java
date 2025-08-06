package com.nhnacademy.cannongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;


public class BoundedWorldApp extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private BoundedWorld world = new BoundedWorld(WIDTH, HEIGHT);

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        world.setRestitution(0.8);

        createRandomBalls(10);

        AnimationTimer timer = new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                world.update(deltaTime);
                draw(gc);
            }
        };

        timer.start();
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("BoundedBall Simulation");
        stage.show();
    }

    private void createRandomBalls(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            double radius = 20 + rand.nextDouble() * 40; // 20~60
            double x = radius + rand.nextDouble() * (WIDTH - 2 * radius);
            double y = radius + rand.nextDouble() * (HEIGHT - 2 * radius);

            BoundedBall ball = new BoundedBall(x, y, radius);
            ball.setBounds(0, 0, WIDTH, HEIGHT);

            ball.setDx(-100 + rand.nextDouble() * 200);
            ball.setDy(-100 + rand.nextDouble() * 200);

            Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
            ball.setColor(color);

            world.addBall(ball);
        }
    }

    private void draw(GraphicsContext gc) {
        world.drawBackground(gc);

        for (Ball ball : world.getBalls()) {
            if (ball instanceof BoundedBall bBall) {
                gc.setFill(bBall.getColor());
                gc.fillOval(
                        bBall.getX() - bBall.getRadius(),
                        bBall.getY() - bBall.getRadius(),
                        bBall.getRadius() * 2,
                        bBall.getRadius() * 2
                );
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
