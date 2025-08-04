package com.nhnacademy.canongame;

import com.nhnacademy.cannongame.ballWorld.Ball;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    @Test
    void testBallCreation() {
        // 기본 생성자 테스트
        Ball ball = new Ball(100, 200, 30);
        assertEquals(100, ball.getX(), 0.001, "X 좌표가 올바르게 설정되지 않았습니다");
        assertEquals(200, ball.getY(), 0.001, "Y 좌표가 올바르게 설정되지 않았습니다");
        assertEquals(30, ball.getRadius(), 0.001, "반지름이 올바르게 설정되지 않았습니다");
    }

    @Test
    void testBallDefaultConstructor() {
        // 기본값 생성자 테스트
        Ball ball = new Ball();
        assertEquals(0, ball.getX(), 0.001, "기본 X 좌표는 0이어야 합니다");
        assertEquals(0, ball.getY(), 0.001, "기본 Y 좌표는 0이어야 합니다");
        assertEquals(10, ball.getRadius(), 0.001, "기본 반지름은 10이어야 합니다");
    }

    @Test
    void testBallPosition() {
        Ball ball = new Ball(50, 75, 20);

        // setter 테스트
        ball.setX(150);
        ball.setY(175);

        assertEquals(150, ball.getX(), 0.001, "setX가 올바르게 작동하지 않습니다");
        assertEquals(175, ball.getY(), 0.001, "setY가 올바르게 작동하지 않습니다");
    }

    @Test
    void testRadiusValidation() {
        // 유효하지 않은 반지름 테스트
        assertThrows(IllegalArgumentException.class, () -> {
            new Ball(0, 0, -5); // 음수 반지름은 예외 발생해야 함
        }, "반지름은 음수거나 0이 될 수 없습니다.");

        assertThrows(IllegalArgumentException.class, () -> {
            new Ball(0, 0, 0); // 0 반지름도 예외 발생해야 함
        }, "반지름은 음수거나 0이 될 수 없습니다.");
    }

    @Test
    void testSetRadius() {
        Ball ball = new Ball(0, 0, 10);
        ball.setRadius(25);
        assertEquals(25, ball.getRadius(), 0.001, "setRadius가 올바르게 작동하지 않습니다");

        // 유효하지 않은 반지름 설정 시 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> {
            ball.setRadius(-10);
        }, "반지름은 음수거나 0이 될 수 없습니다.");
    }

    @Test
    void testContains() {
        Ball ball = new Ball(100, 100, 50);

        // 공 내부의 점들
        assertTrue(ball.contains(100, 100), "중심점이 포함되지 않았습니다");
        assertTrue(ball.contains(120, 120), "공 내부 점이 포함되지 않았습니다");

        // 공 외부의 점들
        assertFalse(ball.contains(200, 200), "공 외부 점이 포함되었습니다");
        assertFalse(ball.contains(50, 50), "공 외부 점이 포함되었습니다");

        // 경계선상의 점 (반지름 거리)
        assertTrue(ball.contains(150, 100), "경계선상의 점이 포함되지 않았습니다");
    }

}
