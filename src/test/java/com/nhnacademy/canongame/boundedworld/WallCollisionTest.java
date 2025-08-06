package com.nhnacademy.canongame.boundedworld;

import com.nhnacademy.cannongame.boundedworld.CollisionDetector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.nhnacademy.cannongame.boundedworld.CollisionDetector.*;
import static org.junit.jupiter.api.Assertions.*;

class WallCollisionTest {

    @Test
    void WallCollisionCreation() {
        WallCollision collision = new WallCollision(Wall.LEFT, 5.0);
        assertEquals(Wall.LEFT, collision.getWall(), "벽 종류가 올바르지 않습니다.");
        assertEquals(5.0, collision.getPenetration(), "침투 깊이가 올바르지 않습니다.");
    }
}
