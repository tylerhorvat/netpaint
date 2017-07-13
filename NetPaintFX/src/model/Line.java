package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends PaintObject {

	public Line(Color color, Point a, Point b){
		super(color, a, b, 1);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(getColor());
		gc.strokeLine(getA().getX(), getA().getY(), getB().getX(), getB().getY());

	}
}
