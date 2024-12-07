//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.thread;

import arkanoid.ball.Shape;
import javax.swing.SwingUtilities;

public class GameThread extends Thread {
    private Shape shape;
    private int speed;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public GameThread(Shape shape, int speed, String name) {
        super(name);
        this.shape = shape;
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }

    public void stopThread() {
        running = false;
    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        paused = false;
    }

    
    public void stopThreadAndExit() {
        running = false;
        SwingUtilities.invokeLater(() -> {
            System.exit(0);
        });
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        try {
            while (running) {
                if(shape.limiteBall(shape)==true){
                    shape.p.x=800;
                    shape.p.y=800;
                    shape.dx=0;
                    shape.dy=0;
                    
                }
                if (!paused) {
                    shape.parent.repaint();
                    shape.move();
                }
                Thread.sleep(speed);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}