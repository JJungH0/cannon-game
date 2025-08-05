package com.nhnacademy.canongame.movableball;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.ballworld.World;
import com.nhnacademy.cannongame.movableworld.MovableBall;
import com.nhnacademy.cannongame.movableworld.MovableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class MovableWorldTest {

    private MovableWorld world;

    @BeforeEach
    public void setUp() {
        world = new MovableWorld(800, 600);
    }

    @Test
    public void testWorldCreation() {
        assertEquals(800, world.getWidth(), "World 너비가 올바르지 않습니다");
        assertEquals(600, world.getHeight(), "World 높이가 올바르지 않습니다");
    }

    @Test
    public void testAddMovableBall() {
        MovableBall ball = new MovableBall(100, 100, 20);
        world.addBall(ball);

        assertEquals(1, world.getBalls().size(), "MovableBall이 추가되지 않았습니다");
    }

    @Test
    public void testUpdate() {
        MovableBall ball = new MovableBall(100, 100, 20);
        ball.setDx(50.0);
        ball.setDy(30.0);
        world.addBall(ball);

        double deltaTime = 0.1; // 0.1초
        world.update(deltaTime);

        // 공이 이동했는지 확인
        assertEquals(105.0, ball.getX(), 0.001, "update 후 공이 X 방향으로 이동하지 않았습니다");
        assertEquals(103.0, ball.getY(), 0.001, "update 후 공이 Y 방향으로 이동하지 않았습니다");
    }

    @Test
    public void testUpdateMultipleBalls() {
        MovableBall ball1 = new MovableBall(100, 100, 20);
        MovableBall ball2 = new MovableBall(200, 200, 30);

        ball1.setDx(10.0);
        ball1.setDy(20.0);
        ball2.setDx(-15.0);
        ball2.setDy(25.0);

        world.addBall(ball1);
        world.addBall(ball2);

        world.update(1.0); // 1초

        assertEquals(110.0, ball1.getX(), 0.001, "첫 번째 공의 X 이동이 올바르지 않습니다");
        assertEquals(120.0, ball1.getY(), 0.001, "첫 번째 공의 Y 이동이 올바르지 않습니다");
        assertEquals(185.0, ball2.getX(), 0.001, "두 번째 공의 X 이동이 올바르지 않습니다");
        assertEquals(225.0, ball2.getY(), 0.001, "두 번째 공의 Y 이동이 올바르지 않습니다");
    }

    @Test
    public void testRender() {
        /**
         * Mocktito(기본) 에서는 final 클래스 mock 불가
         * GraphicsContext gc = Mockito.mock(GraphicsContext.class);
         * -> 따로 의존성을 추가하거나
         *      <dependency>
         *        <groupId>org.mockito</groupId>
         *        <artifactId>mockito-inline</artifactId>
         *        <version>4.6.1</version>
         *        <scope>test</scope>
         *      </dependency>
         * -> 직접 GraphicsContext 테스트용 구현체(스텁) 작성해야함
         */
        GraphicsContext gc = Mockito.mock(GraphicsContext.class);
        MovableBall ball = new MovableBall(100, 100, 20);
        ball.setColor(Color.GREEN);
        world.addBall(ball);

        assertDoesNotThrow(() -> {
            world.render(gc);
        }, "MovableWorld 렌더링 중 예외가 발생했습니다");
    }

    @Test
    public void testInheritance() {
        // MovableWorld가 World를 상속받는지 확인
        assertTrue(world instanceof World, "MovableWorld는 World를 상속받아야 합니다");

        // 부모 클래스의 메서드 사용 가능한지 확인
        Ball staticBall = new Ball(50, 50, 15);
        world.addBall(staticBall);
        assertEquals(1, world.getBalls().size(), "상속받은 addBall 메서드가 작동하지 않습니다");
    }
}