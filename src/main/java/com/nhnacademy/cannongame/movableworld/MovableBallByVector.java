package com.nhnacademy.cannongame.movableworld;

import com.nhnacademy.cannongame.ballworld.PaintableBall;

import java.util.LinkedList;
import java.util.List;

public class MovableBallByVector extends PaintableBall {
    private Vector2D velocity = new Vector2D(0, 0);
    private Vector2D acceleration = new Vector2D(0, 0);

    // 최근 50개 위치 저장
    private final List<Vector2D> positionHistory = new LinkedList<>();

    public MovableBallByVector(double x, double y, double radius) {
        super(x, y, radius);
        // 초기 위치 저장
        positionHistory.add(new Vector2D(x, y));
    }

    /**
     * 가속 + 힘
     */
    public void applyForce(Vector2D force) {
        this.acceleration = this.acceleration.add(force);
    }


    public void update(double deltaTime) {
        // 속도 + 가속도
        velocity = velocity.add(acceleration.multiply(deltaTime));
        // Ball메서드 -> 위치에 속도 반영
        setX(getX() + velocity.getX() * deltaTime);
        setY(getY() + velocity.getY() * deltaTime);

        velocity = velocity.damp(0.99);

        // 가속도 초기화 (= 다음 프레임 대비)
        acceleration = new Vector2D(0, 0);

        /**
         * 위치 히스토리 추가
         * 여기서 자료구조 공부
         * -> ArrayList로 동작 시:
         * -> 맨 앞 원소를 삭제하면 뒤의 모든 원소가 앞으로 한 칸씩 이동해야 해서
         * -> 성능상 비효율적 시간 복잡도 = O(n)
         * -> LinkedList로 동작 시:
         * -> 노드의 연결만 바꾸면 되기 떄문에
         * -> 앞에서 삭제할때 매우 유리
         * -> 성능상 효율적 시간 복잡도 = O(1)
         */

        positionHistory.add(new Vector2D(getX(), getY()));
        if (positionHistory.size() > 50) {
            positionHistory.removeFirst(); // 최근 50개만 유지
        }
    }

    public List<Vector2D> getPositionHistory() {
        return positionHistory;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getSpeed() {
        return velocity.magnitude();
    }

}
