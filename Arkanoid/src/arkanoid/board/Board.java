//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.board;

import arkanoid.Block.*;
import arkanoid.Score.Score;
import arkanoid.Stopwatch.Stopwatch;
import arkanoid.ball.GameBall;
import arkanoid.pad.GamePad;
import arkanoid.panel.GamePanel;
import arkanoid.records.IRecords;
import arkanoid.records.Records;
import arkanoid.thread.StopwatchThread;
import arkanoid.thread.GameThread;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class Board extends javax.swing.JFrame implements ActionListener {

    

    
    Block [] block1 = new Block[50];
    StopwatchThread stopwatchthread1;
     
    final static int VERYSLOW = 100;
    static final int SLOW = 50;
    static final int FAST = 20;
    static final int VERYFAST = 1;
    private boolean juegoenmarcha=false;
    int speed=0;
    public GamePanel panel = new GamePanel();
    GamePad pad = new GamePad(580, 100, 600,200, Color.ORANGE, this); 
    Font font = new Font("Arial", Font.PLAIN, 16);
    Stopwatch stopwatch = new Stopwatch(font, Color.WHITE, 700 - 150, 20,panel);
    Score score = new Score(font, Color.WHITE, 700 - 150, 40, panel);
    GameBall[] balls = new GameBall[3];
    GameThread[] gameThreads = new GameThread[3];
    int panelWidth = 700;
    int panelHeight = 700;

    
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Board() {
        
      
       
      
        panel.setBackground(Color.BLACK);
                                //ALT  IZQ  ALT+PRO ANC  
        
        
        
      JMenuBar mainMenuBar = new JMenuBar();

                    // Menú "Game"
                    JMenu gameMenu = new JMenu("Game");
                    mainMenuBar.add(gameMenu);

                    JMenuItem playItem = new JMenuItem("Play");
                    gameMenu.add(playItem);
                    playItem.setActionCommand("PLAY");
                    playItem.addActionListener(this);

                    JMenuItem restartItem = new JMenuItem("Restart");
                    gameMenu.add(restartItem);
                    restartItem.setActionCommand("RESTART");
                    restartItem.addActionListener(this);
                    

                     JMenuItem stopItem = new JMenuItem("Stop");
                    gameMenu.add(stopItem);
                    stopItem.setActionCommand("STOP");
                    stopItem.addActionListener(this);
                    
                    JMenuItem closeItem = new JMenuItem("Close");
                    gameMenu.add(closeItem);
                    closeItem.setActionCommand("CLOSE");
                    closeItem.addActionListener(this);

                    // Menú "Options"
                    JMenu optionsMenu = new JMenu("Options");
                    mainMenuBar.add(optionsMenu);

                    // Submenú para "Speed"
                    JMenu speedSubMenu = new JMenu("Speed");
                    optionsMenu.add(speedSubMenu);

                    ButtonGroup speedGroup = new ButtonGroup();
                    
                    JRadioButtonMenuItem veryslowItem = new JRadioButtonMenuItem("Very Slow");
                    veryslowItem.setActionCommand("VERY SLOW");
                    veryslowItem.addActionListener(this);
                    speedGroup.add(veryslowItem);
                    speedSubMenu.add(veryslowItem);
                    
                    
                    JRadioButtonMenuItem slowItem = new JRadioButtonMenuItem("Slow");
                    slowItem.setActionCommand("SLOW");
                    slowItem.addActionListener(this);
                    speedGroup.add(slowItem);
                    speedSubMenu.add(slowItem);                     

                    JRadioButtonMenuItem fastItem = new JRadioButtonMenuItem("Fast");
                    fastItem.setActionCommand("FAST");
                    fastItem.addActionListener(this);
                    speedGroup.add(fastItem);
                    speedSubMenu.add(fastItem);
                    
                    JRadioButtonMenuItem veryfastItem = new JRadioButtonMenuItem("Very Fast");
                    veryfastItem.setActionCommand("VERYFAST");
                    veryfastItem.addActionListener(this);
                    speedGroup.add(veryfastItem);
                    speedSubMenu.add(veryfastItem);
                    
                    
                    JMenu ballsSubMenu = new JMenu("Balls");
                    optionsMenu.add(ballsSubMenu);

                    ButtonGroup ballsGroup = new ButtonGroup();
                    JRadioButtonMenuItem oneBallItem = new JRadioButtonMenuItem("Balls : ONE");
                    oneBallItem.setActionCommand("ONE");
                    oneBallItem.addActionListener(this);
                    ballsGroup.add(oneBallItem);
                    ballsSubMenu.add(oneBallItem);
                    
                    JRadioButtonMenuItem twoBallsItem = new JRadioButtonMenuItem("Balls: TWO");
                    twoBallsItem.setActionCommand("TWO");
                    twoBallsItem.addActionListener(this);
                    ballsGroup.add(twoBallsItem);
                    ballsSubMenu.add(twoBallsItem);

                    JRadioButtonMenuItem threeBallsItem = new JRadioButtonMenuItem("Balls: THREE");
                    threeBallsItem.setActionCommand("THREE");
                    threeBallsItem.addActionListener(this);
                    ballsGroup.add(threeBallsItem);
                    ballsSubMenu.add(threeBallsItem);

                    JButton recordsButton = new JButton("Records");
                    recordsButton.setActionCommand("RECORDS1");
                    recordsButton.addActionListener(this);
                    JMenu recordsMenu = new JMenu("Records");
                    recordsMenu.add(recordsButton);
                    mainMenuBar.add(Box.createHorizontalStrut(480)); // Espacio horizontal para separación
                    mainMenuBar.add(recordsMenu);

               

                    
           
                    
                    
    panel.add(stopwatch);
    panel.add(pad);
    this.add(panel, BorderLayout.CENTER);
    this.setJMenuBar(mainMenuBar);
    panel.printBlocks(block1);
        
        // GAME      
       
       


                balls[0] = new GameBall(new Point(panelWidth / 2, panelHeight / 2), Color.RED, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[1], balls[2]}, block1);
                panel.add(balls[0]);

                balls[1] = new GameBall(new Point((panelWidth / 2) - 45, panelHeight / 2), Color.BLUE, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[0], balls[2]}, block1);
                panel.add(balls[1]);

                balls[2] = new GameBall(new Point((panelWidth / 2) + 45, panelHeight / 2), Color.GREEN, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[0], balls[1]}, block1);
                panel.add(balls[2]);
        
                gameThreads[0] = new GameThread(balls[0], speed, "GAMETHREAD1");
                gameThreads[1] = new GameThread(balls[1], speed, "GAMETHREAD2");
                gameThreads[2] = new GameThread(balls[2], speed, "GAMETHREAD3");

              
               
        stopwatchthread1 = new StopwatchThread(stopwatch, balls, block1, gameThreads,score);
       
        
        panel.add(score);
        panel.add(pad);
        this.add(panel, BorderLayout.CENTER);
        
        
        this.addKeyListener(new KeyAdapter() {
             @Override
             public void keyPressed(KeyEvent e) 
             {                
                 int keyCode = e.getKeyCode();
                 
                 switch(keyCode)
                 {
                 case 39:
                     pad.moveRight(panel.getGraphics());
                     repaint();
                     break;
                 case 37:
                    pad.moveLeft(panel.getGraphics());
                    repaint();
                     break;
                 case 68:
                     pad.moveRight(panel.getGraphics());
                     repaint();
                     break;
                 case 65: 
                     pad.moveLeft(panel.getGraphics());
                     repaint();
                     break;
                 }                    
             }
         });
        
        
    //initComponents();
    
    this.setTitle("Arkanoid - UdC");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(panelWidth, panelHeight);
    this.setResizable(false); 

    }
    
    
  @Override
   public void actionPerformed(ActionEvent e){
        int comp=5;
         if(this.stopwatchthread1!= null){
             
             System.out.println("Action "+e.getActionCommand());
             
             
            if(e.getActionCommand().equals("PLAY")){
                if(speed!=0){
                    stopwatchthread1.resetStopwatch();
                    stopwatchthread1.start();
                    juegoenmarcha=true;
                }else{
                    System.out.println("Elige una velocidad");
                }
            }
            else if(juegoenmarcha==false){
             
                switch (e.getActionCommand()) {
                    case "ONE":
                        panel.remove(balls[0]);
                        panel.remove(balls[1]);
                        panel.remove(balls[2]);
                        panel.add(balls[0]);
                        gameThreads[1]=null;
                        gameThreads[2]=null;
                        balls[1]=null;
                        balls[2]=null;
                        panel.repaint();
                        break;
                    case "TWO":
                         panel.remove(balls[0]);
                         if(balls[1]==null){
                         
                         balls[1] = new GameBall(new Point((panelWidth / 2) - 45, panelHeight / 2), Color.BLUE, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[0], balls[2]}, block1);
                         }
                         if(gameThreads[1]==null){
                         
                         gameThreads[1] = new GameThread(balls[1], speed, "GAMETHREAD2");
                         }
                        panel.remove(balls[1]);
                        panel.remove(balls[2]);
                        panel.add(balls[0]);
                        panel.add(balls[1]);
                        gameThreads[2]=null;
                        balls[2]=null;
                        
                        panel.repaint();
                        break;
                    case "THREE":
                        
                        panel.remove(balls[0]);
                        if(balls[1]==null){
                         
                         balls[1] = new GameBall(new Point((panelWidth / 2) - 45, panelHeight / 2), Color.BLUE, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[0], balls[2]}, block1);
                         }
                         if(gameThreads[1]==null){
                         
                         gameThreads[1] = new GameThread(balls[1], speed, "GAMETHREAD2");
                         }
                         if(balls[2]==null){
                         
                         balls[2] = new GameBall(new Point((panelWidth / 2) + 45, panelHeight / 2), Color.GREEN, 1.0, 1.0, 15, panel, pad, new GameBall[]{balls[0], balls[1]}, block1);
                         }
                         if(gameThreads[2]==null){
                         
                         gameThreads[2] = new GameThread(balls[2], speed, "GAMETHREAD3");
                         }
                         
                        panel.remove(balls[1]);
                        panel.remove(balls[2]);
                        panel.add(balls[0]);
                        panel.add(balls[1]);
                        panel.add(balls[2]);
                        panel.repaint();
                        break;
                    default:
                        break;
                    }
                    switch (e.getActionCommand()) {
                        case "VERYSLOW":
                            this.speed = VERYSLOW;
                            this.gameThreads[0].setSpeed(VERYSLOW);
                            if(gameThreads[1]!=null){
                            this.gameThreads[1].setSpeed(VERYSLOW);}
                            if(gameThreads[2]!=null){
                            this.gameThreads[2].setSpeed(VERYSLOW);}
                            break;
                        case "SLOW":
                            this.speed = SLOW;
                            this.gameThreads[0].setSpeed(SLOW);
                            if(gameThreads[1]!=null){
                            this.gameThreads[1].setSpeed(SLOW);}
                            if(gameThreads[2]!=null){
                            this.gameThreads[2].setSpeed(SLOW);}
                            break;
                        case "FAST":
                            this.speed = FAST;
                            this.gameThreads[0].setSpeed(FAST);
                            if(gameThreads[1]!=null){
                            this.gameThreads[1].setSpeed(FAST);}
                            if(gameThreads[2]!=null){
                            this.gameThreads[2].setSpeed(FAST);}
                            break;
                        case "VERYFAST":
                            this.speed = VERYFAST;
                            this.gameThreads[0].setSpeed(VERYFAST);
                            if(gameThreads[1]!=null){
                            this.gameThreads[1].setSpeed(VERYFAST);}
                            if(gameThreads[2]!=null){
                            this.gameThreads[2].setSpeed(VERYFAST);}
                            break;
                        default:
                            break;
                    }
                    juegoenmarcha=false;
                
             }
             
                    if (e.getActionCommand().equals("CLOSE")) {
                  
                    dispose();
                    System.exit(0); 
               }
                   
                    if (e.getActionCommand().equals("STOP")) {
                        stopwatchthread1.pauseStopwatch(); 
                    }
                    
                        if (e.getActionCommand().equals("RESTART")) {
                            score.resetScore();
                            stopwatchthread1.resetGameObjectsPosition();
                            stopwatchthread1.resetStopwatch();
                            stopwatchthread1.resumeStopwatch();                  
                        }
                        if (e.getActionCommand().equals("RECORDS1")) {
                            
                            Records records1[]=new Records[5];
                            IRecords r =new IRecords();
                            
                             try{
                                File file = new File("data\\Data.csv"); //TENER ACCESO AL ARCHIVO
                                BufferedReader br = new BufferedReader(new FileReader(file));//MANEJO MEMORIA
                                String line; //VARIABLE PARA MANIPULAR CADA LINEA DEL ARCHIVO
                                int i=0;

                                br.readLine();
                                while((line=br.readLine())!=null){

                                    String [] str = line.split(","); 


                                            String Nombre = (str[0]);
                                            int scoree =Integer.parseInt(str[1]);
                                            int ballss = Integer.parseInt(str[2]);
                                            String ishowSpeed = (str[3]);
                                            
                                            


                                            records1[i] = new Records(Nombre, scoree, ballss, ishowSpeed);

                                            i++;


                                }
                            }
                            catch(IOException | NumberFormatException y){
                                System.out.println(y);
                            }

                                 for (int x = 0; x < records1.length; x++) {
                                        r.jTextArea1.append("Nombre: "+records1[x].getName()+"\n");
                                        r.jTextArea1.append("Score: "+records1[x].getScore()+"\n");
                                        r.jTextArea1.append("Balls: "+records1[x].getBalls()+"\n");
                                        r.jTextArea1.append("SPEED: "+records1[x].getSpeed()+"\n");
                                        r.jTextArea1.append("==================================\n");
                                        
                                         }

                            r.setVisible(true);
                            r.setLocationRelativeTo(null);  
                            comp++;
                        }                 
        }
    }
   
      
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}