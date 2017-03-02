/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx8ibe;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* Drawing Lines
* Listing 2-1 DrawingLines.java
* @author carldea
*/
public class DrawingLines extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Chapter 2 Drawing Lines");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 350, Color.GRAY);
        
        // Red line
        Line redLine = new Line(10, 10, 200, 10);
        
        // setting common properties
        redLine.setStroke(Color.RED);
        redLine.setStrokeWidth(10);
        redLine.setStrokeLineCap(StrokeLineCap.BUTT);
        
        // creating a dashed pattern
        redLine.getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
        redLine.setStrokeDashOffset(0);
        root.getChildren().add(redLine);
        
        // White line
        Line whiteLine = new Line(10, 30, 200, 30);
        whiteLine.setStroke(Color.WHITE);
        whiteLine.setStrokeWidth(10);
        whiteLine.setStrokeLineCap(StrokeLineCap.ROUND);
        root.getChildren().add(whiteLine);
        
        // Blue line
        Line blueLine = new Line(10, 50, 200, 50);
        blueLine.setStroke(Color.BLUE);
        blueLine.setStrokeWidth(10);
        root.getChildren().add(blueLine);
        
        // slider min, max, and current value
        Slider slider = new Slider(0, 100, 0);
        slider.setLayoutX(10);
        slider.setLayoutY(95);
        
        // bind the stroke dash offset property
        redLine.strokeDashOffsetProperty()
        .bind(slider.valueProperty());
        root.getChildren()
        .add(slider);
        Text offsetText = new Text("Stroke Dash Offset: 0.0");
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.WHITE);
        
        // display stroke dash offset value
        slider.valueProperty()
        .addListener((ov, curVal, newVal) ->
        offsetText.setText("Stroke Dash Offset: " + slider.getValue()));
        root.getChildren().add(offsetText);
        
        
        //outer donut --> s.41 JavaFX Fundamentals
        Ellipse bigCircle = new Ellipse(
            100, // center x
            100, // center y
             50, // radius x
           75/2); // radius y
        bigCircle.setStrokeWidth(3);
        bigCircle.setStroke(Color.BLACK);
        bigCircle.setFill(Color.WHITE);
        
        // donut hole
        Ellipse smallCircle = new Ellipse(
            100, // center x
            100, // center y
            35/2, // radius x
            25/2); // radius y
        
        // make a donut
        Shape donut = Path.subtract(bigCircle, smallCircle);
        donut.setStrokeWidth(1.8);
        donut.setStroke(Color.BLACK);
        
        // orange glaze
        donut.setFill(Color.rgb(255, 200, 0));
        
        //bind the x coordinate to the value of the slider
        donut.layoutXProperty().bind(slider.valueProperty());
        
        // add drop shadow
        DropShadow dropShadow = new DropShadow(
            5, // radius
            2.0f, // offset X
            2.0f, // offset Y
            Color.rgb(50, 50, 50, .588));
        donut.setEffect(dropShadow);
        
        // move slightly down (measured from slider's y coordinate)
        donut.setTranslateY(slider.getBoundsInParent().getMinY() + 10);
        
        root.getChildren().add(donut);
        
        // ask a question
        
        Text question = new Text("Do you like donuts?");
        question.setUnderline(true); 
        
        //position the text so it doesn't display on top of the other elements
        question.setX(10);
        question.setY(donut.getBoundsInParent().getMinY() + 130);
        
        // bind text color to same color as the donut
        question.fillProperty().bind(donut.fillProperty());
        
        root.getChildren().add(question);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
