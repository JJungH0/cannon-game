package com.nhnacademy.canongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.boundedworld.BallCollision;
import com.nhnacademy.cannongame.boundedworld.BoundedBall;
import com.nhnacademy.cannongame.boundedworld.CollisionDetector;
import com.nhnacademy.cannongame.movableworld.MovableBall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.nhnacademy.cannongame.boundedworld.CollisionDetector.*;
import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {


    @Test
    void BallCollisionDetection() {
        Ball ball1 = new Ball(100, 100, 30);
        Ball ball2 = new Ball(150, 100, 30);

        assertTrue(BallCollision.areColliding(ball1,ball2));

        ball2.setX(200);
        assertFalse(BallCollision.areColliding(ball1,ball2));
    }

    @Test
    void ElasticCollision() {
        MovableBall ball1 = new MovableBall(100, 200, 20);
        ball1.setDx(100);
        ball1.setDy(0);

        MovableBall ball2 = new MovableBall(140, 100, 20);
        ball2.setDx(-100);
        ball2.setDy(0);


        double totalMomentumBefore =
                ball1.getRadius() * ball1.getDx() +
                        ball2.getRadius() * ball2.getDx();

        BallCollision.resolveElasticCollision(ball1, ball2);

        double totalMomentumAfter =
                ball1.getRadius() * ball1.getDx() +
                        ball2.getRadius() * ball2.getDx();

        assertEquals(totalMomentumBefore, totalMomentumAfter, 0.001);
    }

    @Test
    void WallCollisionDetection() {
        BoundedBall ball = new BoundedBall(50, 300, 20);

        CollisionDetector.WallCollision collision = CollisionDetector.checkWallCollision(
                ball, 0, 0, 800, 600
        );

        assertEquals(Wall.NONE, collision.wall);

        ball.setX(15);
        collision = checkWallCollision(
                ball, 0, 0, 800, 600
        );

        assertEquals(Wall.LEFT, collision.wall);
        assertEquals(5, collision.penetration,0.001);
    }

}
