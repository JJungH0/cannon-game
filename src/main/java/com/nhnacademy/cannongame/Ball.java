package com.nhnacademy.cannongame;

/**
 * Ball 클래스
 * -> 2차원 공간에서 위치(x,y)와 반지름(radius)을 가지는 공을 나타냄
 */
public class Ball {

    private double x; // x 좌표
    private double y; // y 좌표
    private double radius; // 반지름


    /**
     * 1. 생성자를 통해 Ball 객체를 생성
     * 2. 초기 위치 및 반지름을 설정
     * @param x     x좌표
     * @param y     y좌표
     * @param radius 반지름
     */
    public Ball(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        if (radius < 0) {
            throw new IllegalArgumentException("반지름은 음수가 될 수 없습니다.");
        }
        this.radius = radius;
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
}
