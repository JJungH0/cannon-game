package com.nhnacademy.cannongame.ballWorld;

import java.util.Objects;

public class Ball {

    private double x; // x 좌표
    private double y; // y 좌표
    private double radius; // 반지름

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 음수거나 0이 될 수 없습니다.");
        }

        this.radius = radius;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // 생성자 체이닝 구조

    /**
     * 생성자 1 -> 모든 값 파라미터 기반으로 설정
     */
    public Ball(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 음수거나 0이 될 수 없습니다.");
        }
        this.radius = radius;
    }

    /**
     * 생성자 2 -> x,y 좌표만 파라미터로 설정
     * -> radius(반지름 = 10)으로 고정
     */
    public Ball(double x, double y) {
        // 내부적으로 생성자 1 호출
        this(x, y, 10);
    }

    /**
     * 생성자 3 -> 기본 생성자
     * -> x,y,radius 값 고정
     */
    public Ball() {
        // 내부적으로 생성자 1 호출
        this(0, 0, 10);
    }

    /**
     * 공의 면적을 계산하는 메서드
     * @return 공의 면적 계산 (π × r²)
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * 주어진 점 (px, py)가 이 공 안에 포함되는지 확인
     * 거리 제곱: (px - x)^2 + (py - y)^2
     * 반지름 제곱: radius^2
     */
    public boolean contains(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        return (dx * dx + dy * dy) <= (radius * radius);
    }

    public boolean isColliding(Ball other) {
        if (Objects.isNull(other)) {
            return false;
        }
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (this.radius + other.radius);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
