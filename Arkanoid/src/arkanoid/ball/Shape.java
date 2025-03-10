//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.ball;

import interfaces.Movable;
import interfaces.Paintable;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;


public abstract class Shape implements Paintable, Movable{
    public Point p;
    Color c;
    public double dx;
    public double dy;
    public int width;
    public int height;
    public Container parent;

    public Shape(Point p, Color c, double dx, double dy, int width, int height, Container parent) {
        this.p = p;
        this.c = c;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.parent = parent;
    }

    
    @Override
    public void move(){
        if(dy < 0){
            if(p.y+dy < 0)
                dy = -dy;
            }
        else{
            
            if(p.y+dy > parent.getHeight()-parent.getInsets().bottom-height)
            dy = -dy;
        }
        if(dx < 0){
            if(p.x+dx < 0)
                dx = -dx;
        }
        else{
            if(p.x+dx > parent.getWidth()-parent.getInsets().right-width)
                dx = -dx;
        }

        p.x += dx;
        p.y += dy;
    }

    @Override
    abstract public void paint(Graphics2D g);
    
    
    public boolean limiteBall(Shape shape){
        return p.y + height + dy - 100 > parent.getHeight() - parent.getInsets().bottom;
    }

    @Override
    public String toString(){
        return "Shape [p=" + p + ", c=" + c + ", dx=" + dx + ", dy=" + dy
        + ", width=" + width + ", height=" + height + ", parent="
        + parent + "]";
    }    
}