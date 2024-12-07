//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.thread;

import arkanoid.records.saveRecord;
import arkanoid.Block.Block;
import arkanoid.board.Gameover;
import arkanoid.Score.Score;
import arkanoid.Stopwatch.Stopwatch;
import arkanoid.ball.Ball;

public class StopwatchThread extends Thread {
    private final Ball Balls[];
    private final Block Blocks[];
    private final GameThread GameThreads[];
    public final Stopwatch stopwatch;
    private final Score score;
    Gameover go = new Gameover();
    saveRecord sr;
    private volatile boolean running;
    private volatile boolean paused;
    private boolean isGameRunning = false;
    private boolean gameThreadsInitialized = false;
    

    public StopwatchThread(Stopwatch stopwatch,Ball Balls[],Block Blocks[],GameThread GameThreads[],Score score) {
        this.stopwatch = stopwatch;
        this.Balls = Balls;
        this.Blocks = Blocks;
        this.GameThreads = GameThreads;
        this.running = true;
        this.paused = false;
        this.score=score;
    }

     private void stopGameThreads() {
        if (GameThreads != null) {
            for (GameThread gameThread : GameThreads) {
                if (gameThread != null) {
                    gameThread.stopThread();
                }
            }
        }
      }
     
        private void stopGameThreadsOnPause() {
        if (GameThreads != null) {
            for (GameThread gameThread : GameThreads) {
                if (gameThread != null) {
                    gameThread.pauseThread();
                }
            }
        }
    }
    
        private void resumeGameThreads() {
        if (GameThreads != null) {
            for (GameThread gameThread : GameThreads) {
                if (gameThread != null) {
                    gameThread.resumeThread(); // Debes agregar un método para reanudar la GameThread
                }
            }
        }
    }
     
     public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    public boolean isGameThreadsInitialized() {
        return gameThreadsInitialized;
    }

    public void setGameThreadsInitialized(boolean gameThreadsInitialized) {
        this.gameThreadsInitialized = gameThreadsInitialized;
    }


    public void stopStopwatchthread() {
        running = false;
    }

    public void resetStopwatch() {
            stopwatch.resetStopwatch();
            stopwatch.startStopwatch();
            paused = false;
            running = true;
    }

    public synchronized void pauseStopwatch() {
        paused = true;
    }

    public synchronized void resumeStopwatch() {
        paused = false;
        notify();
    }

            @Override
            public void run() {
                try {
                    stopwatch.startStopwatch();
                    while (running) {
                        synchronized (this) {
                            while (paused) {
                                
                               stopGameThreadsOnPause();
                               wait();
                            }
                            resumeGameThreads();
                        }

                        if (gameThreadsInitialized==false) {
                            initGameThreads();
                            gameThreadsInitialized = true;
                        }
                        countBlocksAtTargetPosition();
                        score.increaseScore(countBlocksAtTargetPosition());
                        checkBlocksPosition();
                        checkBallsPosition();
                        stopwatch.parent.repaint();
                        stopwatch.updateTime();
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Error en el hilo del cronómetro: " + e.getMessage());
                }
            }
                private void initGameThreads() {
                      if (GameThreads != null) {
                          for (GameThread gameThread : GameThreads) {
                              if (gameThread != null) {
                                  initGame(gameThread);
                              }
                          }
                      }
                  }
                        public void initGame(GameThread gt) {
                            gt.start();
                        }
                        private void checkBlocksPosition() {
                            int blocksAtTargetPosition = countBlocksAtTargetPosition();
                            if (blocksAtTargetPosition == Blocks.length) {
                            System.out.println("Todos los bloques están en la posición objetivo. Deteniendo el hilo del juego.");
                            stopGameThreads();
                            pauseStopwatch();
                            saveRecord sr = new saveRecord(score, this);
                            sr.setScore(score.getScore(),stopwatch.getMinutes(),stopwatch.getSeconds(),stopwatch.getMilliseconds());
                            sr.setLocationRelativeTo(null);
                            sr.setVisible(true);
                            
                            }
                        }  
                        private void checkBallsPosition() {
                            int ballsAtTargetPosition = 0;   
                            for (Ball ball : Balls) {
                                if (ball != null) {
                                    if(ball.isAtTargetPosition(800, 800)){
                                    ballsAtTargetPosition++;
                                    }
                                }
                            }
                            if (ballsAtTargetPosition == getNumBalls()) {
                                System.out.println("Todas las bolas están en la posición objetivo. Deteniendo las threads y la StopwatchThread.");
                                stopGameThreads();
                                pauseStopwatch();
                                go.setScore(score.getScore(),stopwatch.getMinutes(),stopwatch.getSeconds(),stopwatch.getMilliseconds());
                                go.setLocationRelativeTo(null); 
                                go.setVisible(true);
                                resetGameObjectsPosition();
                               
                            }
                        }
                        public void resetGameObjectsPosition() {
                            int numBalls=0;
                            for (Ball ball : Balls) {
                                numBalls++;
                                if (ball != null) {
                                    if(numBalls==1){
                                        ball.resetToStartPosition();
                                    }else if(numBalls==2){
                                        ball.resetToStartPosition2();
                                    }else{
                                        ball.resetToStartPosition3();
                                    }
                                }
                                stopwatch.resetStopwatch();
                            }
                                int bdx=0,bdy= 100;
                                int blocks=0;
                                for(int i=0;i<5;i++){
                                    bdy=bdy+30;
                                    for(int f=0;f<=9;f++){
                                        if(f==0){
                                            bdx=2;
                                        }else{
                                            bdx=bdx+68;
                                        }
                                        Blocks[blocks].dx=bdx;
                                        Blocks[blocks].dy=bdy;
                                        blocks++;
                                    }
                                }
                        }
                        private int countBlocksAtTargetPosition() {
                            int blocksAtTargetPosition = 0;

                            for (Block block : Blocks) {
                                if (block != null && block.isAtTargetPosition(700, 700)) {
                                    blocksAtTargetPosition++;
                                }
                            }

                            return blocksAtTargetPosition;
                        }
                        public int getNumBalls(){
                            int numBall=0;
                            for(Ball ball : Balls){
                                if(ball!=null){
                                    numBall++;
                                }
                            }
                            return numBall;
                        }
                        public int getSpeed(){
                            int speed=GameThreads[0].getSpeed();
                            return speed;
                        }
                        public int getScore(){
                            int finalScore;
                            if(stopwatch.getMinutes()==0){
                                if(stopwatch.getSeconds()==0){
                                    finalScore=stopwatch.getMilliseconds()*score.getScore();
                                }else{
                                    finalScore=stopwatch.getSeconds()*stopwatch.getMilliseconds()*score.getScore();
                                }
                                }else if(stopwatch.getSeconds()==0){
                                if(stopwatch.getMilliseconds()==0){
                                    finalScore=stopwatch.getMinutes()*score.getScore();
                                }else{
                                    finalScore=stopwatch.getMinutes()*stopwatch.getMinutes()*score.getScore();
                                }
                                }else{
                                    finalScore=stopwatch.getMinutes()*stopwatch.getSeconds()*stopwatch.getMilliseconds()*score.getScore();
                                }
                            return finalScore;
                        }
}



