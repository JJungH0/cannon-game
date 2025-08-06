package com.nhnacademy.cannongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.ballworld.World;
import com.nhnacademy.cannongame.movableworld.MovableBall;

import java.util.List;

/**
 * World를 상속받아 충돌 처리 기능을 통합한 클래스
 * 추가 필드:
 * -> restitution (= 반발 계수)
 */
public class BoundedWorld extends World {

    private double restitution = 0.0;

    public BoundedWorld(int width, int height) {
        super(width, height);
    }

    public void update(double deltaTime) {
        List<Ball> balls = getBalls();

        for (Ball ball : balls) {
            if (ball instanceof MovableBall mBall) {
                mBall.move(deltaTime);
            }
        }

        for (Ball ball : balls) {
            if (ball instanceof BoundedBall bBall) {
                CollisionDetector.WallCollision collision = CollisionDetector.checkWallCollision(bBall, 0, 0, 800, 600);

                if (collision.wall != CollisionDetector.Wall.NONE) {
                    CollisionDetector.resolveWallCollision(bBall,collision,bBall.getRestitution());
                }
            }
        }

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball1 = balls.get(i);
                Ball ball2 = balls.get(j);

                if (BallCollision.areColliding(ball1, ball2)) {
                    if (ball1 instanceof MovableBall mBall1 &&
                            ball2 instanceof MovableBall mBall2) {
                        BallCollision.resolveElasticCollision(mBall1,mBall2);
                    }
                }
            }
        }
    }

    public void setRestitution(double restitution) {
        this.restitution = restitution;
    }

    public double getRestitution() {
        return restitution;
    }
}
