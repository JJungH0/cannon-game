package com.nhnacademy.cannongame.boundedworld;

/**
 * CollisionDetector 클래스
 * - 벽과의 충돌 감지란:
 * -> 공이 화면의 가장자리 (minX, maxX ...)를 넘어서면 충돌로 간주
 * -> 단순히 넘었다고 끝이 아니라, 얼마나 벽을 뚫고 들어갔는지(penetration)도 함께 계산
 * - 반발 계수 (penetration):
 * -> 벽에 닿았을 때 공이 얼마나 튕겨나가는지 결정
 * -> 0.0 = 거의 안 튕김 (= 완전 비탄성 충돌)
 * -> 1.0 = 에너지 손실 없이 완벽히 튕김
 * -> 0.8 = 일반적인(?) 반발력
 */
public class CollisionDetector {

    /**
     * 벽 정보 (왼쪽, 오른쪽, 하단 ....)
     */
    public enum Wall {
        LEFT, RIGHT, TOP, BOTTOM, NONE
    }

    /**
     * 내부 클래스:
     * -> 충돌 결과 정보
     */
    public static class WallCollision {
        public Wall wall;

        public double getPenetration() {
            return penetration;
        }

        public Wall getWall() {
            return wall;
        }

        public double penetration;

        public WallCollision(Wall wall, double penetration) {
            this.wall = wall;
            this.penetration = penetration;
        }
    }

    /**
     * 각 벽과의 충돌검사
     * 충돌한 벽과 침투 깊이 반환 -> (= double penetration = OO - (....);
     * 충돌이 없으면 NONE 반환 -> return new WallCollision(Wall.NONE, 0.0);
     */
    public static WallCollision checkWallCollision(BoundedBall ball, double minX, double minY, double maxX, double maxY) {
        double x = ball.getX();
        double y = ball.getY();
        double radius = ball.getRadius();

        // 왼쪽 벽 충돌 시
        if (x - radius < minX) {
            double penetration = minX - (x - radius);
            return new WallCollision(Wall.LEFT, penetration);
        }

        // 오른쪽 벽 충돌 시
        if (x + radius > maxX) {
            double penetration = (x + radius) - maxX;
            return new WallCollision(Wall.RIGHT, penetration);
        }

        // 위쪽 벽 충돌 시
        if (y - radius < minY) {
            double penetration = minY - (y - radius);
            return new WallCollision(Wall.TOP, penetration);
        }

        // 아래쪽 벽 충돌 시
        if (y + radius > maxY) {
            double penetration = (y + radius) - maxY;
            return new WallCollision(Wall.BOTTOM, penetration);
        }

        // 충돌 없음
        return new WallCollision(Wall.NONE, 0.0);
    }

    /**
     * 충돌한 벽에 따라 속도 반전:
     * -> 반발 계수 적용
     * -> LEFT/RIGHT (= x 속도 반전)
     * -> TOP/BOTTOM (= y 속도 반전)
     */

    public static void resolveWallCollision(BoundedBall ball, WallCollision collision, double restitution) {
        if (collision.wall == Wall.NONE) return;

        switch (collision.wall) {
            case LEFT -> {
                ball.setDx(-ball.getDx() * restitution);
                ball.setX(ball.getX() + collision.penetration);
            }
            case RIGHT -> {
                ball.setDx(-ball.getDx() * restitution);
                ball.setX(ball.getX() - collision.penetration);
            }
            case TOP -> {
                ball.setDy(-ball.getDy() * restitution);
                ball.setY(ball.getY() + collision.penetration);
            }
            case BOTTOM -> {
                ball.setDy(-ball.getDy() * restitution);
                ball.setY(ball.getY() - collision.penetration);
            }
        }
    }
}
