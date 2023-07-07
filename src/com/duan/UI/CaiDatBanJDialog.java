package com.duan.UI;

import com.duan.DAO.BanDAO;
import com.duan.Model.Ban;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.JDialog;

public class CaiDatBanJDialog extends javax.swing.JDialog {

    public int soLuongBan;
    private Test test;
    BanDAO dao = new BanDAO();
    List<Ban> listBan;

    public CaiDatBanJDialog(java.awt.Frame parent, boolean modal, Test test, Integer soLuongBan) {
        super(parent, modal);
        initComponents();
        panelShadow1.setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
        this.initMoving(CaiDatBanJDialog.this);
        this.test = test;
        this.soLuongBan = soLuongBan;
        SoLuongBanSlider.setValue(soLuongBan);
        setLocationRelativeTo(null);

        listBan = dao.selectAll();
    }

    private int x, y;

    public void initMoving(JDialog frame) {
        pnlHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        pnlHeader.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    void capNhat() {
        listBan = dao.selectAll();
        int sl = soLuongBan - SoLuongBanSlider.getValue();
        int dem = 0;
        int demcong = 0;
        int sltrong = soLuongBanTrong();

        if (sl > 0) {
            // Xóa
            if (sl > sltrong) {
                // ko dc
                main.title = "TMTP";
                main.message = "Lỗi: Số lượng bàn muốn xóa nhiều hơn số lượng bàn trống!";
                main.infor = 1;
                test.click(10);
                return;
            } else {
                for (int i = listBan.size() - 1; i >= 0; i--) {
                    if (dem == sl) {
                        break;
                    } else {
                        if (!listBan.get(i).isTrangThai()) {
                            dao.delete(listBan.get(i).getMaBan());
                            dem++;
                        }
                    }
                }
            }
        } else {
            // Thêm
            int j = 0;
            while (true) {
                if (demcong == Math.abs(sl)) {
                    break;
                }
                if ((j + 1) > listBan.size()) {
                    Ban ban = new Ban();
                    ban.setMaBan(j + 1);
                    ban.setKhuVuc(0);
                    ban.setTrangThai(false);
                    dao.insert(ban);
                    listBan = dao.selectAll();
                    demcong++;
                } else {
                    if ((j + 1) == listBan.get(j).getMaBan()) {
                    } else {
                        Ban ban = new Ban();
                        ban.setMaBan(j + 1);
                        ban.setKhuVuc(0);
                        ban.setTrangThai(false);
                        dao.insert(ban);
                        listBan = dao.selectAll();
                        demcong++;
                    }
                }
                j++;
            }
        }
        soLuongBan = SoLuongBanSlider.getValue();
        dispose();
        main.soLuongBan = soLuongBan;
        main.title = "TMTP";
        main.message = "Cập nhật thành công";
        main.infor = 1;
        test.click(10);
    }

    private int soLuongBanTrong() {
        int soLuongBanTrong = 0;
        for (Ban ban : listBan) {
            if (ban.isTrangThai() == false) {
                soLuongBanTrong += 1;
            }
        }
        return soLuongBanTrong;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new com.duan.Shadow.PanelShadow();
        pnlHeader = new javax.swing.JPanel();
        btnExit = new com.duan.Design.Button();
        btnMiniSize = new com.duan.Design.Button();
        jLabel1 = new javax.swing.JLabel();
        SoLuongBanSlider = new javax.swing.JSlider();
        btnCapNhat = new com.duan.Design.ButtonBoder();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlHeader.setBackground(new java.awt.Color(153, 255, 255));
        pnlHeader.setOpaque(false);
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setBackground(new java.awt.Color(255, 51, 51));
        btnExit.setBorder(null);
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setFocusable(false);
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        pnlHeader.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 48, 25));

        btnMiniSize.setBackground(new java.awt.Color(204, 204, 204));
        btnMiniSize.setBorder(null);
        btnMiniSize.setForeground(new java.awt.Color(255, 255, 255));
        btnMiniSize.setText("_");
        btnMiniSize.setFocusable(false);
        btnMiniSize.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMiniSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseExited(evt);
            }
        });
        pnlHeader.add(btnMiniSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 50, 25));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Số lượng bàn ");

        SoLuongBanSlider.setMajorTickSpacing(1);
        SoLuongBanSlider.setMaximum(30);
        SoLuongBanSlider.setMinimum(1);
        SoLuongBanSlider.setMinorTickSpacing(1);
        SoLuongBanSlider.setPaintLabels(true);
        SoLuongBanSlider.setPaintTicks(true);
        SoLuongBanSlider.setValue(15);
        SoLuongBanSlider.setName(""); // NOI18N
        SoLuongBanSlider.setValueIsAdjusting(true);

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-update-20.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294))
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(SoLuongBanSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SoLuongBanSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(Color.decode("#FF6B6B"));
        btnExit.setForeground(Color.RED);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMiniSizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniSizeMouseEntered
        btnMiniSize.setBackground(Color.BLACK);
        btnMiniSize.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnMiniSizeMouseEntered

    private void btnMiniSizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniSizeMouseExited
        btnMiniSize.setBackground(Color.LIGHT_GRAY);
        btnMiniSize.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnMiniSizeMouseExited

    MainJFrame main;
    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        capNhat();

    }//GEN-LAST:event_btnCapNhatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider SoLuongBanSlider;
    private com.duan.Design.ButtonBoder btnCapNhat;
    private com.duan.Design.Button btnExit;
    private com.duan.Design.Button btnMiniSize;
    private javax.swing.JLabel jLabel1;
    private com.duan.Shadow.PanelShadow panelShadow1;
    private javax.swing.JPanel pnlHeader;
    // End of variables declaration//GEN-END:variables
}
