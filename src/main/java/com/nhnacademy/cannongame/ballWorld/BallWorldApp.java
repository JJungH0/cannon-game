package com.nhnacademy.cannongame.ballWorld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class BallWorldApp extends Application {
    private World world;
    private Canvas canvas;
    private GraphicsContext gc;
    private final BallRenderer ballRenderer = new BallRenderer();

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        // World & UI 초기화
        world = new World(WIDTH, HEIGHT);
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // 랜덤 공 5개 추가
        createRandomBalls(5);

        // 마우스 클릭 이벤트
        canvas.setOnMouseClicked(this::handleMouseClick);

        // Scene/Stage 구성
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Ball World App");
        stage.setScene(scene);
        stage.show();

        // 최초 화면 그리기
        draw();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void createRandomBalls(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            double radius = 20 + rand.nextDouble() * 40; // 20~60
            double x = radius + rand.nextDouble() * (WIDTH - 2 * radius);
            double y = radius + rand.nextDouble() * (HEIGHT - 2 * radius);

            Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
            PaintableBall ball = new PaintableBall(x, y, radius);
            ball.setColor(color);
            world.addBall(ball);
        }
    }

    private void handleMouseClick(MouseEvent event) {
        Random rand = new Random();
        double radius = 20 + rand.nextDouble() * 40;
        double x = event.getX();
        double y = event.getY();

        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
        PaintableBall ball = new PaintableBall(x, y, radius);
        ball.setColor(color);
        world.addBall(ball);
        draw();
    }

    private void draw() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        for (Ball b : world.getBalls()) {
            if (b instanceof PaintableBall) {
                ballRenderer.drawPaintableBall(gc, (PaintableBall) b);
            } else {
                ballRenderer.drawBall(gc, b);
            }
        }
    }

}
