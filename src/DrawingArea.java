
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 350239737
 */
public class DrawingArea extends javax.swing.JPanel {

    /**
     * Creates new form DrawingArea
     */
    
    public static Timer t1;
    int xlanding = -50, ylanding = -50, xtakeoff = -50, ytakeoff = 188;
    public DrawingArea() {
        initComponents();
         t1 = new Timer(14, new DrawingArea.TimerListener());
       
    }
    
     @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the images of the underwater background and crab
        Image landing = Toolkit.getDefaultToolkit().getImage("airplanelanding-2.png");
        Image takeoff = Toolkit.getDefaultToolkit().getImage("airplaneTakeoff-2.png");
        
        g.fillRect(0, 0, 500, 500);
        
        //Draws underwater background and crab onto panel
        if (MainFrame.loop <= 1){
            g.drawImage(landing, xlanding, ylanding, this);
        }
        if (MainFrame.loop == 3){ 
            g.drawImage(takeoff, xtakeoff, ytakeoff, this);
        }

        xlanding = xlanding + 2;
        ylanding++;
        xtakeoff = xtakeoff + 4;
        ytakeoff =  ytakeoff - 2;
        
    }
    
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (MainFrame.interval == 4 && MainFrame.loop <= 1){
            xlanding = -50;
            ylanding = -50;
//                System.out.println("YTESTSTTS");
        }
            if (MainFrame.interval == 2 && MainFrame.loop == 3){
            xtakeoff = -50;
            ytakeoff = 188;
//                System.out.println("please");
            }
            repaint();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
