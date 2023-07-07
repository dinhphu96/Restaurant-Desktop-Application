package com.duan.Messege;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MessageInforJDialog2 extends javax.swing.JDialog {

    //private final JFrame fram;
    private Animator animator;
    private boolean show;
    private Glass glass;

    public MessageInforJDialog2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //this.fram = fr;
        initComponents();
        init();
    }
    
    private void init() {
        StyledDocument doc = textPane2.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setBackground(new Color(0, 0, 0, 0));
        textPane2.setOpaque(false);
        textPane2.setBackground(new Color(0, 0, 0, 0));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();

        btnOk2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnOk2.setBackground(new Color(76, 206, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnOk2.setBackground(new Color(69, 191, 71));
            }
        });
    }
    
    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    public void showMessage(String title, String message) {
        //fram.setGlassPane(glass);
        glass.setVisible(true);
        lblTitle2.setText(title);
        textPane2.setText(message);
       setLocationRelativeTo(null);
        startAnimator(true);
        setVisible(true);
    }

    public void closeMessage() {
        startAnimator(false);
    }

    @Override
    public void pack() {
        super.pack(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new com.duan.Messege.Background();
        lblIcon2 = new javax.swing.JLabel();
        lblTitle2 = new javax.swing.JLabel();
        textPane2 = new javax.swing.JTextPane();
        btnOk2 = new com.duan.Design.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

        lblIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/question.png"))); // NOI18N

        lblTitle2.setBackground(new java.awt.Color(245, 71, 71));
        lblTitle2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle2.setForeground(new java.awt.Color(245, 71, 71));
        lblTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle2.setText("NULL");

        textPane2.setEditable(false);
        textPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textPane2.setForeground(new java.awt.Color(76, 76, 76));
        textPane2.setText("NULL");
        textPane2.setFocusable(false);

        btnOk2.setBackground(new java.awt.Color(69, 191, 71));
        btnOk2.setForeground(new java.awt.Color(255, 255, 255));
        btnOk2.setText("OK");
        btnOk2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIcon2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
            .addComponent(lblTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textPane2)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnOk2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(lblIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(textPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk2ActionPerformed
        dispose();
        startAnimator(false);
    }//GEN-LAST:event_btnOk2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Messege.Background background;
    private com.duan.Design.Button btnOk2;
    private javax.swing.JLabel lblIcon2;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JTextPane textPane2;
    // End of variables declaration//GEN-END:variables
}
