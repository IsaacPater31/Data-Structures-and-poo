//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.Stopwatch;

import interfaces.Paintable;
import arkanoid.panel.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.Instant;

public class Stopwatch implements Paintable {
    private int minutes;
    private int seconds;
    private int milliseconds;
    public GamePanel parent;
    private Font font;
    private Color textColor;
    private int positionX;
    private int positionY;
    private Instant startTime;
    

    public Stopwatch(Font font, Color textColor, int positionX, int positionY,GamePanel parent) {
        this.font = font;
        this.textColor = textColor;
        this.positionX = positionX;
        this.positionY = positionY;
        this.parent = parent; 
        this.startTime = null;
    }
    
    public void startStopwatch(){
        this.startTime = Instant.now();
    }
    // Método para actualizar el tiempo del cronómetro
    public void updateTime() {
        if(startTime!=null){
        Instant currentTime = Instant.now();
        Duration duration = Duration.between(startTime, currentTime);
        this.minutes = (int) duration.toMinutes();
        this.seconds = (int) (duration.toSeconds() % 60);
        this.milliseconds = (int) (duration.toMillis() % 100);
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }
    

    public void resetStopwatch() {
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
        startTime= null;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setFont(font);
        g.setColor(textColor);
        g.drawString("Tiempo: " + formatTime(), positionX, positionY);
    }

    public String formatTime() {
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }
}