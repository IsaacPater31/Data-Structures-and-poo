//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.Block;



import arkanoid.panel.GamePanel;
import interfaces.*;
import java.awt.Graphics2D;
import java.awt.Color;



public class Block implements Paintable{
    Color c;
    public int dx;
    public int dy;
     private int targetX;  
    private int targetY;
    public int width;
    public int height;
    public GamePanel parent;

    public Block(Color c, int dx, int dy, int width, int height, GamePanel parent) {
        this.c = c;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.parent = parent;
    }
    

    
    public void removeBlock(){
        dx=700;
        dy=700;
    }
   
    @Override
    public void paint(Graphics2D g){
        g.setColor(this.c);
        g.fillRect(dx, dy, width, height);
        g.setColor(Color.gray);
        g.drawRect(dx, dy, width, height);
    }

    
    @Override
    public String toString(){
        return "Shape [c=" + c + ", dx=" + dx + ", dy=" + dy
        + ", width=" + width + ", height=" + height + ", parent="
        + parent + "]";
    } 
    
       public boolean isAtTargetPosition(int targetX, int targetY) {
        return dx == targetX && dy == targetY;
    }
    
}