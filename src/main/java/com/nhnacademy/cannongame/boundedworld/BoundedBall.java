package com.nhnacademy.cannongame.boundedworld;

import com.nhnacademy.cannongame.movableworld.MovableBall;

public class BoundedBall extends MovableBall{

    /**
     * minX, minY -> 최소 경계 (공의 중심 기준)
     * maxX, maxY -> 최대 경계 (공의 중심 기준)
     * 기본 경계 범위 -> 800,600
     */
    private double minX, minY, maxX, maxY;
    private double restitution;

    public BoundedBall(double x, double y, double radius) {
        super(x, y, radius);
        setBounds(0, 0, 800, 600);
        restitution = 0.8;
    }

    /**
     * 경계 설정 시 공의 중심이 이동 가능한 범위
     * -> 실제 공의 중심이 닿을 수 있는 최소,최대값을 계산
     * -> 공이 벽(=경계)에 딱 붙게 하려면 공의 반지름(radius)만큼 안 으로 들어와야 벽에 닿음
     * -> 그래서 최소,최대에 radius를 더하고 뻄
     */

    public void setBounds(double minX, double minY, double maxX, double maxY) {
        this.minX = minX + getRadius();
        this.minY = minY + getRadius();
        this.maxX = maxX - getRadius();
        this.maxY = maxY - getRadius();
    }

    /**
     * -> 다음 위치 계산
     * -> 경계 충돌 검사
     * -> 충돌 시 속도 반전
     * -> 위치 보정 (경계 안쪽으로)
     */
    @Override
    public void move(double deltaTime) {
        double nextX = getX() + getDx() * deltaTime;
        double nextY = getY() + getDy() * deltaTime;

        if (nextX <= minX || nextX >= maxX) {
            setDx(-getDx());
            if (nextX <= minX) {
                nextX = minX + (minX - nextX);
            } else {
                nextX = maxX - (nextX - maxX);
            }
        }

        if (nextY <= minY || nextY >= maxY) {
            setDy(-getDy());
            if (nextY <= minY) {
                nextY = minY + (minY - nextY);
            } else {
                nextY = maxY - (nextY - maxY);
            }
        }

        setX(nextX);
        setY(nextY);
    }

    public void setRestitution(double restitution) {
        if (restitution > 1 || restitution < 0) {
            throw new IllegalArgumentException("반발 계수는 1보다 크거나 음수일 수 없습니다.");
        }
        this.restitution = restitution;
    }

    public double getRestitution() {
        return restitution;
    }

}
