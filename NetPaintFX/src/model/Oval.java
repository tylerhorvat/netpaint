package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends PaintObject {

	public Oval(Color color, Point a, Point b) {
		super(color, a, b);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(getColor());
		gc.fillOval(getA().getX(), getA().getY(), getW(), getH());		
	}
}
