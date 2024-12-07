//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.ball;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;



public class Ball extends Shape{    

    public Ball(Point p, Color c, double dx, double dy, int diametro, Container parent) {
        super(p, c, dx, dy, diametro, diametro, parent);
    }
     
    @Override
    public void paint(Graphics2D g){
        g.setColor(this.c);
        g.fill(new Ellipse2D.Double(p.x, p.y, width, width));
        g.setColor(Color.GRAY);
        g.drawOval(p.x, p.y, width, width);
    }
    
    public boolean interseccion(Ball otherBall) {
        if (otherBall == null) {
            return false;
        }
        int x1 = p.x;
        int y1 = p.y;
        int x2 = otherBall.p.x;
        int y2 = otherBall.p.y;

        return x1 < x2 + otherBall.width &&
               x1 + width > x2 &&
               y1 < y2 + otherBall.height &&
               y1 + height > y2;
    }
    
   
    
        public int getRadio() {
        return width / 2;
        }
        
         public boolean isAtTargetPosition(int targetX, int targetY) {
        return p.x == targetX && p.y == targetY;
    }
         
        public void resetToStartPosition() {
            p.setLocation(parent.getWidth() / 2, parent.getHeight() / 2);
            dx=1;
            dy=1;
        }
        public void resetToStartPosition2() {
            p.setLocation((parent.getWidth() / 2)+30, parent.getHeight() / 2);
            dx=1;
            dy=1; 
        }
        public void resetToStartPosition3() {
            p.setLocation((parent.getWidth() / 2+30), parent.getHeight() / 2);
            dx=1;
            dy=1;
        }
 
}

   
