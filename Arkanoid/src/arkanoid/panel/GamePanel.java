//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.panel;

import arkanoid.Block.*;
import interfaces.Paintable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JPanel;



public class GamePanel extends JPanel{
    private final List<Paintable> list = Collections.synchronizedList(new ArrayList<>());
    
    public void add(Paintable obj){
        synchronized (list) {
        list.add(obj);
    }
    }
     
    public void remove(Paintable obj){
        synchronized (list) {
        if(list.contains(obj))
            list.remove(obj);
        }
    }
    
    public void printBlocks(Block[] block1){
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
                block1[blocks++] = new Block(Color.WHITE, bdx, bdy, 65,27,this);
                this.add(block1[blocks-1]);
            }
        }
    }
     
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        for(Paintable obj : list){
            obj.paint(g);
        }
    }

    public int getCount(){
        this.list.size();
        return 0;
   }
}