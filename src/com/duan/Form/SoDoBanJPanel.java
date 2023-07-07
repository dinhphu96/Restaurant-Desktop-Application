package com.duan.Form;

import com.duan.UI.CaiDatBanJDialog;
import com.duan.UI.Test;
import java.awt.Color;

public class SoDoBanJPanel extends javax.swing.JPanel{
    Test test;
    Integer soLuongBan;
    public SoDoBanJPanel(Test test, Integer index) {
        initComponents();
        this.setBackground(Color.decode("#2D4563"));
        this.test = test;
        this.soLuongBan = index;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button1 = new com.duan.Design.Button();
        btnQuanLyBan = new com.duan.Design.ButtonBoder();

        jPanel1.setBackground(new java.awt.Color(63, 70, 99));

        button1.setBackground(new java.awt.Color(10, 135, 219));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-location-white.png"))); // NOI18N
        button1.setText("Khu vực 1");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnQuanLyBan.setBackground(new java.awt.Color(14, 209, 134));
        btnQuanLyBan.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/setting.png"))); // NOI18N
        btnQuanLyBan.setText("  Quản lý bàn  ");
        btnQuanLyBan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnQuanLyBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuanLyBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(btnQuanLyBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuanLyBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyBanActionPerformed
        new CaiDatBanJDialog(new javax.swing.JFrame(), true, test, soLuongBan).setVisible(true);
    }//GEN-LAST:event_btnQuanLyBanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.ButtonBoder btnQuanLyBan;
    private com.duan.Design.Button button1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
