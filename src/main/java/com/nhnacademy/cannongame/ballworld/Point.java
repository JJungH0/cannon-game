package com.nhnacademy.cannongame.ballworld;

import java.util.Objects;

public class Point {

    private double x; // x 좌표
    private double y; // y 좌표

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point point) {
        /**
         * TODO: 피타고라스 정리를 사용하여 거리 계산
         * 두 점(x1, y1), (x2,y2) 사이의 거리를
         * √[(x2 - x1)² + (y2 - y1)²] 로 나타냄
         */
        if (Objects.isNull(point)) {
            throw new IllegalArgumentException("null값을 입력하실수 없습니다.");
        }
        double dx = point.x - this.x; // x 좌표의 차이
        double dy = point.y - this.y; // y 좌표의 차이
        return Math.sqrt(dx * dx + dy * dy); // 두 점의 거리 (=x,y 좌표 차이의 제곱 합)의 제곱근
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
