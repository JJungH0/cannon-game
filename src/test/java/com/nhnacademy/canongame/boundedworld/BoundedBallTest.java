package com.nhnacademy.canongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.PaintableBall;
import com.nhnacademy.cannongame.boundedworld.BoundedBall;
import com.nhnacademy.cannongame.boundedworld.BoundedWorld;
import com.nhnacademy.cannongame.movableworld.MovableBall;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundedBallTest {

    private BoundedBall ball;
    private BoundedWorld world;

    @BeforeEach
    void setUp() {
        ball = new BoundedBall(100, 100, 20);
        ball.setColor(Color.RED);
        world = new BoundedWorld(800, 600);
        world.addBall(ball);
    }
    @Test
    void wallBounce() {
        BoundedBall ball = new BoundedBall(50, 300, 20);
        ball.setBounds(0, 0, 800, 600);
        ball.setDx(-100);

        assertTrue(ball.getDx() < 0);

        for (int i = 0; i < 10; i++) {
            ball.move(0.1);
        }

        assertTrue(ball.getDx() > 0);
    }

    @Test
    void BoundedBallCreation() {
        assertEquals(100, ball.getX(), 0.001, "X 좌표가 올바르게 설정되지 않았습니다.");
        assertEquals(100, ball.getY(), 0.001, "Y 좌표가 올바르게 설정되지 않았습니다.");
        assertEquals(20, ball.getRadius(), 0.001, "반지름이 올바르게 설정되지 않았습니다.");
        assertEquals(Color.RED, ball.getColor(), "색상이 올바르게 설정되지 않았습니다.");

        // 기본 반발 계수 확인
        assertEquals(0.8, ball.getRestitution());
    }

    @Test
    void RestitutionSetterGetterTest() {
        ball.setRestitution(0.9);
        assertEquals(0.9, ball.getRestitution(), 0.001, "반발 계수 설정이 올바르지 않습니다.");

        // 유효하지 않은 반발 계수
        assertThrows(IllegalArgumentException.class, () -> ball.setRestitution(-0.1));
        assertThrows(IllegalArgumentException.class, () -> ball.setRestitution(1.1));
    }

    @Test
    void InheritanceTest() {
        assertTrue(ball instanceof MovableBall);
        assertTrue(ball instanceof PaintableBall);
        assertTrue(ball instanceof BoundedBall);


        ball.setDx(100);
        ball.setDy(75);
        assertEquals(100, ball.getDx());
        assertEquals(75,ball.getDy());

        ball.move(0.1);
        assertEquals(110, ball.getX());
        assertEquals(107.5,ball.getY());
    }
}
