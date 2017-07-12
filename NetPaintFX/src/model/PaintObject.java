package model;

import java.awt.Point;

//import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * PaintObject
 * 
 * <p>A PaintObject is an abstract class defining a shape that
 * can be drawn with two points <p>
 * 
 * @author YOUR NAME
 *
 */
public abstract class PaintObject {
	
	private Point a;
	private Point b;
	private double w;
	private double h;
	private Color color;
	private Image image;

	//shape constructor
	protected PaintObject(Color color, Point a, Point b) {
		this.setColor(color);
		 double x = (a.getX() < b.getX()) ? a.getX() : b.getX();
         double y = (a.getY() < b.getY()) ? a.getY() : b.getY();
         w = (a.getX() > b.getX()) ? a.getX()-b.getX() : b.getX()-a.getX();
         h = (a.getY() > b.getY()) ? a.getY()-b.getY() : b.getY()-a.getY();
         Point point = new Point();
         point.setLocation(x, y);
		this.setA(point);
		this.setW(w);
		this.setH(h);
		setImage(null);
		
	}

	//image constructor
	protected PaintObject(Point a, Point b, String str) {
		double x = (a.getX() < b.getX()) ? a.getX() : b.getX();
        double y = (a.getY() < b.getY()) ? a.getY() : b.getY();
        w = (a.getX() > b.getX()) ? a.getX()-b.getX() : b.getX()-a.getX();
        h = (a.getY() > b.getY()) ? a.getY()-b.getY() : b.getY()-a.getY();
        Point point = new Point();
        point.setLocation(x, y);
		this.setA(point);
		this.setW(w);
		this.setH(h);
		
		this.image = new Image(str, false);
	}

	//line constructor
	public PaintObject(Color color2, Point a2, Point b, int i) {
		this.setColor(color2);
		this.setA(a2);
		this.setB(b);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	abstract public void draw(GraphicsContext gc);

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}
}