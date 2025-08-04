package com.nhnacademy.canongame.ballworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.ballworld.PaintableBall;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaintableBallTest {

    @Test
    void testPaintableBallCreation() {
        PaintableBall ball = new PaintableBall(100, 100, 20);

        // 상속받은 Ball의 속성들 확인
        assertEquals(100, ball.getX(), 0.001, "X 좌표가 올바르게 설정되지 않았습니다");
        assertEquals(100, ball.getY(), 0.001, "Y 좌표가 올바르게 설정되지 않았습니다");
        assertEquals(20, ball.getRadius(), 0.001, "반지름이 올바르게 설정되지 않았습니다");

        // PaintableBall의 고유 속성 확인
        assertEquals(Color.RED, ball.getColor(), "색상이 올바르게 설정되지 않았습니다");
    }

    @Test
    void testPaintableBallInheritance() {
        PaintableBall paintableBall = new PaintableBall(100, 100, 20);

        // Ball의 메서드들이 작동하는지 확인 (상속 확인)
        assertTrue(paintableBall instanceof Ball, "PaintableBall은 Ball을 상속받아야 합니다");

        // Ball의 메서드 사용 가능한지 확인
        paintableBall.setX(200);
        paintableBall.setY(300);
        assertEquals(200, paintableBall.getX(), 0.001, "상속받은 setX가 작동하지 않습니다");
        assertEquals(300, paintableBall.getY(), 0.001, "상속받은 setY가 작동하지 않습니다");

        // contains 메서드도 사용 가능한지 확인
        assertTrue(paintableBall.contains(200, 300), "상속받은 contains가 작동하지 않습니다");
    }

    @Test
    void testColorHandling() {
        PaintableBall ball = new PaintableBall(0, 0, 10);

        // 색상 변경
        ball.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, ball.getColor(), "색상 변경이 올바르게 작동하지 않습니다");

        // null 색상 처리 (구현에 따라 기본 색상으로 설정하거나 예외 발생)
        assertThrows(IllegalArgumentException.class,
                () -> ball.setColor(null));

        assertNotNull(ball.getColor(), "null 색상 설정 후에도 색상이 있어야 합니다");
    }

    @Test
    void testDefaultColor() {
        // 색상 없이 생성하는 생성자가 있다면
        PaintableBall ball = new PaintableBall(0, 0, 10);
        assertNotNull(ball.getColor(), "기본 색상이 설정되어야 합니다");
    }
}