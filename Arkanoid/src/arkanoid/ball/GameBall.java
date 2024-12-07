//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.ball;

import arkanoid.Block.*;
import arkanoid.pad.GamePad;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;




public class GameBall extends Ball{
    private GamePad padBottom;
    private GameBall[] otherBalls;
    private Block[] Blocks;

    public GameBall(Point p, Color c, double dx, double dy, int diametro , Container parent, GamePad padBottom , GameBall[] otherBalls, Block[] Blocks) {
        super(p, c, dx, dy, diametro, parent);
        this.padBottom = padBottom;
        this.otherBalls = otherBalls;
        this.Blocks = Blocks;
    }
   
        
        
        @Override
        public void move(){

            System.out.println("dirx    "+dx);
            System.out.println("diry    "+dy);
            System.out.println("px    "+p.x);
            System.out.println("py    "+p.y);
            System.out.println("padBotton.left      "+padBottom.left);
            System.out.println("padBotton.right     "+padBottom.right);

            if (p.y + dy < 0) {
            dy = -dy; 
            } else if (p.y + height + dy > parent.getHeight() - parent.getInsets().bottom) {
            }
            if (p.x + dx < 0 || p.x + width + dx > parent.getWidth() - parent.getInsets().right) {
            dx = -dx; 
            }


            if (dy > 0 && p.y + height > padBottom.top && p.y < padBottom.top) {
                if (p.x + width > padBottom.left && p.x < padBottom.right) {
                    dy = -dy;
                }
            }

            if(dy<1 && dy>0){
                if(p.y<parent.getHeight()){
                    dy=1;}
            }
            if(dx<1 && dx>0){
                if(p.x<parent.getWidth()/2){
                    dx=-1;
                }else{
                    dx=1;
                }
            }

            for (GameBall otherBall : otherBalls) {
                if (this != otherBall && this.interseccion(otherBall)) {
                    actionCollisionBalls(this, otherBall);
                }
            }
            actionBlockCollisions(Blocks);


        p.x += dx;
        p.y += dy;
        }   
        
        private void actionCollisionBalls(GameBall ball1, GameBall ball2) {
            if (ball1 != null && ball2 != null) {
        // Calcular nuevas velocidades después de la colisión
        double angulo = Math.atan2(ball2.p.y - ball1.p.y, ball2.p.x - ball1.p.x);
        double speed1 = Math.sqrt(ball1.dx * ball1.dx + ball1.dy * ball1.dy);
        double speed2 = Math.sqrt(ball2.dx * ball2.dx + ball2.dy * ball2.dy);

        double direccion1 = Math.atan2(ball1.dy, ball1.dx);
        double direccion2 = Math.atan2(ball2.dy, ball2.dx);

        double newDx1 = speed1 * Math.cos(direccion1 - angulo);
        double newDy1 = speed1 * Math.sin(direccion1 - angulo);
        double newDx2 = speed2 * Math.cos(direccion2 - angulo);
        double newDy2 = speed2 * Math.sin(direccion2 - angulo);

        ball1.dx = Math.cos(angulo) * newDx2 + Math.cos(angulo + Math.PI / 2) * newDy2;
        ball1.dy = Math.sin(angulo) * newDx2 + Math.sin(angulo + Math.PI / 2) * newDy2;
        ball2.dx = Math.cos(angulo) * newDx1 + Math.cos(angulo + Math.PI / 2) * newDy1;
        ball2.dy = Math.sin(angulo) * newDx1 + Math.sin(angulo + Math.PI / 2) * newDy1;
    }
        }
        public boolean collisionWithBlock(Block Blocks) {
            if (Blocks == null) {
                return false;
            }   
            int x1 = p.x;
            int y1 = p.y;
            int x2 = Blocks.dx;
            int y2 = Blocks.dy;

            return x1 < x2 + Blocks.width &&
                   x1 + width > x2 &&
                   y1 < y2 + Blocks.height &&
                   y1 + height > y2;
        }
        public void actionBlockCollisions(Block[] blocks) {
            for (Block block : blocks) {
                if (block != null && this.collisionWithBlock(block)) {
                    actionBlockCollision(block);
                    block.removeBlock();
                }
            }
        }
        private void actionBlockCollision(Block Blocks) {
            double ballCentroX = p.x + getRadio();
            double ballCentroY = p.y + getRadio();
            double blockCentroX = Blocks.dx + Blocks.width / 2.0;
            double blockCentroY = Blocks.dy + Blocks.height / 2.0;
            double dx = ballCentroX - blockCentroX;
            double dy = ballCentroY - blockCentroY;
            double distanciaX = Math.abs(ballCentroX - blockCentroX) - Blocks.width / 2.0;
            double distanciaY = Math.abs(ballCentroY - blockCentroY) - Blocks.height / 2.0;

         
            if (distanciaX > distanciaY) {
                this.dx = -this.dx;
            } else {
                this.dy = -this.dy;
            } 
        }       
        
}