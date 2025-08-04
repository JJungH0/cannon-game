package com.nhnacademy.canongame;

import com.nhnacademy.cannongame.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private Point point1;
    private Point point2;

    @BeforeEach
    void setUp() {
        point1 = new Point(0, 0);
        point2 = new Point(3, 4);
    }

    @Test
    @DisplayName("포인트 객체 생성")
    void constructorTest() {
        // point1 x, y 값 비교
        assertEquals(0, point1.getX());
        assertEquals(0, point1.getY());

        // point2 x, y 값 비교
        assertEquals(3, point2.getX());
        assertEquals(4, point2.getY());
    }

    @Test
    @DisplayName("피타고라스 정의 거리 계산 테스트")
    void distanceToTest() {
        assertEquals(5, point1.distanceTo(point2));
    }

    @Test
    @DisplayName("예외 상황 테스트 (null 입력)")
    void notnullTest() {
        // 예외 검증은 core.api.Assertions 사용
        assertThatThrownBy(() -> point1.distanceTo(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값을 입력하실수 없습니다.");
    }
}
