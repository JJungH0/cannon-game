package com.nhnacademy.cannongame.movableworld;

import com.nhnacademy.cannongame.ballworld.World;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class MovableWorldApp extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private World world;
    private Canvas canvas;
    private Label label;

    @Override
    public void start(Stage stage) {
        // World, Canvas, Label 초기화
        world = new World(WIDTH, HEIGHT);
        canvas = new Canvas(WIDTH, HEIGHT);
        label = new Label("FPS: 0");
        label.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 16; -fx-padding: 4px;");
        label.setTranslateY(-(HEIGHT/2.0) + 20); // 상단에 위치

        createMovingBalls(10);

        StackPane stackPane = new StackPane(canvas, label);
        Scene scene = new Scene(stackPane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("MovableWorldApp");
        stage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameLoop gameLoop = new GameLoop(world, gc) {
            private int frameCount = 0;
            private long lastFpsTime = 0L;

            @Override
            public void handle(long now) {
                super.handle(now); // 기존 update, render 호출

                frameCount++;
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastFpsTime >= 1000) {
                    int fps = frameCount;
                    frameCount = 0;
                    lastFpsTime = currentTime;
                    Platform.runLater(() -> label.setText("FPS: " + fps));
                }
            }
        };
        gameLoop.start();
    }

    private void createMovingBalls(int count) {
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            double radius = 10 + rand.nextDouble() * 20; // 반지름: 10~30
            double x = radius + rand.nextDouble() * (WIDTH - 2 * radius);
            double y = radius + rand.nextDouble() * (HEIGHT - 2 * radius);
            double dx = -100 + rand.nextDouble() * 200; // -100 ~ 100
            double dy = -100 + rand.nextDouble() * 200;

            MovableBall ball = new MovableBall(x, y, radius, dx, dy);

            Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

            ball.setColor(color);

            world.addBall(ball);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}