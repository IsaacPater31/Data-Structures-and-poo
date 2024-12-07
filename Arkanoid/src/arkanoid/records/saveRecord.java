//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.records;

import arkanoid.Score.*;
import arkanoid.thread.StopwatchThread;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yo
 */
public class saveRecord extends javax.swing.JFrame {

    Score score;
    StopwatchThread stopwacth;
    
    public saveRecord(Score score, StopwatchThread stopwacth) {
        this.score=score;
        this.stopwacth=stopwacth;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("nombre");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Guarda tu puntuación");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel3.setText("GANASTÉ");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jButton1))
                                .addGap(66, 66, 66)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel4)))
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            String playerName = jTextField1.getText();

    if (!playerName.isEmpty() && playerName.length() <= 5) {
        try {
            Path filePath = Paths.get("data", "Data.csv");

            // Lee las últimas 5 líneas del archivo o todas las líneas si hay menos de 5
            List<String> lines = Files.readAllLines(filePath);
            int startIndex = Math.max(0, lines.size() - 5);

            // Modifica el registro más antiguo (si hay al menos 5 registros)
            if (lines.size() >= 5) {
                String record = playerName + "," + stopwacth.getScore() + ","
                        + stopwacth.getNumBalls() + "," + stopwacth.getSpeed();
                lines.set(startIndex, record);
            } else {
                // Si hay menos de 5 registros, simplemente añade uno nuevo
                String record = playerName + "," + stopwacth.getScore() + ","
                        + stopwacth.getNumBalls() + "," + stopwacth.getSpeed();
                lines.add(record);
            }

            // Escribe todas las líneas de vuelta al archivo
            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

            // Proporciona retroalimentación al usuario
            jLabel2.setText("Registro guardado exitosamente");
            this.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción adecuadamente en tu aplicación
            jLabel2.setText("Error al guardar el registro. Por favor, inténtalo de nuevo.");
        }
    } else {
        jLabel2.setText("Ingresa 1 o menos de 6 caracteres");
    }
    }//GEN-LAST:event_jButton1ActionPerformed
    public void setScore(int score,int minutes, int second, int milliseconds) {
            int finalScore=0;
            if(minutes==0){
                if(second==0){
                    finalScore=milliseconds*5000;
                }else{
                    finalScore=second*milliseconds*5000;
                }
            }else if(second==0){
                if(milliseconds==0){
                    finalScore=minutes*5000;
                }else{
                    finalScore=minutes*milliseconds*5000;
                }
            }else{
                finalScore=minutes*second*milliseconds*5000;
            }
            jLabel4.setText("Su puntaje es de " + finalScore);
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}