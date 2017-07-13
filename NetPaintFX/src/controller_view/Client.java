package controller_view;

import java.awt.Point;
import java.util.Vector;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DrawWhat;
import model.Line;
import model.Oval;
import model.PaintObject;
import model.Picture;
import model.Rectangle;

/**
  * A JPanel GUI for Netpaint that has all paint objects drawn on it.
  * This file also represents the controller as it controls how paint objects
  * are drawn and sends new paint objects to the server. All Client objects
  * also listen to the server to read the Vector of PaintObjects and
  * repaint every time any client adds a new one. 
  * 
  * @author YOUR NAME
  * 
 */
public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    DrawWhat toDraw = DrawWhat.NOTSET;
    boolean isDrawing = false;
    Color color;
    Vector<PaintObject> allPaintObjects = new Vector<>();
    Point a = null, b = null;
    PaintObject tempDraw;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane all = new BorderPane();
        
        Canvas canvas = new Canvas(800,620);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
   
        all.setCenter(canvas);
        
        GridPane radioButtons = new GridPane();
    
        RadioButton one = new RadioButton("Line");
        RadioButton two = new RadioButton("Rectangle");
        RadioButton three = new RadioButton("Oval");
        RadioButton four = new RadioButton("Picture");
    
        radioButtons.add(one, 1, 0);
        radioButtons.add(two, 2, 0);
        radioButtons.add(three, 3, 0);
        radioButtons.add(four, 4, 0);
    
        ToggleGroup radioGroup = new ToggleGroup();
        
    
        one.setToggleGroup(radioGroup);
        two.setToggleGroup(radioGroup);
        three.setToggleGroup(radioGroup);
        four.setToggleGroup(radioGroup);
        
        one.setOnAction(new ButtonHandler());
        two.setOnAction(new ButtonHandler());
        three.setOnAction(new ButtonHandler());
        four.setOnAction(new ButtonHandler());
    
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.MEDIUMSPRINGGREEN);
        color = colorPicker.getValue();
            
        colorPicker.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent arg0) {
				color = colorPicker.getValue();
			}
        	
        });
        
        radioButtons.add(colorPicker, 5, 0);
    
        radioButtons.setHgap(40);
        radioButtons.setHgap(50);
            
        radioButtons.setAlignment(Pos.TOP_CENTER);
    
        all.setBottom(radioButtons);
        
        addHandlersTo(canvas);
    
        Scene scene = new Scene(all, 800, 650);
    
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      
    private void drawAllPaintObects(Vector<PaintObject> allPaintObjects, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (PaintObject po : allPaintObjects)
            po.draw(gc);
    }
    
    private void addHandlersTo(Canvas canvas) {
    	canvas.setOnMouseClicked(event -> {
    		if(toDraw != DrawWhat.NOTSET) {
    			if(!isDrawing) {
    				double startX = event.getX();
    				double startY = event.getY();
    				a = new Point();
    				a.setLocation(startX, startY);
    			}
    			else {
    				double endX = event.getX();
    				double endY = event.getY();
    				b = new Point();
    				b.setLocation(endX, endY);
    				
    				if(toDraw == DrawWhat.LINE) {
    					allPaintObjects.add(new Line(color, a, b));
    				}
    				if(toDraw == DrawWhat.IMAGE) {
    					allPaintObjects.add(new Picture(a, b, "doge.jpeg"));
    				}
    				if(toDraw == DrawWhat.RECTANGLE) {
    					allPaintObjects.add(new Rectangle(color, a, b));
    				}
    				if(toDraw == DrawWhat.OVAL) {
    					allPaintObjects.add(new Oval(color, a, b));
    				}
    				drawAllPaintObects(allPaintObjects, canvas);
    			}
    			isDrawing = !isDrawing;
    		}	
    	});
    	
    	canvas.setOnMouseMoved(event -> {
    		
    		
    		GraphicsContext gc = canvas.getGraphicsContext2D();
    		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    		drawAllPaintObects(allPaintObjects, canvas);
    		
    		double endX = event.getX();
    		double endY = event.getY();
    		b = new Point();
    		b.setLocation(endX, endY);
    		
    		
    		if(isDrawing) {
    			if(toDraw == DrawWhat.LINE) {
    				tempDraw = new Line(color, a, b);
    			}
    			if(toDraw == DrawWhat.IMAGE) {
    				tempDraw = (new Picture(a, b, "doge.jpeg"));
    			}
    			if(toDraw == DrawWhat.RECTANGLE) {
    				tempDraw = (new Rectangle(color, a, b));
    			}
    			if(toDraw == DrawWhat.OVAL) {
    				tempDraw = (new Oval(color, a, b));
    			}
    			tempDraw.draw(gc);
    		}
    	});
    }
    
    private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			RadioButton button = (RadioButton) arg0.getSource();
			String str = button.getText();
			
			if(str.equals("Line"))
				toDraw = DrawWhat.LINE;
			if(str.equals("Oval"))
				toDraw = DrawWhat.OVAL;
			if(str.equals("Rectangle"))
				toDraw = DrawWhat.RECTANGLE;
			if(str.equals("Picture"))
				toDraw = DrawWhat.IMAGE;
		}
    	
    }
}