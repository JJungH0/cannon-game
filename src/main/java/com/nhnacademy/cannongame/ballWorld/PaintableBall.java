package com.nhnacademy.cannongame.ballWorld;

import javafx.scene.paint.Color;

import java.util.Objects;

public class PaintableBall extends Ball{
    private Color color;

    public PaintableBall(double x, double y, double radius) {
        super(x, y, radius);
    }

    public PaintableBall(double x, double y) {
        super(x, y);
    }

    public PaintableBall() {
        super();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("색상은 null일 수 없습니다.");
        }
        this.color = color;
    }
}
