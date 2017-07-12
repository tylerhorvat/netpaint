package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Picture extends PaintObject {

	public Picture(Point a, Point b, String image) {
		super(a, b, "file:NetPaintFX/images/" + image);
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(getImage(), getA().getX(), getA().getY(), getW(), getH());
	}
}
