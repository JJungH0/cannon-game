package com.nhnacademy.cannongame.ballWorld;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

        // 1. 공 채우기 (공 색상 사용, null일 때는 빨간색)
        Color fillColor = ball.getColor() == null ? Color.RED : ball.getColor();
        gc.setFill(fillColor);
        gc.fillOval(leftX, topY, diameter, diameter);

        // 2. 검은색 테두리 그리기
        gc.setStroke(Color.BLACK);   // 테두리 색상: 검정
        gc.setLineWidth(2);          // 테두리 두께: 2픽셀
        gc.strokeOval(leftX, topY, diameter, diameter);

    }
}
