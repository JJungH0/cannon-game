package com.nhnacademy.cannongame.movableworld;

import com.nhnacademy.cannongame.ballworld.PaintableBall;

public class MovableBall extends PaintableBall {
    private double dx; // x 방향 속도
    private double dy; // y 방향 속도


    /**
     * 3개 매개변수 -> 속도는 0으로 초기화
     */
    public MovableBall(double x, double y, double radius) {
        super(x, y, radius);
        this.dx = 0.0;
        this.dy = 0.0;
    }

    /**
     * 6개 매개변수 -> 모든 속성 지정
     * 예제에서는 6개지만 Color 필드를 Setter로 구현해서 5개로 지정
     */
    public MovableBall(double x, double y, double radius, double dx, double dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * 속도 크기 = √(dx² + dy²)
     * -> 수학이 제일 어렵다.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 속도의 방향 계산 (라디안)
     * 살면서 "라디안"이라는 단어를 첨 들어봤다.
     */
    public double getDirection() {
        return Math.atan2(dy, dx);
    }

    /**
     * 프레임 단위 이동
     * 기존 위치에서 속도의 값을 더해서 X,Y값을 재설정 하면 된다.
     */
    public void move() {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * 시간 기반 이동
     * 메서드 오버로딩 방식.
     * 새 위치 = 현재 위치 + 속도 × 시간
     */
    public void move(double deltaTime) {
        setX(getX() + dx * deltaTime);
        setY(getY() + dy * deltaTime);
    }

    /**
     * Getter && Setter
     */
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }


}
