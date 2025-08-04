package com.nhnacademy.canongame;

import com.nhnacademy.cannongame.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    private Ball ball;

    // 각 테스트가 실행되기 전에 수행
    @BeforeEach
    void setUp() {
        ball = new Ball(100, 200, 20);
    }

    @Test
    @DisplayName("공 객체 생성")
    void constructorTest() {
        assertEquals(100, ball.getX());
        assertEquals(200, ball.getY());
        assertEquals(20, ball.getRadius());
    }

    @Test
    @DisplayName("공 객체 생성 -> 반지름이 음수일때 (예외 발생)")
    void InvalidRadius() {
        // 예외 검증은 core.api.Assertions 사용
        assertThatThrownBy(() -> new Ball(0, 0, -5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("반지름은 음수가 될 수 없습니다.");


    }
}
