package com.nhnacademy.cannongame.boundedworld;

import com.nhnacademy.cannongame.ballworld.Ball;
import com.nhnacademy.cannongame.movableworld.MovableBall;

/**
 * 운동량 보존 법칙:
 * -> 충돌 전과 후에 전체 운동량은 같다는 법칙
 * -> 무거운 공이 빠르게 오면 가벼운 공이 더 멀리 튕겨나감
 * -> 반대로 두 공이 같은 무게면 속도를 나누어 갖고 서로 튕겨나감
 * 탄성 충돌:
 * -> 공이 충돌했을 때, 에너지 손실 없이 완전히 튕겨나가는 충돌
 * -> 당구공마냥 튕기는 느낌
 */
public class BallCollision {

    public static boolean areColliding(Ball ball1, Ball ball2) {
        /**
         * 두 공의 중심 거리 계산
         * 거리 < 두 반지름의 합이면 충돌
         * 1. 중심 좌표 차 구하기:
         * -> dx (= x좌표 간 거리 차)
         * -> dy (= y좌표 간 거리 차)
         * -> 두 공의 중심점을 연결하는 직선의 x,y 길이를 각각 구한 것.
         * 2. 중심 간 거리의 제곱 구하기:
         * -> disSquared(=피타고라스 정의)
         * 3. 반지릅 합 구하고 제곱:
         * -> 두 공의 반지름을 더한 값을 구하고(=두 공이 '겹치기 시작하는 경계선')
         * -> 중심 거리의 제곱과 반지름 합의 제곱을 비교
         */
        double dx = ball2.getX() - ball1.getX();
        double dy = ball2.getY() - ball1.getY();
        double disSquared = dx * dx + dy * dy;

        double rediusSum = ball1.getRadius() + ball2.getRadius();
        return disSquared <= (rediusSum * rediusSum * 0.95);

    }

    public static void resolveElasticCollision(MovableBall ball1, MovableBall ball2) {
        /**
         * 탄성 충돌 처리 (운동량 보존)
         * 충돌 발향 계산?
         * 상대 속도 계산?
         * 충격량 계산 및 속도 업데이트?
         * 겹침 해결
         */
        double dx = ball2.getX() - ball1.getX();
        double dy = ball2.getY() - ball1.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) {
            return;
        }

        double nx = dx / distance;
        double ny = dy / distance;

        double dvx = ball1.getDx() - ball2.getDx();
        double dvy = ball1.getDy() - ball2.getDy();

        double relativeVelocity = dvx * nx + dvy * ny;

        if (relativeVelocity >= 0) {
            return;
        }

        double mass1 = 1.0;
        double mass2 = 1.0;

        double impulse = (2 * relativeVelocity) / (mass1 + mass2);

        ball1.setDx(ball1.getDx() - impulse * mass2 * nx);
        ball1.setDy(ball1.getDy() - impulse * mass2 * ny);

        ball2.setDx(ball2.getDx() + impulse * mass1 * nx);
        ball2.setDy(ball2.getDy() + impulse * mass1 * ny);

        separateBalls(ball1, ball2);
    }

//    public static void resolveElasticCollision(MovableBall ball1, MovableBall ball2, double restitution) {
//        double dx = ball2.getX() - ball1.getX();
//        double dy = ball2.getY() - ball1.getY();
//        double distance = Math.sqrt(dx * dx + dy * dy);
//
//        if (distance == 0) return;
//
//        double nx = dx / distance;
//        double ny = dy / distance;
//
//        double dvx = ball1.getDx() - ball2.getDx();
//        double dvy = ball1.getDy() - ball2.getDy();
//
//        double relativeVelocity = dvx * nx + dvy * ny;
//
//        if (relativeVelocity >= 0) return;
//
//        double mass1 = 1.0;
//        double mass2 = 1.0;
//
//        // 반발 계수 적용
//        double impulse = (1 + restitution) * relativeVelocity / (mass1 + mass2);
//
//        ball1.setDx(ball1.getDx() - impulse * mass2 * nx);
//        ball1.setDy(ball1.getDy() - impulse * mass2 * ny);
//        ball2.setDx(ball2.getDx() + impulse * mass1 * nx);
//        ball2.setDy(ball2.getDy() + impulse * mass1 * ny);
//
//        separateBalls(ball1, ball2);
//    }


    public static void separateBalls(Ball ball1, Ball ball2) {
        /**
         * 겹친 공을 분리
         * 각 공을 겹침의 절발만큼 밀어냄
         */
        double dx = ball2.getX() - ball1.getX();
        double dy = ball2.getY() - ball1.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) return;

        double overlap = (ball1.getRadius() + ball2.getRadius()) - distance;

//        double offsetX = dx / distance * (overlap / 2);
//        double offsetY = dy / distance * (overlap / 2);
//
//        ball1.setX(ball1.getX() - offsetX);
//        ball1.setY(ball1.getY() - offsetY);
//        ball2.setX(ball2.getX() + offsetX);
//        ball2.setY(ball2.getY() + offsetY);

        double offsetX = dx / distance * overlap;
        double offsetY = dy / distance * overlap;

        ball1.setX(ball1.getX() - offsetX * 0.2);
        ball1.setY(ball1.getY() - offsetY * 0.2);
        ball2.setX(ball2.getX() + offsetX * 0.2);
        ball2.setY(ball2.getY() + offsetY * 0.2);

    }
}
