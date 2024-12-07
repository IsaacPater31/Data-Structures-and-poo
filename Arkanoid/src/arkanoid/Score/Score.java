//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.Score;

import arkanoid.panel.GamePanel;
import interfaces.Paintable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score implements Paintable {
    private int score;
    private Font font;
    private Color textColor;
    private int positionX;
    private int positionY;
    private GamePanel parentPanel;

    public Score(Font font, Color textColor, int positionX, int positionY, GamePanel parentPanel) {
        this.score = 0;

        this.font = font;
        this.textColor = textColor;
        this.positionX = positionX;
        this.positionY = positionY;
        this.parentPanel = parentPanel;
    }

    public void increaseScore(int i) {
       int x=0;  
       if(i>x){
       score=i*100;
       x=i;
        if(score==5000){    
            x=5;
            score=x*1000;
        }
       }
    }
    
     public int getScore() {
        return score;
    }
    public void resetScore(){
    
    score=0;
    
    }

    @Override
    public void paint(Graphics2D g) {
        g.setFont(font);
        g.setColor(textColor);
        g.drawString("Puntaje: " + score, positionX, positionY);
    }
}