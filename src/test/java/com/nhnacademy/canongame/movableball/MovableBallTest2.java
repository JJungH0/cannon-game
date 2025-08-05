package com.nhnacademy.canongame.movableball;

import com.nhnacademy.cannongame.movableworld.MovableBall;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovableBallTest2 {
    private MovableBall ball;

    @BeforeEach
    public void setUp() {
        ball = new MovableBall(100, 100, 20);
        ball.setDx(60); // 60 pixels/second
        ball.setDy(80); // 80 pixels/second
    }

    @Test
    public void testInitialVelocity() {
        MovableBall newBall = new MovableBall(0, 0, 10);
        assertEquals(0, newBall.getDx());
        assertEquals(0, newBall.getDy());
    }

    @Test
    public void testMove() {
        double originalX = ball.getX();
        double originalY = ball.getY();

        ball.move(0.5); // 0.5초 이동

        assertEquals(originalX + 30, ball.getX(), 0.001);
        assertEquals(originalY + 40, ball.getY(), 0.001);
    }

    @Test
    public void testSpeedCalculation() {
        assertEquals(100, ball.getSpeed(), 0.001); // sqrt(60^2 + 80^2) = 100
    }

    @Test
    public void testDirection() {
        MovableBall rightBall = new MovableBall(0, 0, 10);
        rightBall.setDx(10);
        rightBall.setDy(0);
        assertEquals(0, rightBall.getDirection(), 0.001); // 오른쪽 = 0 라디안

        MovableBall upBall = new MovableBall(0, 0, 10);
        upBall.setDx(0);
        upBall.setDy(-10);
        assertEquals(-Math.PI/2, upBall.getDirection(), 0.001); // 위쪽 = -π/2
    }
}