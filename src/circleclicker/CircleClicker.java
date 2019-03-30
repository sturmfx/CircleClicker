/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleclicker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author lordstorm
 */
public class CircleClicker extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        Game g = new Game();
        HBox main = new HBox();
        VBox buttons = new VBox();
        Canvas can = new Canvas(800, 800);
        Button start_stop = new Button("Start/stop");
        Button reset = new Button("Reset");
        Label result = new Label();
        
        start_stop.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                g.pause_start();
            }
        });
        
        reset.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                g.reset();
            }
        });
        
        can.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            public void handle(MouseEvent me) 
            {
                g.clicks++;
                double x = me.getX();
                double y = me.getY();
                for(CircleC c:g.circles)
                {
                    if(Math.sqrt((c.getX()-x)*(c.getX()-x) + (c.getY()-y)*(c.getY()-y)) < c.getRadius())
                    {
                        g.points++;
                        g.circles_to_remove.add(c);
                        g.tick_between_new_circle -= 10;
                    }
                    else
                    {
                        g.tick_between_new_circle -= 200;
                    }
                }
            }
        });
        
        
        buttons.getChildren().add(result);
        buttons.getChildren().add(start_stop);
        buttons.getChildren().add(reset);
        
        main.getChildren().add(can);
        main.getChildren().add(buttons);
        
        
        
        Scene scene = new Scene(main, 1200, 800);
        
        primaryStage.setTitle("Click on circles!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                GraphicsContext gc = can.getGraphicsContext2D();
                gc.clearRect(0, 0, 800, 800);
                for(CircleC c: g.circles)
                {
                    c.draw(gc);
                }
                result.setText(String.valueOf(g.points) + "/" + String.valueOf(g.clicks));
            }
        }.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
