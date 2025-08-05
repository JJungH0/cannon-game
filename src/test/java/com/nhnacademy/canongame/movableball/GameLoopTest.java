//package com.nhnacademy.canongame.movableball;
//
//import com.nhnacademy.cannongame.movableworld.GameLoop;
//import com.nhnacademy.cannongame.movableworld.MovableWorld;
//import javafx.scene.canvas.GraphicsContext;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GameLoopTest {
//
//    private GameLoop gameLoop;
//    private MovableWorld world;
//    private GraphicsContext gc;
//
//    @BeforeEach
//    public void setUp() {
//        world = new MovableWorld(800, 600);
//        gc = Mockito.mock(GraphicsContext.class);
//        gameLoop = new GameLoop(world, gc);
//    }
//
//    @Test
//    public void testGameLoopCreation() {
//        assertNotNull(gameLoop, "GameLoop이 생성되지 않았습니다");
//        assertFalse(gameLoop.isRunning(), "초기 상태에서 게임 루프가 실행 중이면 안됩니다");
//
//    }
//
//    @Test
//    public void testStartStop() {
//        gameLoop.start();
//        assertTrue(gameLoop.isRunning(), "start() 호출 후 게임 루프가 실행되지 않습니다");
//
//        gameLoop.stop();
//        assertFalse(gameLoop.isRunning(), "stop() 호출 후 게임 루프가 정지되지 않습니다");
//    }
//
//    @Test
//    public void testPauseResume() {
//        gameLoop.start();
//        assertTrue(gameLoop.isRunning(), "게임 루프가 시작되지 않았습니다");
//
//        gameLoop.pause();
//        assertTrue(gameLoop.isPaused(), "pause() 호출 후 일시정지 상태가 아닙니다");
//
//        gameLoop.resume();
//        assertFalse(gameLoop.isPaused(), "resume() 호출 후 일시정지가 해제되지 않았습니다");
//        assertTrue(gameLoop.isRunning(), "resume() 후에도 게임 루프가 실행되지 않습니다");
//    }
//
//    @Test
//    public void testFPSMeasurement() {
//        gameLoop.start();
//
//        // 시뮬레이션을 위해 짧은 시간 대기
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        double fps = gameLoop.getCurrentFPS();
//        assertTrue(fps >= 0, "FPS는 0 이상이어야 합니다");
//    }
//}