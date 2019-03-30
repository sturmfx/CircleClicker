/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleclicker;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author lordstorm
 */
public class CircleC 
{
    static Random r = new Random();
    static Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.GOLD, Color.VIOLET};
    private double x;
    private double y;
    private Color color;
    private int life;
    private int radius;

    public CircleC(int start_radius, int end_radius, int start_life, int end_life, int max_x, int max_y) 
    {
        this.x = r.nextInt(max_x);
        this.y = r.nextInt(max_y);
        this.color = colors[r.nextInt(colors.length)];
        this.life = start_life + r.nextInt(end_life - start_life);
        this.radius = start_radius + r.nextInt(end_radius - start_radius);
        
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(getColor());
        gc.fillOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
    }
    
    
}
