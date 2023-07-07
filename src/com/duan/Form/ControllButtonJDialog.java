package com.duan.Form;

import com.duan.DAO.KhachHangDAO;
import com.duan.DAO.NhanVienDAO;
import static com.duan.Form.KhachHangJPanel.khachHang;
import com.duan.UI.MainJFrame;
import com.duan.UI.NhanSuJDialog;
import static com.duan.Form.NhanSuPanel.listNhanVien;
import com.duan.UI.Test;
import com.duan.UI.ThongTinKHJDialog;
import com.duan.Untils.Auth;
import java.awt.Color;

public class ControllButtonJDialog extends javax.swing.JDialog {

    Test test;
    int form;
    int nhanSu;
    NhanVienDAO daonv = new NhanVienDAO();
    KhachHangDAO khachHangDao = new KhachHangDAO();
    MainJFrame main;

    public ControllButtonJDialog(java.awt.Frame parent, boolean modal, Test test) {
        super(parent, modal);
        initComponents();
        this.test = test;
        this.form = MainJFrame.form;
        this.nhanSu = NhanSuPanel.nhanSu;
        init();

    }

    public void init() {
        panelRound1.setOpaque(false);
        panelRound1.setBackground(new Color(0, 0, 0, 0));
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
    }

    public void Update() {

        dispose();
        switch (form) {
            case 1:
                if (!Auth.isManager()) {
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Bạn không có quyền sửa nhân viên";
                    test.click(14);

                } else {
                    new NhanSuJDialog(new javax.swing.JFrame(), true, nhanSu, test).setVisible(true);
                }
                break;
            case 3:
                if (!Auth.isManager()) {
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Bạn không có quyền sửa khách hàng";
                    test.click(14);

                } else {
                    KhachHangJPanel kh = new KhachHangJPanel(test);
                    new ThongTinKHJDialog(new javax.swing.JFrame(), true, kh.khachHang, test).setVisible(true);
                }
                break;
        }

    }

    public void Delete() {

        switch (form) {
            case 1:
                if (!Auth.isManager()) {
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Bạn không có quyền xóa nhân viên";
                    test.click(14);

                } else {
                    try {
                        String manv = listNhanVien.get(nhanSu).getMaNV();
                        daonv.delete(manv);
                        main.title = "TMTP";
                        main.message = "Xóa nhân viên thành công!";
                        main.infor = 1;
                        test.click(1);
                    } catch (Exception e) {
                        main.title = "TMTP";
                        main.message = "Xóa nhân viên thất bại!";
                        test.click(14);
                    }
                }
                break;

            case 3:
                if (!Auth.isManager()) {
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Bạn không có quyền xóa khách hàng";
                    test.click(14);

                } else {
                    try {
                        khachHangDao.delete(khachHang.getMaKH());
                        main.title = "TMTP";
                        main.message = "Xóa khách hàng thành công!";
                        main.infor = 1;
                        test.click(3);
                    } catch (Exception e) {
                        main.title = "TMTP";
                        main.message = "Xóa khách hàng thất bại!";
                        test.click(14);
                    }
                    test.click(3);
                }
                break;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.duan.Design.PanelRound();
        btnXoa = new com.duan.Design.ButtonBoder();
        btnSua = new com.duan.Design.ButtonBoder();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 102, 102));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        dispose();
    }//GEN-LAST:event_formWindowLostFocus

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Update();
        dispose();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Delete();
        dispose();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.ButtonBoder btnSua;
    private com.duan.Design.ButtonBoder btnXoa;
    private com.duan.Design.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
