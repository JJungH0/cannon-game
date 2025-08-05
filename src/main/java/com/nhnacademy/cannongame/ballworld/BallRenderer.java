package com.nhnacademy.cannongame.ballworld;

import com.nhnacademy.cannongame.movableworld.MovableBallByVector;
import com.nhnacademy.cannongame.movableworld.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * BallRenderer 클래스
 * - Canvas(GraphicsContext)에 Ball 및 PaintableBall 객체를 그리는 설계도
 * - 메서드:
 *  1. drawBall(GraphicsContext gc, Ball ball) -> 검은색 공 그리기
 *  2. drawPaintableBall(GraphicsContext gc, PaintableBall ball) -> 색상이 있는 공 그리기
 * - 요구사항:
 *  1. 공의 중심좌표(x,y), 반지름 정보를 이용하여 그리기
 *  2. fillOval 사용 시 왼쪽 상단 좌표와 너비,높이를 계산하여 그리기
 *  3. PaintableBall은 검은색 테두리 추가
 */
public class BallRenderer {

    public void drawBall(GraphicsContext gc, Ball ball) {
        double leftX = ball.getX() - ball.getRadius(); // 중심좌표 -> 왼쪽 상단으로 변환
        double topY = ball.getY() - ball.getRadius();
        double diameter = ball.getRadius() * 2;

        gc.setFill(Color.BLACK);
        gc.fillOval(leftX, topY, diameter, diameter);
    }

    public void drawPaintableBall(GraphicsContext gc, PaintableBall ball) {
        double leftX = ball.getX() - ball.getRadius();
        double topY = ball.getY() - ball.getRadius();
        double diameter = ball.getRadius() * 2;

        Color fillColor = ball.getColor() == null ? Color.RED : ball.getColor();
        gc.setFill(fillColor);
        gc.fillOval(leftX, topY, diameter, diameter);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeOval(leftX, topY, diameter, diameter);
    }

    public void drawTrail(GraphicsContext gc, MovableBallByVector ball) {
        List<Vector2D> history = ball.getPositionHistory();

        if (history.size() < 2) {
            return;
        }

        gc.setLineWidth(2);
        gc.setGlobalAlpha(0.5);

        double speed = ball.getSpeed();
        gc.setStroke(getSpeedColor(speed));

        for (int i = 0; i < history.size() - 1; i++) {
            Vector2D p1 = history.get(i);
            Vector2D p2 = history.get(i + 1);
            gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }

        gc.setGlobalAlpha(1.0);
    }

    private Color getSpeedColor(double speed) {
        double ratio = Math.min(speed / 300.0, 1.0);
        return new Color(ratio, 0.0, 1.0 - ratio, 1.0);
    }
}
