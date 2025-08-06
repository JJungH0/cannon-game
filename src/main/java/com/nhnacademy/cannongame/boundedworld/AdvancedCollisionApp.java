package com.nhnacademy.cannongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.boundedworld.BoundedBall;
import com.nhnacademy.cannongame.boundedworld.BoundedWorld;
import com.nhnacademy.cannongame.movableworld.MovableBall;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class AdvancedCollisionApp extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final double GRAVITY = 500;
    private static final int BALL_COUNT = 10;

    private BoundedWorld world;
    private Canvas canvas;
    private CheckBox gravityCheckBox;
    private Slider restitutionSlider;

    @Override
    public void start(Stage stage) {
        // 월드 생성
        world = new BoundedWorld((int) WIDTH, (int) HEIGHT);
        world.setRestitution(0.8);

        // 캔버스 설정
        canvas = new Canvas(WIDTH, HEIGHT);

        // UI 구성
        gravityCheckBox = new CheckBox("Gravity");
        restitutionSlider = new Slider(0.0, 1.0, 0.8);
        restitutionSlider.setShowTickLabels(true);
        restitutionSlider.setShowTickMarks(true);

        Label restitutionLabel = new Label("Restitution:");
        HBox controls = new HBox(10, gravityCheckBox, restitutionLabel, restitutionSlider);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controls);

        Scene scene = new Scene(root);
        stage.setTitle("Advanced Collision App");
        stage.setScene(scene);
        stage.show();

        createBalls();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                if (gravityCheckBox.isSelected()) {
                    applyGravity(deltaTime);
                }

                world.setRestitution(restitutionSlider.getValue());
                world.update(deltaTime);
                render();
            }
        };
        timer.start();
    }

    private void createBalls() {
        Random random = new Random();
        for (int i = 0; i < BALL_COUNT; i++) {
            double radius = 10 + random.nextDouble() * 20;
            double x = radius + random.nextDouble() * (WIDTH - 2 * radius);
            double y = radius + random.nextDouble() * (HEIGHT - 2 * radius);

            BoundedBall ball = new BoundedBall(x, y, radius);
            ball.setDx(random.nextDouble() * 300 - 150);
            ball.setDy(random.nextDouble() * 300 - 150);
            Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            ball.setColor(color);

            world.addBall(ball);
        }
    }

    private void applyGravity(double deltaTime) {
        for (Ball ball : world.getBalls()) {
            if (ball instanceof MovableBall movable) {
                movable.setDy(movable.getDy() + GRAVITY * deltaTime);
            }
        }
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        for (Ball ball : world.getBalls()) {
            if (ball instanceof BoundedBall bBall) {
                gc.setFill(bBall.getColor());
                gc.fillOval(bBall.getX() - bBall.getRadius(),
                        bBall.getY() - bBall.getRadius(),
                        bBall.getRadius() * 2,
                        bBall.getRadius() * 2);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
