package com.duan.Form;

import com.duan.UI.Test;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;

public class MenuJPanel extends javax.swing.JPanel {

    private Test test;

    public MenuJPanel(Test test) {
        initComponents();

        btnDangXuat.setBorderPainted(true);
        lblTongQuan.setOpaque(true);
        lblHoaDon.setOpaque(true);
        lblKhachHang.setOpaque(true);
        lblMatHang.setOpaque(true);
        lblNhanSu.setOpaque(true);
        btnDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTongQuan.setLocation(10, 10);
        this.test = test;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTongQuan = new javax.swing.JLabel();
        lblNhanSu = new javax.swing.JLabel();
        lblMatHang = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblBaoCao = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        Ngang = new javax.swing.JPanel();
        btnDangXuat = new com.duan.Design.ButtonBoder();

        Menu.setBackground(new java.awt.Color(63, 70, 99));
        Menu.setPreferredSize(new java.awt.Dimension(50, 557));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU MANAGE");
        jLabel1.setToolTipText("");
        Menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 250, 40));

        lblTongQuan.setBackground(new java.awt.Color(63, 70, 99));
        lblTongQuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/Home.png"))); // NOI18N
        lblTongQuan.setText("   TỔNG QUAN");
        lblTongQuan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTongQuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTongQuanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTongQuanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTongQuanMousePressed(evt);
            }
        });
        Menu.add(lblTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 250, 50));

        lblNhanSu.setBackground(new java.awt.Color(63, 70, 99));
        lblNhanSu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNhanSu.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/Human.png"))); // NOI18N
        lblNhanSu.setText("   QUẢN LÝ NHÂN SỰ");
        lblNhanSu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNhanSu.setFocusable(false);
        lblNhanSu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNhanSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNhanSuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNhanSuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblNhanSuMousePressed(evt);
            }
        });
        Menu.add(lblNhanSu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 250, 50));

        lblMatHang.setBackground(new java.awt.Color(63, 70, 99));
        lblMatHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMatHang.setForeground(new java.awt.Color(255, 255, 255));
        lblMatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/box.png"))); // NOI18N
        lblMatHang.setText("   MẶT HÀNG");
        lblMatHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMatHang.setPreferredSize(new java.awt.Dimension(100, 24));
        lblMatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMatHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMatHangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMatHangMousePressed(evt);
            }
        });
        Menu.add(lblMatHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 250, 50));

        lblKhachHang.setBackground(new java.awt.Color(63, 70, 99));
        lblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/customer.png"))); // NOI18N
        lblKhachHang.setText("   KHÁCH HÀNG");
        lblKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblKhachHangMousePressed(evt);
            }
        });
        Menu.add(lblKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 250, 50));

        lblBaoCao.setBackground(new java.awt.Color(63, 70, 99));
        lblBaoCao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        lblBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/contact.png"))); // NOI18N
        lblBaoCao.setText("  LIÊN HỆ/PHẢN HỒI");
        lblBaoCao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBaoCao.setOpaque(true);
        lblBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBaoCaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBaoCaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBaoCaoMouseExited(evt);
            }
        });
        Menu.add(lblBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 250, 50));

        lblHoaDon.setBackground(new java.awt.Color(63, 70, 99));
        lblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/billl.png"))); // NOI18N
        lblHoaDon.setText("   HÓA ĐƠN");
        lblHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHoaDonMousePressed(evt);
            }
        });
        Menu.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 250, 50));

        Ngang.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout NgangLayout = new javax.swing.GroupLayout(Ngang);
        Ngang.setLayout(NgangLayout);
        NgangLayout.setHorizontalGroup(
            NgangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        NgangLayout.setVerticalGroup(
            NgangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Menu.add(Ngang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 230, 2));

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setForeground(new java.awt.Color(255, 51, 51));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/shutdown.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        Menu.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setBackgroundEntered(int option) {
        switch (option) {
            case 9:
                lblTongQuan.setBackground(Color.decode("#2D4563"));
                lblTongQuan.setForeground(Color.decode("#50B83C"));
                lblNhanSu.setBackground(Color.decode("#3F4663"));
                lblNhanSu.setForeground(Color.white);
                lblMatHang.setBackground(Color.decode("#3F4663"));
                lblMatHang.setForeground(Color.white);
                lblKhachHang.setBackground(Color.decode("#3F4663"));
                lblKhachHang.setForeground(Color.white);
                lblHoaDon.setBackground(Color.decode("#3F4663"));
                lblHoaDon.setForeground(Color.white);
                lblBaoCao.setBackground(Color.decode("#3F4663"));
                lblBaoCao.setForeground(Color.white);
                break;
            case 1:
                lblTongQuan.setBackground(Color.decode("#3F4663"));
                lblTongQuan.setForeground(Color.white);
                lblNhanSu.setBackground(Color.decode("#2D4563"));
                lblNhanSu.setForeground(Color.decode("#50B83C"));
                lblMatHang.setBackground(Color.decode("#3F4663"));
                lblMatHang.setForeground(Color.white);
                lblKhachHang.setBackground(Color.decode("#3F4663"));
                lblKhachHang.setForeground(Color.white);
                lblHoaDon.setBackground(Color.decode("#3F4663"));
                lblHoaDon.setForeground(Color.white);
                lblBaoCao.setBackground(Color.decode("#3F4663"));
                lblBaoCao.setForeground(Color.white);
                break;
            case 2:
                lblTongQuan.setBackground(Color.decode("#3F4663"));
                lblTongQuan.setForeground(Color.white);
                lblNhanSu.setBackground(Color.decode("#3F4663"));
                lblNhanSu.setForeground(Color.white);
                lblMatHang.setBackground(Color.decode("#2D4563"));
                lblMatHang.setForeground(Color.decode("#50B83C"));
                lblKhachHang.setBackground(Color.decode("#3F4663"));
                lblKhachHang.setForeground(Color.white);
                lblHoaDon.setBackground(Color.decode("#3F4663"));
                lblHoaDon.setForeground(Color.white);
                lblBaoCao.setBackground(Color.decode("#3F4663"));
                lblBaoCao.setForeground(Color.white);
                break;
            case 3:
                lblTongQuan.setBackground(Color.decode("#3F4663"));
                lblTongQuan.setForeground(Color.white);
                lblNhanSu.setBackground(Color.decode("#3F4663"));
                lblNhanSu.setForeground(Color.white);
                lblMatHang.setBackground(Color.decode("#3F4663"));
                lblMatHang.setForeground(Color.white);
                lblKhachHang.setBackground(Color.decode("#2D4563"));
                lblKhachHang.setForeground(Color.decode("#50B83C"));
                lblHoaDon.setBackground(Color.decode("#3F4663"));
                lblHoaDon.setForeground(Color.white);
                lblBaoCao.setBackground(Color.decode("#3F4663"));
                lblBaoCao.setForeground(Color.white);
                break;
            case 4:
                lblTongQuan.setBackground(Color.decode("#3F4663"));
                lblTongQuan.setForeground(Color.white);
                lblNhanSu.setBackground(Color.decode("#3F4663"));
                lblNhanSu.setForeground(Color.white);
                lblMatHang.setBackground(Color.decode("#3F4663"));
                lblMatHang.setForeground(Color.white);
                lblKhachHang.setBackground(Color.decode("#3F4663"));
                lblKhachHang.setForeground(Color.white);
                lblHoaDon.setBackground(Color.decode("#2D4563"));
                lblHoaDon.setForeground(Color.decode("#50B83C"));
                lblBaoCao.setBackground(Color.decode("#3F4663"));
                lblBaoCao.setForeground(Color.white);
                break;
            case 5:
                lblTongQuan.setBackground(Color.decode("#3F4663"));
                lblTongQuan.setForeground(Color.white);
                lblNhanSu.setBackground(Color.decode("#3F4663"));
                lblNhanSu.setForeground(Color.white);
                lblMatHang.setBackground(Color.decode("#3F4663"));
                lblMatHang.setForeground(Color.white);
                lblKhachHang.setBackground(Color.decode("#3F4663"));
                lblKhachHang.setForeground(Color.white);
                lblHoaDon.setBackground(Color.decode("#3F4663"));
                lblHoaDon.setForeground(Color.white);
                lblBaoCao.setBackground(Color.decode("#2D4563"));
                lblBaoCao.setForeground(Color.decode("#50B83C"));
                break;
        }
    }

    public void setBackgroundIteamMenuEntered(JLabel lbl) {
        String color = lbl.getBackground() + "";
        if (!(color.equals("java.awt.Color[r=45,g=69,b=99]"))) {
            lbl.setBackground(Color.decode("#557B83"));
        }
    }

    public void setBackgroundIteamMenuExited(JLabel lbl) {
        String color = lbl.getBackground() + "";
        if (color.equals("java.awt.Color[r=85,g=123,b=131]")) {
            lbl.setBackground(Color.decode("#3F4663"));
            lbl.setForeground(Color.white);
        }
    }

    private void lblNhanSuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanSuMouseEntered
        setBackgroundIteamMenuEntered(lblNhanSu);

    }//GEN-LAST:event_lblNhanSuMouseEntered

    private void lblNhanSuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanSuMouseExited
        setBackgroundIteamMenuExited(lblNhanSu);
    }//GEN-LAST:event_lblNhanSuMouseExited

    private void lblMatHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatHangMouseEntered
        setBackgroundIteamMenuEntered(lblMatHang);
    }//GEN-LAST:event_lblMatHangMouseEntered

    private void lblMatHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatHangMouseExited
        setBackgroundIteamMenuExited(lblMatHang);
    }//GEN-LAST:event_lblMatHangMouseExited

    private void lblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseEntered
        setBackgroundIteamMenuEntered(lblKhachHang);
    }//GEN-LAST:event_lblKhachHangMouseEntered

    private void lblKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseExited
        setBackgroundIteamMenuExited(lblKhachHang);
    }//GEN-LAST:event_lblKhachHangMouseExited

    private void lblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseEntered
        setBackgroundIteamMenuEntered(lblHoaDon);
    }//GEN-LAST:event_lblHoaDonMouseEntered

    private void lblHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseExited
        setBackgroundIteamMenuExited(lblHoaDon);
    }//GEN-LAST:event_lblHoaDonMouseExited

    private void lblTongQuanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuanMouseEntered
        setBackgroundIteamMenuEntered(lblTongQuan);
    }//GEN-LAST:event_lblTongQuanMouseEntered

    private void lblTongQuanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuanMouseExited
        setBackgroundIteamMenuExited(lblTongQuan);

    }//GEN-LAST:event_lblTongQuanMouseExited

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        this.test.click(7);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void lblNhanSuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanSuMousePressed
        this.test.click(1);
    }//GEN-LAST:event_lblNhanSuMousePressed

    private void lblMatHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatHangMousePressed
        this.test.click(2);
    }//GEN-LAST:event_lblMatHangMousePressed

    private void lblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMousePressed
        this.test.click(3);
    }//GEN-LAST:event_lblKhachHangMousePressed

    private void lblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMousePressed
        this.test.click(4);
    }//GEN-LAST:event_lblHoaDonMousePressed

    private void lblTongQuanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuanMousePressed
        this.test.click(9);
    }//GEN-LAST:event_lblTongQuanMousePressed

    private void lblBaoCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseEntered
        setBackgroundIteamMenuEntered(lblBaoCao);
    }//GEN-LAST:event_lblBaoCaoMouseEntered

    private void lblBaoCaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseExited
        setBackgroundIteamMenuExited(lblBaoCao);
    }//GEN-LAST:event_lblBaoCaoMouseExited

    private void lblBaoCaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseClicked
       Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = null;
        try {
            oURL = new URI("https://www.facebook.com/congtygido/?ref=pages_you_manage");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        try {
            desktop.browse(oURL);
        } catch (IOException ex) {
         ex.printStackTrace();
        }
    }//GEN-LAST:event_lblBaoCaoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Ngang;
    private com.duan.Design.ButtonBoder btnDangXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMatHang;
    private javax.swing.JLabel lblNhanSu;
    private javax.swing.JLabel lblTongQuan;
    // End of variables declaration//GEN-END:variables

}
