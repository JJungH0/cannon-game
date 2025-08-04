package com.nhnacademy.canongame.movableball;

import com.nhnacademy.cannongame.movableworld.MovableBall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovableBallTest {

    @Test
    void MovableBallMovement() {
        MovableBall ball = new MovableBall(100, 100, 20);
        ball.setDx(50);
        ball.setDy(30);

        ball.move(1.0);

        assertEquals(150, ball.getX(), 0.001);
        assertEquals(130, ball.getY(), 0.001);
    }

    @Test
    void SpeedTest() {
        MovableBall ball = new MovableBall(0, 0, 10);
        ball.setDx(3);
        ball.setDy(4);

        assertEquals(5.0, ball.getSpeed(), 0.001);
    }
}
