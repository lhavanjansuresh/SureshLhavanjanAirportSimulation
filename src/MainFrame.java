
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
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
public class MainFrame extends javax.swing.JFrame {
    
    public static Queue<Integer> landing = new LinkedList<Integer>();
    public static Queue<Integer> takeOff = new LinkedList<Integer>();
    public static int interval = 4;
    public static int loop = 0;
    Timer t;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        jDialog1.setLocation(dim.width/2-jDialog1.getSize().width/2, dim.height/2-jDialog1.getSize().height/2);
        File takeoffFile = new File("takeoffs.txt");
        File arrivalFile = new File("arrivals.txt");

        try {
            takeOff = readFile(takeoffFile, takeOff);
            landing = readFile(arrivalFile, landing);
            updateText(takeOff, 't');
            updateText(landing, 'a');
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        t = new Timer(1000, new MainFrame.TimerListener());
    }
    
     private class TimerListener implements ActionListener {   
         
        @Override
        public void actionPerformed(ActionEvent e) {

            if (loop < 2) {
                if (interval == 0) {
                    statusLabel.setText("Flight " + landing.toString().substring(1, 5) + " landed.");
                    landing.remove();
                    updateText(landing, 'a');
                    interval = 4;
                    loop++;
                }
                else if (!landing.isEmpty()) {
                    statusLabel.setText("Flight " + landing.toString().substring(1, 5) + " is landing. " + interval);
                    interval--;
                    System.out.println(loop);
                }
                else{
                    loop = 3;
                    interval = 2;
                }

                if (loop == 2) {
                    loop = 3;
                    interval = 2;
                }
            } else {
                
                if (interval == 0) {
                    statusLabel.setText("Flight " + takeOff.toString().substring(1, 5) + " departured.");
                    takeOff.remove();
                    updateText(takeOff, 't');
                    interval = 2;
                    loop++;
                }
                
                else if (!takeOff.isEmpty()) {
                    statusLabel.setText("Flight " + takeOff.toString().substring(1, 5) + " is taking off. " + interval);
                    interval--;
                }
                else{
                    loop = 0;
                    interval = 4;
                }
                
                if (loop == 4) {
                    loop = 0;
                    interval = 4;
                }
            }
            
            if (takeOff.isEmpty() && landing.isEmpty()){
                statusLabel.setText("Waiting for further flights.");
                loop = 0;
            }
            
        }
    }

    public void updateText(Queue<Integer> flightNum, char x) {
        String temp;
        StringTokenizer st;
        int tokens;
        if (x == 't') {
            temp = takeOff.toString().substring(1, takeOff.toString().length() - 1);
            st = new StringTokenizer(temp, ", ");
            temp = "";
            tokens = st.countTokens();
            for (int a = 0; tokens > a; a++) {
                temp = temp + st.nextToken() + "\n";
            }
            takeoffsTextArea.setText(temp);
        } else if (x == 'a') {
            temp = landing.toString().substring(1, landing.toString().length() - 1);
            st = new StringTokenizer(temp, ", ");
            temp = "";
            tokens = st.countTokens();
            for (int a = 0; tokens > a; a++) {
                temp = temp + st.nextToken() + "\n";
            }
            arrivalsTextArea.setText(temp);
        }
    }

    public static Queue<Integer> readFile(File file, Queue<Integer> action) throws FileNotFoundException {
        Scanner input = new Scanner(file); //creates file object
        try {
            //while input has next line
            while (true) {
                int values = Integer.parseInt(input.nextLine()); //Saves the line into an int
                action.add(values);
            }
        } catch (NoSuchElementException ex) {
        }
        input.close(); //releases the file for other use
        return action;
    }

    public static boolean checkVerification(String value) {
      
        try{
            Integer.parseInt(value);
        }catch (NumberFormatException ex) {
            return false;
        }
            return true;
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        drawingArea1 = new DrawingArea();
        arrivingTextField = new javax.swing.JTextField();
        takeoffTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        takeoffsTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        arrivalsTextArea = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jDialog1.setFocusCycleRoot(false);
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(400, 150));
        jDialog1.setType(java.awt.Window.Type.POPUP);

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Please type a valid Integer value!");

        jButton1.setText("Ok!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout drawingArea1Layout = new javax.swing.GroupLayout(drawingArea1);
        drawingArea1.setLayout(drawingArea1Layout);
        drawingArea1Layout.setHorizontalGroup(
            drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        drawingArea1Layout.setVerticalGroup(
            drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        arrivingTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivingTextFieldActionPerformed(evt);
            }
        });

        takeoffTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeoffTextFieldActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel10.setText("Airport Simulator (SUR)");

        jLabel1.setText("Arriving Flight:");

        statusLabel.setText("Press 'START' to begin simulation.");

        jLabel2.setText("Takeoff Flight:");

        jLabel12.setText("Arrivals");

        jLabel13.setText("Takeoffs");

        takeoffsTextArea.setEditable(false);
        takeoffsTextArea.setColumns(20);
        takeoffsTextArea.setRows(5);
        takeoffsTextArea.setAutoscrolls(false);
        takeoffsTextArea.setFocusable(false);
        takeoffsTextArea.setRequestFocusEnabled(false);
        takeoffsTextArea.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(takeoffsTextArea);

        arrivalsTextArea.setEditable(false);
        arrivalsTextArea.setColumns(20);
        arrivalsTextArea.setRows(5);
        arrivalsTextArea.setAutoscrolls(false);
        arrivalsTextArea.setFocusable(false);
        arrivalsTextArea.setRequestFocusEnabled(false);
        arrivalsTextArea.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(arrivalsTextArea);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawingArea1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(statusLabel)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(arrivingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(takeoffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(146, 146, 146))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(statusLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(startButton)
                                .addGap(43, 43, 43)
                                .addComponent(jButton2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(arrivingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(takeoffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(drawingArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arrivingTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivingTextFieldActionPerformed
        // TODO add your handling code here:
        if (checkVerification(arrivingTextField.getText()) == true) {
            landing.add(Integer.parseInt(arrivingTextField.getText()));
            arrivingTextField.setText("");
            arrivalsTextArea.setText("");
            updateText(landing, 'a');
        } else {
            //ERROR MESSAGE
            jDialog1.setVisible(true);
        }
    }//GEN-LAST:event_arrivingTextFieldActionPerformed

    private void takeoffTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeoffTextFieldActionPerformed
        // TODO add your handling code here:
        if (checkVerification(takeoffTextField.getText()) == true) {
            takeOff.add(Integer.parseInt(takeoffTextField.getText()));
            takeoffTextField.setText("");
            takeoffsTextArea.setText("");
            updateText(takeOff, 't');
        } else {
            //ERROR MESSAGE
            jDialog1.setVisible(true);
        }
    }//GEN-LAST:event_takeoffTextFieldActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        //landing 4, takeoff 2
        startButton.setEnabled(false); 
        t.start();
        DrawingArea.t1.start();
    }//GEN-LAST:event_startButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        takeoffTextField.setText("");
        arrivingTextField.setText("");
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea arrivalsTextArea;
    private javax.swing.JTextField arrivingTextField;
    private DrawingArea drawingArea1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField takeoffTextField;
    private javax.swing.JTextArea takeoffsTextArea;
    // End of variables declaration//GEN-END:variables
}
