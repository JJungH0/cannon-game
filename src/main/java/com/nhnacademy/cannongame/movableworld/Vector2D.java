package com.nhnacademy.cannongame.movableworld;


public class Vector2D {

    private double x;
    private double y;

    // 테스트 클래스 용도
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 벡터 더하기
     * 호출한 백터 x,y + 다른 백터 x,y
     * @return 새로운 백터 객체 반환
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * 벡터 빼기
     * 호출한 백터 x,y - 다른 백터 x,y
     * @return 새로운 백터 객체 반환
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    /**
     * 벡터 스칼라 곱
     * -> 벡터의 길이나 크기를 늘이거나 줄일 때 사용
     */
    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * 벡터의 길이
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * 단위 벡터로 정규화
     * -> 벡터의 방향은 유지하되 길이가 1인 단위 벡터를 반환
     * -> 크기가 0이면 (0,0) 벡터를 반환
     */
    public Vector2D normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(x / mag, y / mag);
    }

    /**
     * 마찰력 구현
     */
    public Vector2D damp(double factor) {
        return new Vector2D(x * factor, y * factor);
    }

    /**
     * 테스트 코드 용도 -> 벡터 내적(dot) 구하기
     *
     * @return x1*x2 + y1*y2
     */
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
