package com.nhnacademy.cannongame.movableworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.ballworld.BallRenderer;
import com.nhnacademy.cannongame.ballworld.PaintableBall;
import com.nhnacademy.cannongame.ballworld.World;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * JavaFX의 AnimationTimer를 상속한 클래스
 * 내부적으로 JavaFX가 1초에 약 60프레임 속도로 handle()을 호출
 */
public class GameLoop extends AnimationTimer {

    /**
     * 필드:
     * lastUpdate: 이전 프레임의 시간 (나노초)
     * world: World 객체 참조
     * gc: GraphicsContext 참조
     */

    private long lastUpdate; // 이전 프레임 시간 저장 (nano초)
    private World world; // 게임의 상태/배경/공 등등 정보
    private GraphicsContext gc; // 그래픽을 그릴 캔버스의 펜 도구

    public GameLoop(World world, GraphicsContext gc) {
        this.world = world;
        this.gc = gc;
    }

    /**
     * JavaFX 내부에서 프레임마다 호출하는 메서드
     * @param now 현재 시간(나노초)
     */
    @Override
    public void handle(long now) {
        // 첫프레임은 당연히 0이므로 해당 조건문이 생략.
        if (lastUpdate > 0) {
            // 프레임 간 시간 간격을 초 단위로 계산
            double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
            // 게임 논리 업데이트 (= 공 위치 이동)
            update(deltaTime);
            // 게임 화면을 다시 그림
            render();
        }
        // 다음 프레임을 위해 lastUpdate를 현재시간으로 지정
        lastUpdate = now;
    }

    /**
     * update(deltaTime) 메서드 (= 공들의 위치 업데이트)
     * deltaTime 만큼 각 공을 움직임
     * deltaTime 이 중요한 이유는 PC 속도가 다를 때도 같은 속도로 움직이게 만들기 위함.
     * -> 이렇게 되면 느린 컴퓨터에서도 느리게 움직이지 않게 하기 위해 시간에 비례해서 이동
     */
    private void update(double deltaTime) {
        for (Ball ball : world.getBalls()) {
            // 현재 getBalls()는 반환타입이 Ball이라서
            // 타입캐스팅을 통해 업데이트
            if (ball instanceof MovableBall) {
                ((MovableBall) ball).move(deltaTime);
            }
        }
    }

    private void render() {
        gc.clearRect(0,0,800,600);

        world.drawBackground(gc);

        BallRenderer renderer = new BallRenderer();

        for (Ball ball : world.getBalls()) {
            if (ball instanceof PaintableBall) {
                renderer.drawPaintableBall(gc, (PaintableBall) ball); // 색상 있는 공
            } else {
                renderer.drawBall(gc, ball); // 기본 검정색 공
            }
        }
    }
}
